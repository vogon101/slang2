package com.vogonjeltz.slang2.runtime.typing.types.CustomInstances

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.{ContainableDefinition, SlangInstance}

/**
  * Created by fredd on 15/07/2016.
  */
class ScalaFunctionAdapter(val f : (Scope) => Option[SlangInstance], val argumentNames: List[String], _cont : Option[SlangInstance] = None, val name: String = "Anon")
  extends SlangFunction(null, null, _cont) with ContainableDefinition {

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    assert(arguments.length == argumentNames.length, s"Wrong number of arguments for ScalaFunctionAdapter ($name)")

    Program().currentScope.push(this.scope)

    val runScope = new Scope()

    arguments.zipWithIndex.foreach(X => runScope.set(argumentNames(X._2), X._1))

    scope.push(runScope)

    val retval = f(Program().currentScope)

    scope.pop()

    Program().currentScope.pop()

    retval

  }



  def run(container: Option[SlangInstance]) = Some(new ScalaFunctionAdapter(f, argumentNames, container, name))

  def run() = Some(this)

}
