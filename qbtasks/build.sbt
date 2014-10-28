name := """qbtasks"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

// Add a resolver
resolvers += "mandubian maven bintray" at "http://dl.bintray.com/mandubian/maven"

resolvers += "QB repository" at "http://dl.bintray.com/qbproject/maven"

// Add these dependencies
val qbSchema        = "org.qbproject"    %% "qbschema"    % "0.4.0-rc7"
val qbPlay          = "org.qbproject"    %% "qbplay"      % "0.4.0-rc7"
val qbForms         = "org.qbproject"    %% "qbforms"     % "0.4.0-rc7"
val qbFormsAssets   = "org.qbproject"    %% "qbforms"     % "0.4.0-rc7" classifier "assets"


libraryDependencies ++= Seq(
  qbSchema,
  qbPlay,
  qbForms,
  qbFormsAssets,
  "com.mandubian"     %% "play-json-zipper"    % "1.2",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.0.akka23"
)
