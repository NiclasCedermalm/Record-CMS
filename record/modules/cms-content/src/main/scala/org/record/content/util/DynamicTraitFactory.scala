package org.record.content.util
import scala.runtime.Boxed
import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter.IMain
import java.io.ByteArrayOutputStream
import scala.util.control.Breaks._
import java.lang.reflect.Constructor
import scala.collection.mutable.HashMap

case class NoMatchingConstructorFound extends Exception

// TODO: Can we mixin behaviour data here??

/**
 * Dynamic Trait Factory - Creates object instances with arbitrary traits on the fly.
 */
object DynamicTraitFactory {

  val dynamicClassLoader = new DynamicClassLoader
  var mixinClassCache = new HashMap[String,Class[_]]
  
  def newInstance(clazz : Class[_ <: AnyRef], traitClassNames : List[String]) : AnyRef = {
    val cacheKey = buildCacheKey(clazz, null, traitClassNames)
    val mixinClass = mixinClassCache.get(cacheKey).getOrElse(buildMixinClass(cacheKey, clazz, null, traitClassNames))
    
    try {
    	return mixinClass.getConstructor().newInstance().asInstanceOf[AnyRef]
    }
    catch {
    	case e: NoSuchMethodException => throw new NoMatchingConstructorFound 
    }
  }
  
  def newInstance(clazz : Class[_ <: AnyRef], arguments : Array[AnyRef], traitClassNames : List[String]) : AnyRef = {
    
    println("Creating new instance of class: " + clazz)
    var argClasses = new Array[Class[_]](arguments.size)
    for (i <- 0 until arguments.size) {
      argClasses(i) = arguments(i).getClass
    }
    
    val cacheKey = buildCacheKey(clazz, argClasses, traitClassNames)
    val mixinClass = mixinClassCache.get(cacheKey).getOrElse(buildMixinClass(cacheKey, clazz, argClasses, traitClassNames))
    
    var mixinConstructor : Constructor[_] = null
    breakable {
	    for ( constructor <- mixinClass.getConstructors() ) {
	    	var parameterTypes = constructor.getParameterTypes()
	    	if ( parameterTypes.size == argClasses.size ) {
	    	    val matchingArgs = argClasses.zip(parameterTypes).count(tuple => tuple._1.isAssignableFrom(tuple._2))
	    		if ( matchingArgs == argClasses.size ) {
	    			mixinConstructor = constructor
	    			break
	    		}
	    	}
	    }
    }
    
    if ( mixinConstructor != null ) {
      try {
    	  return mixinConstructor.newInstance(arguments:_*).asInstanceOf[AnyRef]
      }
      catch {
        case e : java.lang.reflect.InvocationTargetException => throw e.getTargetException()
      }
    }
    throw new NoMatchingConstructorFound
  }
  
  def buildCacheKey(clazz : Class[_ <: AnyRef], argClasses : Array[Class[_]], traitClassNames : List[String]) : String = {
    var sb = new StringBuilder
    sb.append(clazz.getCanonicalName())
    sb.append(":");
    if ( argClasses != null ) {
    	argClasses.foreach(argClass => sb.append(argClass.getCanonicalName()).append(":"))
    }
    if ( traitClassNames != null ) {
    	traitClassNames.foreach(className => sb.append(className).append(":"))
    }
    return sb.toString()
  }
  
  def buildMixinClass(cacheKey : String, clazz : Class[_ <: AnyRef], argClasses : Array[Class[_]], traitClassNames : List[String]) : Class[_] = {
    if ( traitClassNames == null || traitClassNames.isEmpty ) {
      return clazz
    }
    println("Building mixin class for key: " + cacheKey)
    val mixinClass = dynamicClassLoader.buildClass(clazz, traitClassNames, argClasses)
    mixinClassCache.put(cacheKey, mixinClass)
    return mixinClass
  }
}

/**
 * Dynamic Class Loader
 * 
 * Handles generation & compilation of on-the-fly generated mixin classes
 */
object DynamicClassLoader {

  private var id = 0
  def uniqueId = synchronized {  id += 1; "MixinClass" + id.toString }
}

// TODO: Refactor into smaller methods
class DynamicClassLoader extends java.lang.ClassLoader(DynamicClassLoader.getClass.getClassLoader) {
	def buildClass(clazz: Class[_ <: AnyRef], traitClassNames: List[String], argumentClasses : Array[Class[_]]) = {

    val id = DynamicClassLoader.uniqueId
    val classDef = new StringBuilder

    classDef.append("class ").append(id)
    if ( argumentClasses != null ) {
	    classDef.append(" (")
	    for ( i <- 0 until argumentClasses.size) {
	    	classDef.append("p")
	    	classDef.append(i)
	    	classDef.append(" : ")
	    	classDef.append(argumentClasses(i).getCanonicalName())
	    	if ( i < argumentClasses.size-1 ) {
	    	  classDef.append(",")
	    	}
	    }
	    classDef.append(")")
    }
    classDef.append(" extends ").append(clazz.getCanonicalName)
    if ( argumentClasses != null) {
	    classDef.append("(")
	    for ( i <- 0 until argumentClasses.size) {
	    	classDef.append("p")
	    	classDef.append(i)
	    	if ( i < argumentClasses.size-1 ) {
	    		classDef.append(",")
	    	}
	    }
	    classDef.append(")")
	}
    traitClassNames.foreach(className => classDef.append(" with %s".format(className))) // TODO: Does this really work with several traits??

    val settings = new Settings(null)
    settings.usejavacp.value = true
    val interpreter = new IMain(settings)

    println("Compiling class def: " + classDef)
    
    // Problem med class loader?
    
    interpreter.compileString(classDef.toString())
    println("Class compiled")
    val r = interpreter.classLoader.getResourceAsStream(id)
    val o = new ByteArrayOutputStream
    val b = new Array[Byte](16384)
    Stream.continually(r.read(b)).takeWhile(_ > 0).foreach(o.write(b, 0, _))
    val bytes = o.toByteArray

    defineClass(id, bytes, 0, bytes.length)
    
  }
 
}
