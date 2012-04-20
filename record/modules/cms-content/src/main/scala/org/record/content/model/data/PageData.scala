package org.record.content.model.data

class PageData extends RuntimeWiredContentData {

  var path: String = _ // TODO: This should be handled by a hierarchical content item instead
  var title: String = _
  var filename: String = _
  var aliasPath : String = _ // For short URLs etc
  
  override def serialize = {
	  super.serialize
	  node.field("path", path)
	  node.field("title", title)
	  node.field("filename", filename)
	  node.field("aliasPath", aliasPath)
	}
	
	override def deserialize = {
	  super.deserialize
	  path = node.field("path")
	  title = node.field("title")
	  filename = node.field("filename")
	  aliasPath = node.field("aliasPath")
	}
}