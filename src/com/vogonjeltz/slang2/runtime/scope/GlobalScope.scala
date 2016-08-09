package com.vogonjeltz.slang2.runtime.scope

import com.vogonjeltz.slang2.runtime.typing.SlangType

import scala.collection.mutable

/**
  * Created by fredd on 10/07/2016.
  */
class GlobalScope extends Scope{


  private val _types = mutable.HashMap[String, SlangType]()

  def setType(name: String, typ: SlangType) = _types(name) = typ

  def getType(name: String):Option[SlangType] = {
    if (_types.contains(name)) Some(_types(name))
    else None
  }


}
