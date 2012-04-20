package org.record.content.model.data

class TextContentData(_text : String) extends ConcreteContentData {
	var text: String = _text
	
	def this() = this("")
	
	override def serialize = {
	  super.serialize
	  node.field("text", text)
	}
	
	override def deserialize = {
	  super.deserialize
	  text = node.field("text")
	}
	
}