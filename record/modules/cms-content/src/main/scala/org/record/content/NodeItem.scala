package org.record.content
import com.orientechnologies.orient.core.record.impl.ODocument
import javax.persistence.{Version, Id}
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

class NodeRelationship(val name : String, private val target : NodeItem) {
  def getTarget : NodeItem = {
    if ( target.isInstanceOf[LazyLoadedNodeItem] ) {
      return target.asInstanceOf[LazyLoadedNodeItem].load()
    }
    return target
  }
}

trait NodeItem {

	//var id : String = null;
	def id : String = {
	  if ( node != null ) {
		  return node.getIdentity().toString()
	  }
	  return null
	}
	var node : ODocument = null;
	var relationships : ListBuffer[NodeRelationship] = new ListBuffer[NodeRelationship]

	def serialize
	
	def deserialize
}