package com.vogonjeltz.slang2.runtime.StandardLib

import com.vogonjeltz.slang2.runtime.StandardLib.Functions._
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  */
abstract class Library {

  def globalMembers: Map[String, SlangInstance]

}

class BasicFunctions extends Library{

  def globalMembers = Map(
    "print" -> new PrintFunction(""),
    "println" -> new PrintFunction(),
    "printc" -> new CustomPrintFunction(),
    "input" -> new InputFunction()
  )

}

object STDLIB {

  lazy val onBuild = List(
    new BasicFunctions()
  )

  lazy val available = List[Library]()

}