package com.vogonjeltz.slang2.runtime.scope

import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 10/07/2016.
  */
class Scope {

  private var _variableMap = Map[String, SlangInstance]()

  def variableMap = _variableMap

  def get(name: String): Option[SlangInstance] =
    if (variableMap.contains(name)) Some(variableMap(name))
    else None

  def set(name: String, obj: SlangInstance) = {
    _variableMap = variableMap + (name -> obj)
  }

  def has(name: String) = variableMap.contains(name)

  def load(definitions: Map[String, SlangInstance]) =
    definitions.foreach(X => set(X._1, X._2))

  override def toString = s"Scope with variables : ${_variableMap}"

}