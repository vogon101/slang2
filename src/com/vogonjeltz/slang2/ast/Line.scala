package com.vogonjeltz.slang2.ast

import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 10/07/2016.
  */
trait Line {

  def run(): Option[SlangInstance]

}
