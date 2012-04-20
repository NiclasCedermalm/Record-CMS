package org.record.content.model

import scala.collection.mutable.ListBuffer
import org.record.content.model.data.CompositeContentData
import scala.collection.mutable.HashMap

class CompositeContent(override val contentData : CompositeContentData) extends Content(contentData) {
  
    var tmpMap = new HashMap[String,Content] // TEMP. UGLO CODE
    for ( child <- contentData.children ) {
      tmpMap.put(child._1, child._2.retrieveRuntimeContent[Content])
    }
  
	val children : Map[String,Content] = tmpMap.toMap[String,Content]
   
    
   // TODO: Reference to a prototype? Which works as a schema
   
}