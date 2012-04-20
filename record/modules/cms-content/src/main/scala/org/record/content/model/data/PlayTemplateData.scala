package org.record.content.model.data

class PlayTemplateData extends BehaviourData {
	var templateFunctionName : String = _
	
  override def serialize = {
	  node.field("templateFunctionName", templateFunctionName)
  }

  override def deserialize = {
	templateFunctionName = node.field("templateFunctionName")
  }
}