package org.record.content.model.data
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

class CompositeContentData extends ContentData {
  
	var children : Map[String,ContentData] = new HashMap[String,ContentData]
}