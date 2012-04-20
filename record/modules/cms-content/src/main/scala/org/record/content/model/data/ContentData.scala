package org.record.content.model.data

import javax.persistence.Id
import org.record.content.model.Content
import org.record.content.util.DynamicTraitFactory
import org.record.content.NodeItem
import scala.collection.mutable.ListBuffer

abstract class ContentData extends NodeItem {
  
  var parent : HierarchicalContentData = _ 
  
  var behaviours : ListBuffer[BehaviourData] = new ListBuffer[BehaviourData]
  
 
  def retrieveRuntimeContent[T <: Content] : T = {
    println("Getting runtime content for id: " + id)
    val traitClassNames = behaviours.map(behaviour => behaviour.traitClassName)
    return DynamicTraitFactory.newInstance(retrieveRuntimeContentClass, Array(this), traitClassNames.toList).asInstanceOf[T]
  }
  
  def retrieveRuntimeContentClass : Class[_ <: Content] = {
    val dataClassName = this.getClass.getCanonicalName
    val runtimeClassName = dataClassName.substring(0,dataClassName.length-4).replace(".data.", ".")
    return this.getClass.getClassLoader.loadClass(runtimeClassName).asInstanceOf[Class[_ <: Content]]
  }
  
  override def serialize = {
	  relationships.clear()
  }
	
  override def deserialize = {
	  
  }
}