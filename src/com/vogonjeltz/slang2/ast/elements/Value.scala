package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by Freddie on 11/07/2016.
  */
class Value(value: SlangInstance) extends Element{

  def run() = Some(value)

}
