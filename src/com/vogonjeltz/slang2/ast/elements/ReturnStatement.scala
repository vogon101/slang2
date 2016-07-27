package com.vogonjeltz.slang2.ast.elements
import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.exceptions.ReturnException
import com.vogonjeltz.slang2.runtime.typing.types.Value.SlangStringInstance

/**
  * Created by fredd on 26/07/2016.
  */
class ReturnStatement(element: Element) extends Element{

  def run() = {
    //println("RUNNING THE RETURN STATEMENT")
    throw new ReturnException(element)
    //Some(new SlangStringInstance("NONE"))
  }

}
