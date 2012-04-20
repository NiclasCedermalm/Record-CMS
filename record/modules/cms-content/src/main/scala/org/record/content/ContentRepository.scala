package org.record.content

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import com.orientechnologies.orient.core.record.impl.ODocument
import org.record.content.model._
import org.record.content.model.data._
import com.orientechnologies.orient.core.db.`object`.ODatabaseObjectTx
import com.orientechnologies.orient.core.storage.OStorage
import com.orientechnologies.orient.core.serialization.serializer.`object`.OObjectSerializerHelper
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.db.graph.OGraphDatabase
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import com.orientechnologies.orient.core.exception.OQueryParsingException
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer


class LazyLoadedNodeItem(val docNode : ODocument, private val contentRepo : ContentRepository) extends NodeItem {
  
  this.node = docNode
  def load() : NodeItem = contentRepo.load(node)
  
  def serialize = load.serialize
  def deserialize = load.deserialize
  
}

// TODO: Have the content repository as singleton??

class ContentRepository(val db : OGraphDatabase) {
 
  // Some implicit magic comes here...
  implicit def dbWrapper(db: OGraphDatabase) = new {
	 def queryBySql[T](sql: String, params: AnyRef*): List[T] = {
        val params4java = params.toArray
        try {
        	val results : java.util.List[T] = db.query(new OSQLSynchQuery[T](sql), params4java: _*)
        	return results.asScala.toList
        }
        catch {
          case e : OQueryParsingException => return List[T]()
        }
       
     }
  }
  

  
  protected def createNode(nodeItem : NodeItem) {
    nodeItem.node = db.createVertex(nodeItem.getClass.getSimpleName().replace("Data",""))
    println("Created a node of class: " + nodeItem.node.getClassName())
  }
  
   def save(nodeItem : NodeItem) = {
    if ( nodeItem.node == null ) {
      createNode(nodeItem)
    }
    nodeItem.serialize
    println("Node: " + nodeItem.node)
    
    db.save(nodeItem.node)
  } 
  
  // TODO: Use generics here!!
  def load(id : String) : NodeItem = {
    null
  }
  
  def load[T <: NodeItem](document : ODocument) : T = {
    var className = "org.record.content.model.data." + document.getClassName() + "Data" // TODO: Make this in a better way
    var nodeItem = Class.forName(className).newInstance().asInstanceOf[NodeItem]
    nodeItem.node = document
    nodeItem.deserialize
    return nodeItem.asInstanceOf[T]
  }
  
  def loadRelationships(nodeItem : NodeItem) : List[NodeRelationship] = {
    var edges = db.getOutEdges(nodeItem.node)
    return edges.map(edge => {
    		var name = edge.asInstanceOf[ODocument].field(OGraphDatabase.LABEL)
    		new NodeRelationship(name, new LazyLoadedNodeItem(db.getInVertex(edge), this))
    	}
    ).toList
  }
  
  def saveRelationships(nodeItem: NodeItem, relationships : List[NodeRelationship]) = {
    
    val currentEdges = db.getOutEdges(nodeItem.node)
    
    for ( relationship <- relationships ) {
    	
    }
  }
  
  // TODO: Have different types of relationships??
  def createRelationship(name : String, source : NodeItem, target: NodeItem) {
    var relationship = db.createEdge(source.node, target.node)
    relationship.field(OGraphDatabase.LABEL, name)
    
  }
  
  def getContent(id : String) : Content  = {
    
    // TODO: How to fetch by ID??
	return null
  }
   
  def getPage(path : String, filename : String) : Page = {
    val result = db.queryBySql[Page]("Select from Page where path=? and filename=?", path, filename)
    return result.head
  }
  
  def queryContent(taxonomies : List[Taxonomy]) : List[Content] = {
    return null;
  }
  
  def getContentRoot(name : String) : ContentRoot = {
    var doc = db.getRoot(name)
    if ( doc == null ) {
      var rootData = new ContentRootData
      rootData.name = name
      save(rootData)
      doc = rootData.node
    }
    return load[ContentData](doc).retrieveRuntimeContent[ContentRoot]
  }
  
  def publish(content : Content, zoneName : String) = {
    
  }
 
 
  
}

 