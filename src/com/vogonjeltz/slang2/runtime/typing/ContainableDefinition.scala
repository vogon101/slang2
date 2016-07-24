package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.ast.Element

/**
  * Created by fredd on 24/07/2016.
  */
trait ContainableDefinition extends Element{

  def run(container: Option[SlangInstance]): Option[SlangInstance]

}
