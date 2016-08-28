package com.vogonjeltz.slang2.runtime.typing.types

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.SlangType

/**
  * Created by fredd on 10/07/2016.
  */
class FunctionType extends SlangType("Function"){

  assert(!FunctionType.hasInstance, s"$name type has already been instantiated")

}

object FunctionType {

  private var _instance: FunctionType = null

  def apply():SlangType = {
    if (_instance == null) _instance = new FunctionType()
    Program().globalScope.getType("Function").get
  }


  def hasInstance = _instance != null

}
