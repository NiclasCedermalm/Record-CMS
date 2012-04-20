package org.record.content.model.data
import org.record.content.behaviour.Behaviour
import org.record.content.behaviour.PlayTemplate

class PlayTemplateData extends BehaviourData {
  var templateFunctionName: String = _

  override def getTraitClass() : Class[_ <: Behaviour] = classOf[PlayTemplate]
  
  override def serialize = {
    node.field("templateFunctionName", templateFunctionName)
  }

  override def deserialize = {
    templateFunctionName = node.field("templateFunctionName")
  }
}