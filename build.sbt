name := "NotiLytics"
organization := "com.notilytics"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "3.3.6"

libraryDependencies += guice

// Additional dependencies for NotiLytics
// Play Framework 3 uses different package names
libraryDependencies += "org.playframework" %% "play-ws" % "3.0.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3"
libraryDependencies += "junit" % "junit" % "4.13.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "4.6.1" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.23.1" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "4.2.0" % Test

Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

// JaCoCo configuration
jacocoReportSettings := JacocoReportSettings()
  .withFormats(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)

jacocoExcludes := Seq(
  "views.*",
  "controllers.javascript.*",
  "controllers.routes.*",
  "controllers.routes.javascript.*",
  "router.*",
  "*.routes*"
)