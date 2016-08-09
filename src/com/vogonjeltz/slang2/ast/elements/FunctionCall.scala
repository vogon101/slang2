package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.exceptions.ReturnException
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by Freddie on 11/07/2016.
  */
class FunctionCall(val function: Element, val arguments: List[Element]) extends Element {

  def run(): Option[SlangInstance] = {
    val func = function.run()
    if (func.isEmpty) throw new Exception(s"Function not found")
    else try
      func.get.runApply(
      arguments.map(_ run())
        .map(
          //TODO: Maybe just substitute in a NONE?
          //TODO: Optional Arguments
          X => if (X.isEmpty) throw new Exception("Argument was not defined")
          else X.get
        ))
    catch {
      //TODO: Will this cause issues with stuff
      case e: ReturnException => e.value.run()
    }
  }

}
