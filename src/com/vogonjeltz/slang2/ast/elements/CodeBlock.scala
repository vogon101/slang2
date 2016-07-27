package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.{Element, Line}
import com.vogonjeltz.slang2.exceptions.ReturnException
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  */
class CodeBlock(lines : List[Line]) extends Element {

  def run() : Option[SlangInstance] = {

    var lastResult : Option[SlangInstance] = None
    try {
      lines.foreach(X => lastResult = X.run())
    } catch {
        //Catch a return statement and just return the value
        case e: ReturnException => return e.value.run()
    }
    //println(lastResult)
    lastResult
  }

}
