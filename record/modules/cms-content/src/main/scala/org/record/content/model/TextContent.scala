package org.record.content.model

import play.api.templates.Html
import org.record.content.RenderContext
import org.record.content.behaviour.Renderer
import org.record.content.model.data.TextContentData

class TextContent(override val contentData : TextContentData) extends ConcreteContent(contentData) with Renderer {

  val text: String = contentData.text

  override def render(context: RenderContext): play.mvc.Content = {
    return new Html(text)
  }

}