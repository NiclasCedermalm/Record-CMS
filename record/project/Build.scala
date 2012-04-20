import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "record"
    val appVersion      = "1.0"

    val appDependencies = Seq(
/*      "org.scala-lang" % "scala-compiler" % "2.9.1",
      "com.orientechnologies" % "orientdb-core" % "1.0rc9",
      "com.orientechnologies" % "orientdb-client" % "1.0rc9",
      "com.orientechnologies" % "orient-commons" % "1.0rc9"
*/
    )
   
    val cms_content = Project("cms-content", file("modules/cms-content"))
    /*
    val content = PlayProject(
        "cms-content", appVersion, path = file("modules/cms-content")
    )*/

    /*val admin = PlayProject(
    	"cms-admin", appVersion, path = file("modules/cms-admin")
    ).dependsOn(content)
    */
    
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    ).dependsOn(cms_content)
    
    //.aggregate(cms_content)

}
