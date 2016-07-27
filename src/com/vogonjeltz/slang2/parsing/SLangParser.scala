package com.vogonjeltz.slang2.parsing

import com.vogonjeltz.slang2.ast._
import com.vogonjeltz.slang2.ast.elements._
import com.vogonjeltz.slang2.runtime.Program

import scala.util.parsing.combinator.{JavaTokenParsers, PackratParsers}

/**
  * Created by Freddie Poser on 16/01/2016.
  *
  */
class SLangParser extends JavaTokenParsers with PackratParsers{

  //TODO: Support composite identifiers for cases like "string".operation()
  //TODO: Allow no semicolons

  lazy val program:PackratParser[Program] = rep(line) ^^ (x => new Program(x))

  lazy val line:PackratParser[Line] = comment | (assignment | element  ) <~ "[;[;\n][;\n\r]]".r

  //ASSIGNMENT
    lazy val assignment:PackratParser[Assignment] = assignmentLHS ~ element ^^ {
      case x ~ y => new Assignment(x,y)
    }

    lazy val assignmentLHS = identifier <~ "="

  lazy val comment: PackratParser[Comment] = "//.*".r ^^ (i => new Comment())

  lazy val element: PackratParser[Element] = {
    functionCall        |
    functionDefinition  |
    codeBlock           |
    returnStatement     |
    value               |
    identifier
  }

  lazy val name: Parser[String] = "([a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)".r

  lazy val identifier:Parser[Identifier] = "([a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)(\\.[a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)*".r ^^
    (i => {
      //println(s"Parsed $i")
      new Identifier(i)
    })


  //FUNCTION CALLS
    lazy val functionCall : PackratParser[FunctionCall] = (element ~ elementList ^^ {
      case x ~ y => new FunctionCall(x, y)
    }) | operatorFunctionCall

  lazy val operatorFunctionCall : PackratParser[FunctionCall] = (element ~ name ~ element) ^^ {
      case container ~ name ~ arg => new FunctionCall(new CompositeIdentifier(container, name), List(arg))
    }

  lazy val nameList: Parser[List[String]] = "(" ~> repsep("([a-zA-Z]+[a-zA-Z0-9\\-_]*)".r, ",") <~ ")"

  lazy val elementList : PackratParser[List[Element]] = "(" ~> repsep(element, ",") <~ ")"

  //FUNCTION DEFINITION
    lazy val functionDefinitionLHS = nameList <~ "=>"

    lazy val functionDefinition : PackratParser[FunctionDefinition] = functionDefinitionLHS ~ element ^^ {
      case x ~ y => new FunctionDefinition(x, y)
    }

  lazy val codeBlock : PackratParser[CodeBlock] = ("{" ~> rep(line) <~ "}") ^^ (X => new CodeBlock(X))

  //Value
    lazy val value: PackratParser[ValueElement] = string

    lazy val string: PackratParser[StringElement] = stringLiteral ^^ (X => new StringElement(X.toString.substring(1,X.length-1)))

  lazy val returnStatement : PackratParser[ReturnStatement] = ("return\\s".r ~> element) ^^ (X => new ReturnStatement(X))

}
