package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.SlangFunction

/**
  * Created by fredd on 15/07/2016.
  */
class FunctionDefinition(argumentNames: List[String], code: Element) extends Element{

  def run() = Some(new SlangFunction(code, argumentNames))

}
