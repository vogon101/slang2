package com.vogonjeltz.slang2.runtime

import com.vogonjeltz.slang2.ast.Line
import com.vogonjeltz.slang2.runtime.StandardLib.{Library, STDLIB}
import com.vogonjeltz.slang2.runtime.scope._

/**
  * Created by fredd on 10/07/2016.
  */
class Program (val lines: List[Line]) {

  Program.register(this)

  val globalScope = new GlobalScope()

  val currentScope = new ScopeStack(globalScope)

  STDLIB.onBuild.foreach(loadLibSimple)

  def loadLibSimple(lib: Library): Unit = {
    lib.globalMembers.foreach(
      X => currentScope.set(X._1, X._2)
    )
  }

  def loadLibNamespaced(lib: Library, name: String): Unit = {
    val namespace = new Namespace()
    lib.globalMembers.foreach(
      X => namespace.scope.set(X._1, X._2)
    )
    currentScope.set(name, namespace)
  }

  def run(): Unit ={
    lines.foreach( _ run())
  }

}

object Program {

  private var _currentInstance: Program = null

  def apply()  = _currentInstance

  def register(p: Program) = _currentInstance = p

}
