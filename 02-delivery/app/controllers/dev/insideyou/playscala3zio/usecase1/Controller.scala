package dev.insideyou
package playscala3zio
package usecase1

import play.api.mvc._
import zio._

final class Controller(
    boundary: Boundary
  )(implicit
    override val controllerComponents: ControllerComponents
  ) extends BaseController {
  lazy val index =
    Action.asyncZIO {
      boundary.businessLogic.map { time =>
        Ok(views.html.index(time.toString))
      }
    }
}
