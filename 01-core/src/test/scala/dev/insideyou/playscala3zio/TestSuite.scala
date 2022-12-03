package dev.insideyou
package playscala3zio

import org.scalatest.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

trait TestSuite extends AnyFunSuite, should.Matchers, GivenWhenThen, ScalaCheckPropertyChecks:
  final protected type Arbitrary[A] =
    org.scalacheck.Arbitrary[A]

  final protected val Arbitrary: org.scalacheck.Arbitrary.type =
    org.scalacheck.Arbitrary

  final protected type Assertion =
    org.scalatest.compatible.Assertion

  final protected type Gen[+A] =
    org.scalacheck.Gen[A]

  final protected val Gen: org.scalacheck.Gen.type =
    org.scalacheck.Gen
