package com.vogonjeltz.slang2.test

import java.io.FileNotFoundException

import com.vogonjeltz.slang2.parsing.SLangParser

import scala.io.{Source, StdIn}

/**
  * Created by fredd on 15/07/2016.
  */
object SimpleFileTest extends App {

  while(true) {
    val path = "Examples\\" + StdIn.readLine( ">>" ) + ".sl2"
    var text = ""
    try {
      text = Source.fromFile( path ).mkString
    }
    catch {
      case e:FileNotFoundException =>
    }
    if (text != "") {
      val p = new SLangParser()
      val parsed = p.parseAll(p.program, text)
      println(parsed)
      if (!parsed.isEmpty){
        println(parsed.get)
        //parsed.get.debug()
        parsed.get.run()
      }
    }
  }

}
