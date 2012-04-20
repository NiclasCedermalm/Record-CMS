package org.record.content.model

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import org.record.content.NodeItem
import org.record.content.behaviour.VersionedItem
import javax.persistence.Id
import java.util.ArrayList
import org.record.content.model.data.ContentData
import org.record.content.util.DynamicTraitFactory
import org.record.content.behaviour.Behaviour
import org.record.content.model.data.BehaviourData

/**
 * Runtime Content. All data must be immutable.
 */


abstract class Content(val contentData : ContentData) extends VersionedItem {
  
  val id : String = contentData.id
  val parent : HierarchicalContent = getParent
  
  // Initialize behaviours
  //
  if ( contentData.behaviours.size > 0 ) {
   // this.asInstanceOf[Behaviour].initBehaviour(contentData.behaviours.toList)
  }

  
  //val taxonomies: List[Taxonomy] = _
  
   protected def getParent: HierarchicalContent = {
    if ( contentData.parent == null) return null
    return contentData.parent.retrieveRuntimeContent[HierarchicalContent]
  }
 
}
