package dev.insideyou
package playscala3zio

import play.api._

final class Main extends ApplicationLoader {
  override def load(context: ApplicationLoader.Context): Application =
    new Program(context).application
}
