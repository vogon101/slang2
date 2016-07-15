package com.vogonjeltz.slang2.runtime.StandardLib.Functions

import com.vogonjeltz.slang2.runtime.typing.SlangInstance
import com.vogonjeltz.slang2.runtime.typing.types.CustomInstances.SlangFunction
import com.vogonjeltz.slang2.runtime.typing.types.Value.SlangStringInstance

import scala.io.StdIn

/**
  * Created by Freddie on 11/07/2016.
  */
class PrintFunction(end: String = "\n") extends SlangFunction(null, null){

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    //TODO: Support all control codes
    arguments.map(_.toString() + end).map(_.replace("\\n", "\n")).foreach(print)

    None
  }

}

class CustomPrintFunction extends PrintFunction {

  override def runApply(arguments: List[SlangInstance]) : Option[SlangInstance] = {

    if (arguments.length < 1) throw new Exception("Not enough arguments passed to custom print function")
    else arguments.tail.map(_.toString + arguments.head.toString) foreach print

    None

  }

}

class InputFunction extends SlangFunction(null, null) {

  override def runApply(arguments: List[SlangInstance]) : Option[SlangInstance] =
    if (arguments.length > 1) throw new Exception(s"Too many arguments for input function (max. 1, got ${arguments.length}")
    else if (arguments.length == 1) Some(new SlangStringInstance(StdIn.readLine(arguments.head.toString)))
    else Some(new SlangStringInstance(StdIn.readLine()))

}
