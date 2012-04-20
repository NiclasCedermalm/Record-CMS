package org.record.content.model

import scala.collection.mutable.ListBuffer
import org.record.content.model.data.ConcreteContentData
import org.record.content.model.data.BehaviourData
import org.record.content.behaviour.Behaviour

abstract class ConcreteContent(override val contentData : ConcreteContentData) extends Content(contentData) with Behaviour {

}