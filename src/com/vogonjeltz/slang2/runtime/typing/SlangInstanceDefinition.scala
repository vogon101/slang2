package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.ast.Element

/**
  * Created by fredd on 15/07/2016.
  */
class SlangInstanceDefinition(value: Element) {

  def getInstance = {
    val instance = value.run()
    if (instance.isEmpty)
      throw new Exception (s"Illegal NONE returned from Instance Definition ($value)")
    else instance.get
  }

}
