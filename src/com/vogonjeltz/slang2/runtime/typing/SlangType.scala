package com.vogonjeltz.slang2.runtime.typing

/**
  * Created by fredd on 10/07/2016.
  */
class SlangType (val name: String) {

  val members = Map[String, SlangInstanceDefinition]()

  def toStringProto(instance: SlangInstance) = {
    s"Slang instance of type $name with members [${instance.scope.variableMap.map(X => s"(${X._1} -> ${X._2.JVMtoString}) ").mkString}]  (${instance.JVMtoString})"

  }
}