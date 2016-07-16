package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.{ScalaFunctionAdapter, SlangInstance, SlangInstanceDefinition}

/**
  * Created by fredd on 15/07/2016.
  */
class SlangStringType extends SlangValueType("String"){

  override val members = Map[String, SlangInstanceDefinition](
    "add" -> new SlangInstanceDefinition(new ScalaFunctionAdapter((args : List[SlangInstance]) => {
      println (Program().currentScope.get("this"))
      None
    }))
  ) ++ super.members

}

class SlangStringInstance(override val value : String) extends SlangValueInstance(new SlangStringType()) {

  override def toString = value

}
