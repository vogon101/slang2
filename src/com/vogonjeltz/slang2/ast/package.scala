package com.vogonjeltz.slang2

import com.vogonjeltz.slang2.runtime.typing.SlangInstance

/**
  * Created by fredd on 05/08/2016.
  */
package object ast {

  implicit def elementListToInstanceList(list: List[Element]): List[SlangInstance] = {

    list.map(_ run()).map( X =>
      if (X.isDefined) X.get
      else throw new Exception("Element in list returns no SlangInstance")
    )

  }

}
