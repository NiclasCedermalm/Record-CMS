package org.record.content
import scala.collection.mutable.HashMap
import org.record.content.model._

object RenderConstants {
  
  val OUTPUT = "Output";
  
}

class RenderContext {
  
	var parameters : HashMap[String, AnyRef] = new HashMap[String, AnyRef];
}

class RenderImplicits {
  
  implicit def renderWrapper(content: Content) = new {
	 def render(context : RenderContext) : play.mvc.Content = {
        RenderManager.render(content, context)
     }
  }
  
  /*
  implicit def renderChildWrapper(content : CompositeContent) = new {
    def render(name : String, context : RenderContext) : play.mvc.Content = {
      RenderManager.render(content.children(name), context)
    }
  }
  */
  
}