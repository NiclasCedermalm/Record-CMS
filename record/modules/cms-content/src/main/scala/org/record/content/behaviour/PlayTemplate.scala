package org.record.content.behaviour

import play.api.templates.Html
import java.lang.reflect.Method
import org.record.content.model.Content
import org.record.content.RenderContext
import org.record.content.model.data._

case class CouldNotInvokeTemplateException extends Exception

/**
 * Play Template
 */
trait PlayTemplate extends Renderer {
  
  
    var templateFunctionName : String = _
	var target : Content = this.asInstanceOf[Content] // TODO: Encapsylate this better!
    
    /* TODO: Stacked trait
	abstract override def initBehaviour(dataList : List[BehaviourData]) {
      // TODO: Factor into a function
		val templateData = dataList.filter(data => data.isInstanceOf[PlayTemplateData]).firstOption.
				orNull(throw new NotDefinedError("No data for Play Template")).asInstanceOf[PlayTemplateData]
		
		templateFunctionName = templateData.templateFunctionName
		super.initBehaviour(dataList)
	}
  	*/
  
	override def render(context: RenderContext) : play.mvc.Content = {
		println("Invoking template: " + templateFunctionName)
		val template = getTemplateObject[Any](templateFunctionName)
		return invokeTemplate(template, target, context)
	}
  
  	def getTemplateObject[T](name : String)(implicit man: Manifest[T]) : T = {
  	  Class.forName(name + "$").getField("MODULE$").get(man.erasure).asInstanceOf[T]
  	}
  	
  	def invokeTemplate(template : Any, content : Content, context: RenderContext) : play.mvc.Content = {
  	  
  	  val applyMethod = getApplyMethod(template)
  	  //val applyMethod = template.getClass.getMethod("apply", classOf[Page])
  	  if ( applyMethod == null ) {
  	    throw new CouldNotInvokeTemplateException
  	  }
  	  return applyMethod.invoke(template, content, context).asInstanceOf[play.mvc.Content]
  	}
  	
  	def getApplyMethod(template: Any) : Method = {
  	  for ( method <- template.getClass.getMethods()) {
  	    if ( method.getName().equals("apply")) {
  	      return method
  	    }
  	  }
  	  return null
  	}
  
}