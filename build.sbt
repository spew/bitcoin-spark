name := "bitcoin-spark"

version := "1.0"

scalaVersion := "2.10.4"

// this resolver is needed for Spark
resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.github.scopt" %% "scopt" % "3.2.0",
  "com.google" % "bitcoinj" % "0.11.2",
  "org.apache.spark" %% "spark-core" % "1.0.0"
)
    