package dev.insideyou
package playscala3zio
package usecase1

object DI {
  def controller(implicit c: ControllerComponents) =
    new Controller(Boundary.make)
}
