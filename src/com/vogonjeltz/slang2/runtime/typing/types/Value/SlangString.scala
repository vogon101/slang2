package com.vogonjeltz.slang2.runtime.typing.types.Value

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.ScalaFunctionAdapter
import com.vogonjeltz.slang2.runtime.typing.{SlangInstance, SlangInstanceDefinition}

/**
  * Created by fredd on 15/07/2016.
  */
class SlangStringType extends SlangValueType("String"){

  assert(!SlangStringType.hasInstance, "Instance already created")

  _members ++= Map[String, SlangInstanceDefinition](
    "+" -> new SlangInstanceDefinition(new ScalaFunctionAdapter((scope: Scope) => {
      val _me = scope.get("this")
      val _that = scope.get("other")

      if (_me.isDefined && _that.isDefined) {
        val me = _me.get
        val that = _that.get
        if (me.slangType == this && that.slangType == this) {
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
  )

  override def create(args: List[SlangInstance]) =
    if (args.length != 1) throw new Exception("Wrong number of arguments for String constructor")
    else if (args.head.slangType==this) args.head match {
        case string: SlangStringInstance => new SlangStringInstance(string.value)
        case _ => throw new Exception("String constructor requires String argument")
      }
    else throw new Exception("String constructor requires String argument")

}

object SlangStringType {

  private var _instance: SlangStringType = null

  def apply() = {
    if (_instance == null) _instance = new SlangStringType()
    _instance
  }


  def hasInstance = _instance != null

}

class SlangStringInstance(override val value : String) extends SlangValueInstance(SlangStringType()) {

}
