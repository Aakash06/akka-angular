package com.codesquad.util

import org.slf4j.{Logger, LoggerFactory}

trait LoggerUtil {

  val logger: Logger = LoggerFactory.getLogger(this.getClass())

  def info(message: String): Unit = logger.info(message)

  def error(message: String, exception: Throwable): Unit = logger.error(message + " Reason::" + exception.printStackTrace())

  def errorMessage(message: String): Unit = logger.error(message)

}

object LoggerUtil extends LoggerUtil
