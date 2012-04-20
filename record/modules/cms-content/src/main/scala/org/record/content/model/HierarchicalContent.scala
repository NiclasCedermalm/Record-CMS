package org.record.content.model

import scala.collection.mutable.ListBuffer
import org.record.content.model.data.HierarchicalContentData

class HierarchicalContent(override val contentData : HierarchicalContentData) extends Content(contentData) {

  // TODO: Possible to render? 
  
  val name = contentData.name
  
  println("Children for ID: " + contentData.id + ", " + contentData.children)
  val children : List[Content] = getChildren
 
  private def getChildren : List[Content] = {
    if ( contentData.children == null ) return List[Content]()
    return contentData.children.toList.map(item => item.retrieveRuntimeContent.asInstanceOf[Content])
  }
}
