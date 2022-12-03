package dev.insideyou
package playscala3zio
package usecase1

import play.api.test.Helpers._
import play.api.test._

final class ControllerSuite extends TestSuite {
  test("Controller GET should render the index page from a new instance of controller") {
    val expected = java.time.LocalTime.now.withSecond(0).withNano(0).toString
    val controller = new Controller(Boundary.make)(stubControllerComponents())
    val index = controller.index(FakeRequest(GET, "/"))

    status(index) shouldBe OK
    contentType(index) shouldBe Some("text/html")
    contentAsString(index) should include(expected) // a bit flaky
  }
}
