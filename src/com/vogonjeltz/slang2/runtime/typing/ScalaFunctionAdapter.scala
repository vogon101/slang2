package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.{SlangFunction, SlangFunctionAdapter}
import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope

/**
  * Created by fredd on 15/07/2016.
  */
class ScalaFunctionAdapter(val f : (List[SlangInstance]) => Option[SlangInstance], _cont : Option[SlangInstance] = None) extends SlangFunction(null, null, _cont) with Element {

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    Program().currentScope.push(this.scope)

    scope.push(new Scope())

    val retval = f(arguments)

    scope.pop()

    Program().currentScope.pop()

    retval

  }



  def run(container: Option[SlangInstance] = None) = new SlangFunctionAdapter(f, container)

  def run() = run(None)

}
