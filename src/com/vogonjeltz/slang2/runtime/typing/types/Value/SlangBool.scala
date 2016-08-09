package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 27/07/2016.
  */
class SlangBoolInstance(val value: Boolean) extends SlangValueInstance(SlangBoolType()){

}

//TODO: Implement booleans
class SlangBoolType extends SlangValueType("Boolean"){

  if (SlangBoolType.hasInstance) throw new Exception("Cannot create SlangBoolType")
  Program().globalScope.setType(name, this)

  override def create(args: List[SlangInstance]) =
    if (args.length != 1) throw new Exception("Wrong number of arguments for String constructor")
    else if (args.head.slangType==this) args.head match {
      case string: SlangStringInstance => new SlangStringInstance(string.value)
      case _ => throw new Exception("String constructor requires String argument")
    }
    else throw new Exception("String constructor requires String argument")

}

object SlangBoolType {

  private var _instance: SlangBoolType = null

  def apply() = {
    if (_instance == null) _instance = new SlangBoolType()
    _instance
  }

  def hasInstance = _instance != null

}
//TODO: Singleton for all types