package dev.insideyou
package playscala3zio
package usecase1

import java.time.LocalTime

import zio.*

trait Boundary:
  def businessLogic(using Trace): UIO[LocalTime]

object Boundary:
  lazy val make: Boundary =
    new:
      override def businessLogic(using Trace): UIO[LocalTime] =
        ZIO.succeed(java.time.LocalTime.now.nn)
