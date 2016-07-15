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

  lazy val program:PackratParser[Program] = rep(line) ^^ (x => new Program(x))

  lazy val line:PackratParser[Line] = (assignment | element | comment) <~ (";" | "[\n\r]*".r)

  //ASSIGNMENT
    lazy val assignment:PackratParser[Assignment] = assignmentLHS ~ element ^^ {
      case x ~ y => new Assignment(x,y)
    }

    lazy val assignmentLHS = identifier <~ "="

  lazy val comment = "//.*".r ^^ (i => new Comment())

  lazy val element: PackratParser[Element] = {
    functionCall        |
    functionDefinition  |
    codeBlock           |
    value               |
    identifier
  }
  lazy val name: Parser[String] = "([a-zA-Z]+[a-zA-Z0-9\\-_]*)".r

  lazy val identifier:Parser[Identifier] = "([a-zA-Z]+[a-zA-Z0-9\\-_]*)(\\.[a-zA-Z]+[a-zA-Z0-9\\-_]*)*".r ^^ (i => new Identifier(i))

  lazy val functionCall : PackratParser[FunctionCall] = element ~ elementList ^^ {
    case x ~ y => new FunctionCall(x, y)
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

}
