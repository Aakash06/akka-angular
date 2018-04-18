name := """Blog Analytica"""

version := "1.0-SNAPSHOT"

lazy val scalaV = "2.11.7"

val liftWebVersion = "3.2.0-M3"
val akkaActorVersion = "2.4.11"
val akkaHTTPExpVersion = "2.4.2-RC3"
val akkaHttpCoreVersion = "0.2.2"
val mockitoVersion = "2.11.0"
val scalaTestVersion = "3.0.1"
val logbackVersion = "1.2.3"
val akkaHttpVersion = "10.0.11"


val liftWeb = "net.liftweb" %% "lift-json" % liftWebVersion
val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaActorVersion
val akkaHttpCore = "ch.megard" %% "akka-http-cors" % akkaHttpCoreVersion
val akkaHttpTest = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
val logback = "ch.qos.logback" % "logback-classic" % logbackVersion
val mockito = "org.mockito" % "mockito-core" % mockitoVersion % Test
val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
val akkaHTTPExp = "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaHTTPExpVersion % Test
val json = "org.json" % "json" % "20140107"
val mongo = "org.reactivemongo" %% "reactivemongo" % "0.13.0"

lazy val akkaModule = (project in file("akka-module")).settings(
  scalaVersion in ThisBuild := scalaV,
  scapegoatVersion := "1.3.4",
  scalacOptions ++= Seq("-deprecation", "-Xlint", "-feature"),
  parallelExecution in test := false,
  libraryDependencies ++= Seq(akkaActor, akkaHTTPExp, akkaHttp, liftWeb,
    mockito, scalaTest, akkaHttp, logback, akkaHttpCore, json, mongo)
)

onLoad in Global := (Command.process("project akkaModule", _: State)) compose (onLoad in Global).value