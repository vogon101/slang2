package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 27/07/2016.
  */
class SlangBoolInstance(val value: Boolean) extends SlangValueInstance(new SlangBoolType){

}

class SlangBoolType extends SlangValueType("Boolean"){

}
//TODO: Singleton for all types