package com.vogonjeltz.slang2.runtime.typing.types.Value

/**
  * Created by fredd on 28/08/2016.
  */
//INTS
class SlangIntType extends SlangValueType("Int"){

}

object SlangIntType {
  private var _instance: SlangIntType = null

  def apply() = {
    if (_instance == null) _instance = new SlangIntType()
    _instance
  }

  def hasInstance = _instance != null
}

class SlangIntInstance(val value: Int) extends SlangValueInstance(SlangIntType()){

}

//FLOATS
class SlangFloatType extends SlangValueType("Float") {

}

object SlangFloatType {
  private var _instance: SlangFloatType = null

  def apply() = {
    if (_instance == null) _instance = new SlangFloatType()
    _instance
  }

  def hasInstance = _instance != null
}

class SlangFloatInstance(val value: Float) extends SlangValueInstance(SlangFloatType()) {

}


