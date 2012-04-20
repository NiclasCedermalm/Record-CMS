package org.record.content.model.data
import javax.persistence.Id
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import org.record.content.NodeItem

/*
 * Behaviour Data
 * 
 */
abstract class BehaviourData extends NodeItem {
  
  val traitClassName = this.getClass.getCanonicalName().replace("Data", "").replace(".model.data.", ".behaviour.") // TODO: Make a better solution for this

  // TODO: Behaviour can also have behaviour, for example if you want to have an editor for an behaviour
  // TODO: Is Behaviour content???
}
