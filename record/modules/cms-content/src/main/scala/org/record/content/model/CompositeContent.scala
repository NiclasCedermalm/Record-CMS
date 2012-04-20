package org.record.content.model

import scala.collection.mutable.ListBuffer
import org.record.content.model.data.CompositeContentData
import scala.collection.immutable.HashMap

class CompositeContent(override val contentData : CompositeContentData) extends Content(contentData) {
  
	var children : Map[String,Content] = new HashMap[String,Content];
   
    
   // TODO: Reference to a prototype? Which works as a schema
   
}