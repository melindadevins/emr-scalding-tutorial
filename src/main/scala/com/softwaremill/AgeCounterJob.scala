package com.softwaremill

import com.twitter.scalding._

class AgeCounterJob(args: Args) extends Job(args) {

  val lines: TypedPipe[String] = TypedPipe.from(TextLine(args("input")))

  val tokens: TypedPipe[Token] = lines.flatMap(f => xmlToToken(f))

  val byAge = tokens.groupBy(_.age)
  byAge.size
    .write(TypedTsv[(Int, Long)](args("output")))

  def xmlToToken(content: String): Option[Token] = {
    val namePattern = "DisplayName=\"(.*)\"".r
    val agePattern = "Age=\"([0-9]+)\"".r

    for {
      name <- namePattern.findFirstMatchIn(content).map(m => m.group(1))
      age <- agePattern.findFirstMatchIn(content).map(m => m.group(1))
    } yield Token(name, age.toInt)
  }
}


case class Token(name: String, age: Int)