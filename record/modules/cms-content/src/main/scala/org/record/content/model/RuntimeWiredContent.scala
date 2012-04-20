package org.record.content.model

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import java.util.ArrayList
import org.record.content.model.data.RuntimeWiredContentData

class RuntimeWiredContent(override val contentData : RuntimeWiredContentData) extends Content(contentData) {

  // TODO: implicit magic to fix target on templates??

  
  val content: Content = {   
   if ( contentData.content != null ) {
     contentData.content.retrieveRuntimeContent
   }
   else {
     null
   }
  }
  
  val children: List[Content] = { 
    if ( contentData.children == null) null
    contentData.children.toList.map(child => child.retrieveRuntimeContent)
  }
  
}