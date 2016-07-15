package com.vogonjeltz.slang2.ast

import com.vogonjeltz.slang2.ast.elements.Identifier
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.SlangInstance
import com.vogonjeltz.slang2.runtime.typing.types.NoneType

/**
  * Created by fredd on 10/07/2016.
  */
class Assignment(val name: Identifier, val element: Element) extends Line{

  def run(): Option[SlangInstance] = {
    val valueOption = element.run()
    var value: SlangInstance = null
    if (valueOption.isEmpty) value = new SlangInstance(new NoneType())
    else value = valueOption.get

    if (name.split.length == 1) {
      Program().currentScope.set(name.topName, value)
    }
    else {
      val containerInstance = name.containerInstance
      if (containerInstance.isEmpty) throw new Exception(s"Cannot find containing instance (${name.container.value})")
      else containerInstance.get.scope.set(name.topName, value)
    }
    None
  }

}
