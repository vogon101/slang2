package com.vogonjeltz.slang2.runtime.typing.types.CustomInstances

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  */
class SlangFunctionAdapter(val function : (Scope) => Option[SlangInstance]) extends SlangFunction(null, null){

  def this(function : () => Option[SlangInstance]) = this( (s: Scope) => function() )

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    Program().currentScope.push(this.scope)
    this.scope.push(new Scope())

    val retVal = function(Program().currentScope)

    this.scope.pop()
    Program().currentScope.pop()

    retVal

  }


}
