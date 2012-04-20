package org.record.content.model

import scala.collection.mutable.ListBuffer
import org.record.content.NodeItem
import org.record.content.behaviour.VersionedItem
import javax.persistence.Id
import java.util.ArrayList
import org.record.content.model.data.ContentData
import org.record.content.util.DynamicTraitFactory
import org.record.content.behaviour._
import org.record.content.model.data._

/**
 * Runtime Content. All data must be immutable.
 */


abstract class Content(val contentData : ContentData) extends VersionedItem with PlayTemplate /* TEMP FIX */ {
  
  val id : String = contentData.id
  val parent : HierarchicalContent = getParent
  
  templateFunctionName = contentData.templateFunctionName // TEMP FIX
  
  // Initialize behaviours
  //
  // TEMP HARDCODE!!! FIX!!!
  /*for ( behaviour <- contentData.behaviours ) {
    if ( this.isInstanceOf[PlayTemplate] ) {
      this.asInstanceOf[PlayTemplate].init(behaviour.asInstanceOf[PlayTemplateData])
    }
  }*/

  
  //val taxonomies: List[Taxonomy] = _
  
   protected def getParent: HierarchicalContent = {
    if ( contentData.parent == null) return null
    return contentData.parent.retrieveRuntimeContent[HierarchicalContent]
  }
 
}
