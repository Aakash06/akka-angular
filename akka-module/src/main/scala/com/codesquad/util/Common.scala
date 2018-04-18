package com.codesquad.util

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date

trait Common {

  def md5(input: String): String = {
      val digest: MessageDigest = MessageDigest.getInstance("MD5")
      val byte = 16
      digest.update(input.getBytes, 0, input.length)
      new BigInteger(1, digest.digest).toString(byte)
    }

  def formatTime(time: Long): String = {
    val sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss")
    sdf.format(new Date(time))
  }

  def getRandomUUID: String = java.util.UUID.randomUUID().toString

  def escapeBadCharacterInXML(s: String): String = s.replaceAll("&", "&amp;").replaceAll(">\"", "&gt;\"").replaceAll("\"<", "\"&lt;").replaceAll("'", "&apos;")
}

object Common extends Common
