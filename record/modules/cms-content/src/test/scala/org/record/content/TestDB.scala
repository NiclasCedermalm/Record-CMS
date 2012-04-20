package org.record.content
import com.orientechnologies.orient.core.Orient
import com.orientechnologies.orient.client.remote.OEngineRemote
import com.orientechnologies.orient.core.db.graph.OGraphDatabase
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import org.record.content.model.data._

object TestDB {

  def main(args: Array[String]): Unit = {

    //Orient.instance().registerEngine(new OEngineRemote());
    //var db = new OGraphDatabase("remote:/cms-db");
    var db = new OGraphDatabase("local:/Users/nic/Develop/orientdb/cms-test");
    db.open("admin", "admin");

    var contentRepo = new ContentRepository(db)

    var root = contentRepo.getContentRoot("stage")

    var page1 = new PageData
    page1.filename = "filename.html"
    page1.path = "/path1"
    contentRepo.save(page1)

    /*
     *   var page1 = new PageData
  page1.filename = "filename.html"
  page1.path = "/path1"
  db.save(page1)

  var text1 = new TextContentData
  text1.text = "Tjabba"
  text1.parent = root.contentData.parent
  db.save(text1)
  
  var article1 = new CompositeContentData
  article1.children.put("text1", text1)
  db.save(article1)
  
  var template1 = new BehaviourData
  template1.traitClassName = classOf[PlayTemplate].getCanonicalName()
  db.save(template1)
  
  var compPres1 = new RuntimeWiredContentData
  compPres1.content = article1 // This does not work!!!!
  compPres1.behaviours += template1
  db.save(compPres1)
  
  page1.children.add(compPres1)
  db.save(page1)
  
  root.contentData.children += page1
  db.save(root.contentData)  
     */
    
    
    db.close();

  }

}