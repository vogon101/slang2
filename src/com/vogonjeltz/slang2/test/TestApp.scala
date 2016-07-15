package com.vogonjeltz.slang2.test

import com.vogonjeltz.slang2.parsing.SLangParser
import com.vogonjeltz.slang2.runtime.StandardLib.Functions.PrintFunction

/**
  * Created by fredd on 10/07/2016.
  */
object TestApp extends App{

  val testText =
    """
      |testFunction = () => {print("Name")}
      |testFunction2 = (value) => {print(value)}
      |testFunction3 = (greeting) => {
      | function = (name) => {print(greeting); print(name)}
      | print(function)
      | function.greeting = greeting
      | print(function)
      | function
      |}
      |name = "Freddie"
      |print(name)
      |testFunction()
      |testFunction2("abcd")
      |testFunction3("greetings")("mortal")
    """.stripMargin

  val parser = new SLangParser()
  val parsed = parser.parseAll(parser.program, testText)
  println(parsed)
  val program = parsed.get

  program.globalScope.set("print", new PrintFunction())

  //println(parsed.get.lines(0).asInstanceOf[Assignment].element.asInstanceOf[Identifier].topName)

  try{
    program.run()
  }
  catch {
    case e : Exception => e.printStackTrace()
  }

  //println(function1)
  //println(program.globalScope.get("name"))

}
