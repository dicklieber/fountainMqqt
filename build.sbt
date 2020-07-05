
name := "mqtt-mongodb"

version := "0.0.1"

scalaVersion := "2.13.3"
resolvers += "MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases/"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "org.eclipse.paho" % "mqtt-client" % "0.4.0",
  "org.mongodb" % "mongodb-driver-sync" % "4.0.4"
)