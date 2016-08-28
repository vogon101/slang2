package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.{ScalaFunctionAdapter}
import com.vogonjeltz.slang2.runtime.typing.types.FunctionType
import com.vogonjeltz.slang2.runtime.typing.types.Value.SlangStringInstance

/**
  * Created by fredd on 10/07/2016.
  */
class SlangType (val name: String, protected var _members: Map[String, SlangInstanceDefinition] = Map()) {

  assert(!Program().globalScope.hasType(name), s"Type $name already defined")
  Program().globalScope.setType(name, this)

  /*
  _members += ("toString" -> new SlangInstanceDefinition(
    new ScalaFunctionAdapter(
      (s: Scope) => {
        val me: SlangInstance = s.get("this").get
        new Some(new SlangStringInstance(me.toString))
      }, List(), None, "toString")
    )
  )
  */

  //This allows subclasses to use this
  def members = _members



  def toStringProto(instance: SlangInstance) = {
    s"Slang instance of type $name with members [${instance.scope.variableMap.map(X => s"(${X._1} -> ${X._2.JVMtoString}) ").mkString}]  (${instance.JVMtoString})"
  }

  def create(args: List[SlangInstance] = List()): SlangInstance = {
    val inst = new SlangInstance(this)
    val constructorOpt = inst.scope.get("__constructor")
    if (constructorOpt.isDefined) {
      val constructor = constructorOpt.get
      if (constructor.slangType == FunctionType())
        constructor.runApply(args)
      else throw new Exception(s"Constructor for $name is not a function")
    }
    inst
  }

}