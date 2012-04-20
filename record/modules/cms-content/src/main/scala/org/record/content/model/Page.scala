package org.record.content.model
import org.record.content.model.data.PageData

class Page(override val contentData : PageData) extends RuntimeWiredContent(contentData) {

  val path: String = contentData.path // TODO: This should be handled by a hierarchical content item instead
  var title: String = contentData.title
  var filename: String = contentData.filename
  var aliasPath : String = contentData.aliasPath
  
  override def toString = "Page: path=" + path + ",filename=" + filename
}