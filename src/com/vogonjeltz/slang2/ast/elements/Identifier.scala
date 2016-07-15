package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 10/07/2016.
  */
class Identifier(val value: String) extends Element {

  lazy val split = value.split('.').toList

  lazy val container = new Identifier(split.take(split.length - 1).mkString("."))

  lazy val topName = split.last

  def containerInstance = find(1)

  def run(): Option[SlangInstance] = find(0)

  def find(offset: Int = 0) : Option[SlangInstance] = {

    //println(s"Searching for $split with offset $offset")

    var lastInstance: Option[SlangInstance] = None
    if (split.length < 1 || split.length <= offset) return None

    for (i <- 0 until split.length - offset) {
      if (lastInstance.isEmpty) lastInstance = Program().currentScope.get(split(i))
      else lastInstance = lastInstance.get.scope.get(split(i))

      //println(i)
      //println(split(i))
      //println(lastInstance)

      if (lastInstance.isEmpty)
        if (i == 0) throw new Exception(s"Cannot find ${split(i)}")
        else throw new Exception(s"Cannot find ${split(i)} in ${split(i-1)}")
      else if (i == split.length - (offset + 1)) return lastInstance

    }
    None

  }

}
