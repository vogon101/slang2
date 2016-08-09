package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.SlangType

/**
  * Created by fredd on 01/08/2016.
  */
class Instantiation(slangTypeName: String, arguments: List[Element]) extends Element{

  def run() = {

    val slangTypeOpt = Program().globalScope.getType(slangTypeName)
    if(slangTypeOpt.isDefined) {
      val slangType = slangTypeOpt.get
      Some(slangType.create(arguments))
    }
    else {
      throw new Exception(s"Cannot find type $slangTypeName")
    }

  }

}
