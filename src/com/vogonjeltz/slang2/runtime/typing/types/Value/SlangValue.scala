package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.typing.{SlangInstance, SlangType}

/**
  * Created by fredd on 15/07/2016.
  */
abstract class SlangValueType(name: String) extends SlangType(name){

}

abstract class SlangValueInstance(slangType : SlangValueType) extends SlangInstance(slangType) {

  val value: Any

}
