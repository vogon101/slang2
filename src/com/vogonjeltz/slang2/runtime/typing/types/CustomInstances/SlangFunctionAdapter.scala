package com.vogonjeltz.slang2.runtime.typing.types.CustomInstances

import com.vogonjeltz.slang2.runtime.Program
import com.vogonjeltz.slang2.runtime.scope.Scope
import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 15/07/2016.
  * @deprecated
  */
class SlangFunctionAdapter(val function : (Scope) => Option[SlangInstance], val _cont: Option[SlangInstance]) extends SlangFunction(null, null, _cont){

  override def runApply(arguments: List[SlangInstance]): Option[SlangInstance] = {

    Program().currentScope.push(this.scope)
    this.scope.push(new Scope())

    val retVal = function(Program().currentScope)

    this.scope.pop()
    Program().currentScope.pop()

    retVal

  }


}
