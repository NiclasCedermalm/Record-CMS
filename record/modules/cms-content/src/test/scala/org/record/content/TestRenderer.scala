package org.record.content

import play.api.templates.Html
import org.record.content.behaviour.Renderer

trait TestRenderer extends Renderer {

  override def render(context : RenderContext) : play.mvc.Content = {
    println("Rendering...")
    return new Html("<b>Tjabba</b>")
  }
}