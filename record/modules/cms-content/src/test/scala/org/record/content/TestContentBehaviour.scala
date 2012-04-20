package org.record.content

import org.record.content.util.DynamicTraitFactory
import org.record.content.model.TextContent
import org.record.content.behaviour.Renderer
import org.record.content.model.Page
import org.record.content.model.data._


//class Test(p1 : String ,p2 : String) extends TextContent(p1,p2) {}

object TestContentBehaviour extends App {

  var page = new TextContent(new TextContentData) with TestRenderer;
  page.render(new RenderContext)
  
  // Test create traits with reflection
  //
  var page2 = DynamicTraitFactory.newInstance(classOf[TextContent], Array(new TextContentData), List("org.record.content.TestRenderer"))
  var renderer = page2.asInstanceOf[Renderer]
  renderer.render(new RenderContext)
  
  var page3 = DynamicTraitFactory.newInstance(classOf[TextContent], Array(new TextContentData), List("org.record.content.TestRenderer"))
  renderer = page3.asInstanceOf[Renderer]
  renderer.render(new RenderContext)
  
  // Test render directly via implicit method
  var page4 = new Page(new PageData) with TestRenderer
  page4.render(new RenderContext)
 
  
  // Test page
  //
  var testPageData = new PageData
 
    testPageData.title = "Test Page";
    var pageTemplate = new PlayTemplateData
    pageTemplate.templateFunctionName = "views.html.pagetemplate1"    
    testPageData.behaviours += pageTemplate
    
    var testPage = testPageData.retrieveRuntimeContent[Page]
  
}