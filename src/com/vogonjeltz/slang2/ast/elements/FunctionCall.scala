package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by Freddie on 11/07/2016.
  */
class FunctionCall(function: Element, arguments: List[Element]) extends Element {

  def run(): Option[SlangInstance] = {
    val func = function.run()
    if (func.isEmpty) throw new Exception(s"Function not found")
    else func.get.runApply(
      arguments.map(_ run())
        .map(
          X => if (X.isEmpty) throw new Exception("Argument was not defined")
          else X.get
        ))
  }

}
