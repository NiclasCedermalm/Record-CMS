package org.record.content.model.data
import scala.collection.mutable.ListBuffer

class RuntimeWiredContentData extends ContentData {
  var content: ContentData = _
  var children : ListBuffer[ContentData] = new ListBuffer[ContentData]
  
  override def serialize = {
	  super.serialize
	  
	}
	
	override def deserialize = {
	  super.deserialize
	  
	}
}