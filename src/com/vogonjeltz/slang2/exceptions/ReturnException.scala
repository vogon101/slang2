package com.vogonjeltz.slang2.exceptions

import com.vogonjeltz.slang2.ast.Element

/**
  * Created by fredd on 26/07/2016.
  */
class ReturnException(val value : Element) extends Exception (s"Return Statement uncaught - used in incorrect place (value returned $value)") {}
