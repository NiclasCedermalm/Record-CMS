package controllers

import play.api._
import play.api.mvc._

import play.api.templates.Html

import org.record.content._
import org.record.content.behaviour._
import org.record.content.model._
import org.record.content.model.data._

object Application extends Controller {
  
  def index = Action {
    
    var context = new RenderContext
    
    // Build test content structure
    //
    var testPageData = new PageData
    testPageData.templateFunctionName = "views.html.pagetemplate1" 
    testPageData.title = "Test Page"
    testPageData.path = "/path1"
    testPageData.filename = "test1.html"
    
    println("Page: " + testPageData)  
      
    /*
    var pageTemplate = new PlayTemplateData
    pageTemplate.templateFunctionName = "views.html.pagetemplate1"    
    testPageData.behaviours += pageTemplate
	*/
      
      
    var articleData = new CompositeContentData
    articleData.templateFunctionName = "views.html.articletemplate1" // TEMP FIX
    articleData.children.put("title", new TextContentData("Article Title"))
    articleData.children.put("body", new TextContentData("<p>Some paragraphs comes here...</p><p>Plus some more comes here</p>"))
    
    testPageData.children += articleData
    
    //var article = new CompositeContent(articleData)
   
   
    //article.render("", context)
    
    /*
    var articlePresentationData
    var articlePresentation = new RuntimeWiredContent with PlayTemplate
    articlePresentation.content = article
    articlePresentation.target = article // TODO: Make this in a cleaner way using implicits
    articlePresentation.templateFunctionName = "views.html.articletemplate1"
    
    // Regions? Just some different types of containers I suppose??
    testPage.children.add(articlePresentation)
    */
    
    var testPage = testPageData.retrieveRuntimeContent[Page]
    println("Created test page: " + testPage)
    
    //var testPage = new Page(testPageData) with PlayTemplate
    //testPage.templateFunctionName = "views.html.pagetemplate1" // TEMP
    
    //val output = testPage.asInstanceOf[Renderer].render(context).asInstanceOf[Html] 
    val output = testPage.render(context).asInstanceOf[Html]
    
    //val output = testPage.render(context).asInstanceOf[Html]
    Ok(output)
    
    //Ok(views.html.testpage(testPage))
    //Ok(views.html.index("Your new application is ready."))

  }
   
}