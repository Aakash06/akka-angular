package com.codesquad.util

import net.liftweb.json.{DefaultFormats, JNothing, JValue, Serialization, parse => liftParser}

import scala.language.implicitConversions

trait JsonHelper {

  implicit protected val formats = DefaultFormats

  def write[T <: AnyRef](value: T): String = Serialization.write(value)

  def parse(value: String): JValue = liftParser(value)

  implicit protected def extractOrEmptyString(json: JValue): String = {
    json match {
      case JNothing => ""
      case data => data.extract[String]
    }
  }

}

object JsonHelper extends JsonHelper
