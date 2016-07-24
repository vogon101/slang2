package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.ScalaFunctionAdapter
import com.vogonjeltz.slang2.runtime.typing.{SlangInstance, SlangInstanceDefinition}

/**
  * Created by fredd on 15/07/2016.
  */
class SlangStringType extends SlangValueType("String"){

  override val members = Map[String, SlangInstanceDefinition](
    "+" -> new SlangInstanceDefinition(new ScalaFunctionAdapter((scope: Scope) => {
      val _me = scope.get("this")
      val _that = scope.get("other")

      if (_me.isDefined && _that.isDefined) {
        val me = _me.get
        val that = _that.get
        if (me.slangType.name == this.name && that.slangType.name == this.name) {
          Some(new SlangStringInstance(me.asInstanceOf[SlangStringInstance].value + that.asInstanceOf[SlangStringInstance].value))
        }
        else {
          throw new Exception("Cannot add non string")
        }
      }
      else {
        throw new Exception("Major error in string add function - This should never happen")
      }

    }, List("other"), None, "String add function"))
  ) ++ super.members

}

class SlangStringInstance(override val value : String) extends SlangValueInstance(new SlangStringType()) {

  override def toString = value

}
