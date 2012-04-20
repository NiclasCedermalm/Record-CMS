package org.record.content.model.data
import scala.collection.mutable.ListBuffer
import org.record.content.NodeRelationship

class HierarchicalContentData extends ContentData {

  var name : String = _
  var children : ListBuffer[ContentData] = new ListBuffer[ContentData]
  
   override def serialize = {
	  super.serialize
	  node.field("name", name)  
	  children.map(child => relationships += new NodeRelationship("child", child))
	}
	
	override def deserialize = {
	  super.deserialize
	  name = node.field("name")
	  relationships.filter(relationship => relationship.name.equals("child")).map(child => children += child.getTarget.asInstanceOf[ContentData])
	}
}