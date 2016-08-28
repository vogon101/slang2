package com.vogonjeltz.slang2.parsing

import com.vogonjeltz.slang2.ast._
import com.vogonjeltz.slang2.ast.elements._
import com.vogonjeltz.slang2.runtime.Program
import jdk.nashorn.internal.runtime.arrays.IntElements

import scala.util.parsing.combinator.{JavaTokenParsers, PackratParsers}

/**
  * Created by Freddie Poser on 16/01/2016.
  *
  */
class SLangParser extends JavaTokenParsers with PackratParsers{

  //TODO: Support composite identifiers for cases like "string".operation()
  //TODO: Allow no semicolons
  //TODO: Allow files to end with line(no semicolon/newline)

  lazy val program:PackratParser[Program] = rep(line) ^^ (x => new Program(x))

  lazy val line:PackratParser[Line] = (comment | (assignment | element)) <~ ( ";")

  //ASSIGNMENT
    lazy val assignment:Parser[Assignment] = assignmentLHS ~ element ^^ {
      case x ~ y => new Assignment(x,y)
    }

    lazy val assignmentLHS: Parser[Identifier] = identifier <~ "="

  lazy val comment: PackratParser[Comment] = "//.*".r ^^ (i => new Comment())

  lazy val element: PackratParser[Element] = {
    functionCall        |
    instantiation       |
    functionDefinition  |
    codeBlock           |
    returnStatement     |
    value               |
    identifier


  }

  lazy val name: PackratParser[String] = "([a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)".r ^^ (X => X)

  //IDENTIFIER
    lazy val identifier:PackratParser[Identifier] = "([a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)(\\.[a-zA-Z_\\+\\-\\*^%#~@\\?\\/]+[a-zA-Z0-9_\\+\\-\\*^%#~@\\?\\/]*)*".r ^^
      (i => {
        //println(s"Parsed $i")
        new Identifier(i)
      }) | compositeIdentifier

    lazy val compositeIdentifier: PackratParser[CompositeIdentifier] = (compositeIdentifierLHS ~ name) ^^ {
      case element ~ name => new CompositeIdentifier(element, name)
    }

    lazy val compositeIdentifierLHS: PackratParser[Element] = element <~ "."


  //FUNCTION CALLS
    lazy val functionCall : PackratParser[FunctionCall] = (element ~ elementList ^^ {
      case x ~ y => new FunctionCall(x, y)
    }) | operatorFunctionCall

    lazy val operatorFunctionCall : PackratParser[FunctionCall] = (element ~ name ~ element) ^^ {
        case container ~ name ~ arg => new FunctionCall(new CompositeIdentifier(container, name), List(arg))
      }

  lazy val nameList: PackratParser[List[String]] = "(" ~> repsep("([a-zA-Z]+[a-zA-Z0-9\\-_]*)".r, ",") <~ ")"

  lazy val elementList : PackratParser[List[Element]] = "(" ~> repsep(element, ",") <~ ")"

  //FUNCTION DEFINITION
    lazy val functionDefinitionLHS: PackratParser[List[String]] = nameList <~ "=>"

    lazy val functionDefinition : PackratParser[FunctionDefinition] = functionDefinitionLHS ~ element ^^ {
      case x ~ y => new FunctionDefinition(x, y)
    }

  lazy val codeBlock : PackratParser[CodeBlock] = ("{" ~> rep(line) <~ "}") ^^ (X => new CodeBlock(X))

  //Value
    lazy val value: PackratParser[ValueElement] = string | number

    lazy val string: PackratParser[StringElement] = stringLiteral ^^ (X => new StringElement(X.toString.substring(1,X.length-1)))

    lazy val number: PackratParser[NumberElement] = float | integer

      lazy val integer: PackratParser[IntElement] = """[+-]?\d+""".r ^^ { i => new IntElement(i.toInt) }

      lazy val float: PackratParser[FloatElement] = """[+-]?[0-9]+((\.[0-9]+([eE][+-]?[0-9]+)?[fF]?)|([fF])|([eE][+-]?[0-9]+))\b""".r ^^ {f => new FloatElement(f.toFloat) }

  lazy val returnStatement : PackratParser[ReturnStatement] = ("return\\s".r ~> element) ^^ (X => new ReturnStatement(X))

  lazy val instantiation : PackratParser[Instantiation] = ("new" ~> name ~ elementList) ^^ {
    case n ~ l => new Instantiation(n, l)
  }

}
