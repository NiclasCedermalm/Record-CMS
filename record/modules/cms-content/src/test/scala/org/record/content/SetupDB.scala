package org.record.content
import com.orientechnologies.orient.core.db.graph.OGraphDatabase
import com.orientechnologies.orient.core.storage.OStorage

object SetupDB extends App {
	
	// TODO: Relativ path???
	var db = new OGraphDatabase("local:/Users/nic/Develop/orientdb/cms-test");
	if ( ! db.exists() ) {
	  db.create()
	}
	db.open("admin", "admin");
    
	//db.addCluster("stage", OStorage.CLUSTER_TYPE.PHYSICAL)
	
	// Mirror the class structure in the DB
	db.createVertexType("Content")
	db.createVertexType("ConcreteContent", "Content")
	db.createVertexType("TextContent", "ConcreteContent")
	db.createVertexType("ImageContent", "ConcreteContent") 
	db.createVertexType("LinkContent", "ConcreteContent") 
	db.createVertexType("HierarchicalContent", "Content")
	db.createVertexType("ContentRoot", "HierarchicalContent")
	db.createVertexType("RuntimeWiredContent", "Content")
	db.createVertexType("Page", "RuntimeWiredContent")
	db.createVertexType("CompositeContent", "Content")
	
}