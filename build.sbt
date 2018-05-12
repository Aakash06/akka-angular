name := """Blog Analytica"""

version := "1.0-SNAPSHOT"

lazy val scalaV = "2.12.3"

val liftWebVersion = "3.2.0-M3"
val akkaActorVersion = "2.5.12"
val akkaHttpCoreVersion = "0.3.0"
val mockitoVersion = "2.11.0"
val scalaTestVersion = "3.0.1"
val logbackVersion = "1.2.3"
val akkaHttpVersion = "10.1.1"


val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaActorVersion
val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.11"
val akkaHttpTest = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
val mockito = "org.mockito" % "mockito-core" % mockitoVersion % Test
val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
val mongo = "org.reactivemongo" %% "reactivemongo" % "0.13.0"
val akkaslf= "com.typesafe.akka" %% "akka-slf4j" % "2.5.12"
val akkaCors= "com.typesafe.akka" %% "akka-http-core" % "10.1.1"
val json = "com.typesafe.play" %% "play-json" % "2.6.9"
val mongoJson = "org.reactivemongo" %% "reactivemongo-play-json" % "0.13.0-play26"


lazy val akkaModule = (project in file("akka-module")).settings(
  scalaVersion in ThisBuild := scalaV,
  scalacOptions ++= Seq("-deprecation", "-Xlint", "-feature"),
  parallelExecution in test := false,
  libraryDependencies ++= Seq(akkaActor, akkaHttp,
    mockito, scalaTest, akkaHttp, logback, mongo, akkaslf, akkaStream, akkaCors, json, mongoJson)
)

onLoad in Global := (Command.process("project akkaModule", _: State)) compose (onLoad in Global).value