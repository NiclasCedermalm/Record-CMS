package org.record.content.model
import org.record.content.behaviour.Renderer
import org.record.content.RenderContext
import org.record.content.model.data.ImageContentData

class ImageContent(override val contentData : ImageContentData) extends ConcreteContent(contentData) with Renderer {

  val imageName: String = contentData.imageName
  var image: Array[Byte] = contentData.image
  
  // TODO: How to handle rendering and resolve of images??

  override def render(context: RenderContext): play.mvc.Content = {
    return null
  }

}