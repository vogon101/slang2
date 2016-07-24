package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.{ContainableDefinition, SlangInstance}
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.SlangFunction

/**
  * Created by fredd on 15/07/2016.
  */
class FunctionDefinition(argumentNames: List[String], code: Element) extends ContainableDefinition{

  /**
    * Special case
    * @param containerInstance
    * @return
    */
  def run(containerInstance : Option[SlangInstance]) = Some(new SlangFunction(code, argumentNames, containerInstance))

  def run() = run(None)

}
