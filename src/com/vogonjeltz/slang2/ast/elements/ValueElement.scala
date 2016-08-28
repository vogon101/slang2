package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.types.Value.{SlangFloatInstance, SlangIntInstance, SlangStringInstance, SlangValueInstance}

/**
  * Created by fredd on 15/07/2016.
  */
abstract class ValueElement extends Element{

  override def run() : Option[SlangValueInstance]

}

class StringElement(string: String) extends ValueElement {

  def run() = Some(new SlangStringInstance(string))

}

abstract class NumberElement extends ValueElement

class IntElement(int: Int) extends NumberElement {

  def run() = Some(new SlangIntInstance(int))

}

class FloatElement(float: Float) extends NumberElement {

  def run() = Some(new SlangFloatInstance(float))

}
