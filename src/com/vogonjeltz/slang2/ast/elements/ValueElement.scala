package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.types.Value.{SlangStringInstance, SlangValueInstance}

/**
  * Created by fredd on 15/07/2016.
  */
abstract class ValueElement extends Element{

  override def run() : Option[SlangValueInstance]

}

class StringElement(string: String) extends ValueElement {

  def run() = Some(new SlangStringInstance(string))

}
