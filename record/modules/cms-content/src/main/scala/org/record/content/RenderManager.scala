package org.record.content

import play.api.templates.Txt
import org.record.content.model.Content
import org.record.content.behaviour.Renderer

object RenderManager {
   
  def render(content : Content, context: RenderContext) : play.mvc.Content = {
    if ( content.isInstanceOf[Renderer] ) {
    	var renderer = content.asInstanceOf[Renderer]
    	return renderer.render(context) 
    }
	return Txt.empty;	    
  }
 
  def renderChildren(content : Content) : String = {
    null
  }
}