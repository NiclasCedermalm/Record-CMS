package org.record.content.behaviour
import org.record.content.RenderContext

abstract trait Renderer extends Behaviour {
  
	// TODO: Should output be written in the context similar as Tridion does with compound templating???
  
	// How to render hierarchically?
    // The render should return something, but the context could carry other resources such as images etc
	
	// TODO: Do we need RenderContext?
  
  
	def render(context : RenderContext) : play.mvc.Content; 
	
}