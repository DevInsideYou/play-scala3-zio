package dev.insideyou
package playscala3zio

import controllers.AssetsComponents
import play.api._
import play.api.mvc.ControllerComponents
import play.filters.HttpFiltersComponents
import router.Routes

final class Program(context: ApplicationLoader.Context)
    extends BuiltInComponentsFromContext(context)
       with HttpFiltersComponents
       with AssetsComponents {
  override lazy val router: Routes = {
    implicit lazy val c: ControllerComponents =
      controllerComponents

    new Routes(
      httpErrorHandler,
      usecase1.DI.controller,
      assets,
    )
  }
}
