package com.vogonjeltz.slang2.runtime.scope

import com.vogonjeltz.slang2.runtime.typing.SlangInstance

import scala.collection.mutable

/**
  * Created by fredd on 10/07/2016.
  */
class ScopeStack(val base : Scope) extends Scope {

  private val scopes = mutable.Stack(base)

  override def get(name : String): Option[SlangInstance] = {
    val containingScopes = scopes.filter(_.has(name))
    if (containingScopes.length < 1) None
    else containingScopes.head.get(name)
  }

  override def set(name:String, obj: SlangInstance) = scopes.head.set(name, obj)

  override def has(name: String) : Boolean =
    scopes.exists(_ has name)


  override def variableMap = {
    var map = Map[String, SlangInstance]()
    scopes.foreach(_.variableMap.foreach(X => map += X))
    map
  }

  override def load(defintions: Map[String, SlangInstance]) = top.load(defintions)

  def top = scopes.top

  def push(scope: Scope) = scopes.push(scope)

  def pop() = scopes.pop()

  override def toString = s"ScopeStack with variables ${scopes.map(_.toString)}"

}
