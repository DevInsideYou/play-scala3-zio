package dev.insideyou

import play.api.mvc._
import zio._

package object playscala3zio {
  type ControllerComponents = play.api.mvc.ControllerComponents

  final implicit class ActionOps(private val self: ActionBuilder[Request, AnyContent])
      extends AnyVal {
    def asyncZIO(zio: UIO[Result]): Action[AnyContent] =
      asyncZIO(_ => zio)

    def asyncZIO(f: Request[AnyContent] => UIO[Result]): Action[AnyContent] =
      self.async { request =>
        Unsafe.unsafe { implicit unsafe =>
          Runtime.default.unsafe.runToFuture(f(request))
        }
      }
  }
}
