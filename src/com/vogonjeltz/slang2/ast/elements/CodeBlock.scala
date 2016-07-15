package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.{Element, Line}
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  */
class CodeBlock(lines : List[Line]) extends Element {

  def run() = {

    //TODO: Return statement
      // * Check if element is of type return, if so exit the function
      // * What about if statements???

    var lastResult : Option[SlangInstance] = None
    lines.foreach(X => lastResult = X.run())
    lastResult
  }

}
