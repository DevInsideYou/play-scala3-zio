import Dependencies._
import MyUtil._

ThisBuild / organization := "dev.insideyou"
ThisBuild / scalaVersion := "3.2.1"

lazy val `play-scala3-zio` =
  project
    .in(file("."))
    .settings(commonSettings)
    .aggregate(core, delivery, main)

lazy val core =
  project
    .in(file("01-core"))
    .settings(commonSettings)
    .settings(scala3Settings)
    .settings(dependencies)

lazy val delivery =
  project
    .in(file("02-delivery"))
    .enablePlugins(PlayScala)
    .dependsOn(core % Cctt)
    .settings(commonSettings)
    .settings(scala2Settings)

lazy val main =
  project
    .in(file("03-main"))
    .enablePlugins(PlayScala)
    .dependsOn(delivery % Cctt)
    .settings(commonSettings)
    .settings(scala2Settings)

lazy val scala3Settings =
  Seq(
    scalacOptions ++= Seq(
      "-deprecation",
      "-explain",
      "-feature",
      "-language:implicitConversions",
      "-unchecked",
      "-Xfatal-warnings",
      "-Yexplicit-nulls", // experimental (I've seen it cause issues with circe)
      "-Ykind-projector",
      "-Ysafe-init", // experimental (I've seen it cause issues with circe)
    ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future-migration")
  )

lazy val scala2Settings =
  Seq(
    scalaVersion := "2.13.10",
    scalacOptions ++= Seq(
      "language:_",
      "-Ymacro-annotations",
      "-Ytasty-reader",
    ),
  )

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions --= Seq(
      "-Wunused:_",
      "-Xfatal-warnings",
    ),
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    dev.zio.zio
  ),
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-16`,
  ).map(_ % Test),
)
