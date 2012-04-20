name := "cms-content"

version := "1.0"
 
scalaVersion := "2.9.1"

libraryDependencies ++= 
  Seq(
  	  "org.scala-lang" % "scala-compiler" % "2.9.1",
      "com.orientechnologies" % "orientdb-core" % "1.0rc9",
      "com.orientechnologies" % "orientdb-client" % "1.0rc9",
      "com.orientechnologies" % "orient-commons" % "1.0rc9",
      "play" % "play_2.9.1" % "2.0",
      "org.specs2" %% "specs2" % "1.9" % "test"
  )

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                   "releases"  at "http://oss.sonatype.org/content/repositories/releases",
                   "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")
