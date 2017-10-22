name := "emr-scalding-tutorial"
version := "0.1"
scalaVersion := "2.11.1"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"
libraryDependencies ++= Seq(
  "com.twitter" %% "scalding-core" % "0.16.0" exclude("com.esotericsoftware.minlog", "minlog"),
  "org.apache.hadoop" % "hadoop-core" % "1.2.1" % "provided"
)
