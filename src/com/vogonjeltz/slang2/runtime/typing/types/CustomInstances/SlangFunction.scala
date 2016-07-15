package com.vogonjeltz.slang2.runtime.typing.types.CustomInstances

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.SlangInstance
import com.vogonjeltz.slang2.runtime.typing.types.FunctionType

/**
  * Created by fredd on 10/07/2016.
  */
class SlangFunction(element: Element, argumentNames: List[String]) extends SlangInstance(new FunctionType()){

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    //println("Running function")

    if (arguments.length < argumentNames.length) throw new Exception(s"Not enough arguments for anonymous function [required ${argumentNames.mkString(",")} | got ${arguments.length}]")
    else if (arguments.length > argumentNames.length) throw new Exception(s"Too many arguments for anonymous function [required ${argumentNames.mkString(",")} | got ${arguments.length}]")

    Program().currentScope.push(this.scope)
    //println(scope)
    scope.push(new Scope())

    argumentNames.zip(arguments).foreach(X => scope.top.set(X._1, X._2))
    //println(argumentNames.zip(arguments))

    //println(Program().currentScope)


    val retVal = element.run()

    this.scope.pop()
    Program().currentScope.pop()

    retVal

  }

}

