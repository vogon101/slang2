package com.vogonjeltz.slang2.runtime.typing

import com.vogonjeltz.slang2.runtime.scope.{Scope, ScopeStack}

/**
  * Created by fredd on 10/07/2016.
  */
class SlangInstance(val slangType: SlangType) {

  private val _baseScope: Scope = new Scope()

  val scope = new ScopeStack(_baseScope)

  //Load the members of this class as defined in the class
  scope.load(slangType.members.map(X => X._1 -> X._2.getInstance(Some(this))))
  scope.set("this", this)

  //FIXME: StackOverflow when setting members to new SlangInstances
  //    This happens because on creation that member will be created again

  def findInScope (name : String) = scope.get(name)

  def setInScope  (name: String, obj: SlangInstance) = scope.set(name, obj)

  def runApply (arguments : List[SlangInstance]): Option[SlangInstance] = scope.get("apply")

  override def toString:String = slangType.toStringProto(this)

  def JVMtoString = super.toString

  def isNone = slangType.name == "None"

}
