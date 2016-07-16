package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.ast.elements.FunctionDefinition

/**
  * Created by fredd on 15/07/2016.
  */
class SlangInstanceDefinition(value: Element) {

  def getInstance(container: Option[SlangInstance] = None) = {
    val instance = value match {
      case v: FunctionDefinition => v.run(container)
      case v => v.run()
    }
    if (instance.isEmpty)
      throw new Exception (s"Illegal NONE returned from Instance Definition ($value)")
    else {
      instance.get
    }
  }

}
