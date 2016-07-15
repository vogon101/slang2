package com.vogonjeltz.slang2.runtime.typing.types.Value

/**
  * Created by fredd on 15/07/2016.
  */
class SlangStringType extends SlangValueType("String"){

}

class SlangStringInstance(override val value : String) extends SlangValueInstance(new SlangStringType()) {

  override def toString = value

}
