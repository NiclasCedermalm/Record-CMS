package org.record.content.model.data
import javax.persistence.Id
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import org.record.content.NodeItem
import org.record.content.behaviour.Behaviour

/*
 * Behaviour Data
 * 
 */
abstract class BehaviourData extends NodeItem {
  		  
  def getTraitClass() : Class[_ <: Behaviour];
  
  // TODO: Behaviour can also have behaviour, for example if you want to have an editor for an behaviour
  // TODO: Is Behaviour content???
}
