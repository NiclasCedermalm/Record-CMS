package org.record.content.model
import org.record.content.model.data.LinkContentData

class LinkContent(override val contentData : LinkContentData) extends ConcreteContent(contentData) {
  val title: String = contentData.title
  var linkTo: Content = contentData.linkTo.retrieveRuntimeContent

}