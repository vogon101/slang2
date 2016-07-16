package com.vogonjeltz.slang2.ast.elements

import com.vogonjeltz.slang2.ast.Element
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  */
class CompositeIdentifier(val cont : Element, val name: String) extends Identifier(null) {

  override def find(offset: Int = 0) : Option[SlangInstance] = {

    val contInstance = cont.run()
    if (contInstance.isEmpty) throw new Exception(s"Cannot get $name on None from anon. element (AST $container)")

    contInstance.get.scope.get(name)

  }

}
