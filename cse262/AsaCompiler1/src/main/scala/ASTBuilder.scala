import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree.TerminalNode
import scala.util.matching._

class ASTBuilder extends AsaBaseVisitor[ASTNode] {

  val vocabulary = new AsaParser(null).getVocabulary()

  def loc(tnode:TerminalNode) = {
    Location(tnode.getSymbol().getLine(),tnode.getSymbol().getCharPositionInLine())
  }

  override def visitProgram(ctx:AsaParser.ProgramContext) = {
    val programName = ctx.IDENT().getText
    val block = visit(ctx.block())
    Program(programName, block)
  }

	override def visitBlock(ctx:AsaParser.BlockContext) = {
    val constDefs = if (ctx.constant_definition() == null) List() else ctx.constant_definition().asScala.toList.map(visit(_))
    val varDecl = if (ctx.variable_declaration() == null) List() else ctx.variable_declaration().asScala.toList.map(visit(_))
    val statement = visit(ctx.compound_statement())
    Block(constDefs, varDecl, statement)
  }

	override def visitConstant_definition(ctx:AsaParser.Constant_definitionContext) = {
    val name = ctx.IDENT().getText
    val value = visit(ctx.literal())
    ConstDef(name, value)
  }

  // Literals

  override def visitIntegerLiteralAlt(ctx:AsaParser.IntegerLiteralAltContext) = {
    visit(ctx.integerLiteral())
  }

  override def visitDecimalIntegerLiteralAlt(ctx:AsaParser.DecimalIntegerLiteralAltContext) = {
    val decint = ctx.DECIMALINTEGERLITERAL()
    val intValue = Integer.decode(decint.getText)
    Literal(intValue, INTEGER_TYPE, loc(decint))
  }

  override def visitHexadecimalIntegerLiteralAlt(ctx:AsaParser.HexadecimalIntegerLiteralAltContext) = {
    val hexint = ctx.HEXADECIMALINTEGERLITERAL()
    val intValue = Integer.decode(hexint.getText)
    Literal(intValue, INTEGER_TYPE, loc(hexint))
  }

  override def visitOctalIntegerLiteralAlt(ctx:AsaParser.OctalIntegerLiteralAltContext) = {
    val octint = ctx.OCTALINTEGERLITERAL()
    val intValue = Integer.decode(octint.getText)
    Literal(intValue, INTEGER_TYPE, loc(octint))
  }

  override def visitFloatingLiteralAlt(ctx:AsaParser.FloatingLiteralAltContext) = {
    val float = ctx.FLOATINGPOINTLITERAL()
    val floatValue = float.getText.toDouble
    Literal(floatValue, REAL_TYPE, loc(float))
  }

  override def visitStringLiteralAlt(ctx:AsaParser.StringLiteralAltContext) = {
    val s = ctx.STRINGLITERAL()
    val st = s.getText drop 1 dropRight 1
    val nl = "\n"
    val tab = "\t"
    val escape = """\\([nt])""".r
    val escapedString = escape.replaceAllIn(st, m => { 
      m.group(1) match {
        case "n" => nl
        case "t" => tab
      }
    })
    Literal(escapedString, STRING_TYPE, loc(s))
  }

  override def visitBooleanLiteralAlt(ctx:AsaParser.BooleanLiteralAltContext) = {
    val boolvalue = ctx.booleanLiteral()
    boolvalue.getText match {
      case "true" => Literal(true, BOOLEAN_TYPE, Location(0,0))
      case "false" => Literal(false, BOOLEAN_TYPE, Location(0,0))
    }
  }

  override def visitSetLiteral(ctx:AsaParser.SetLiteralContext) = {
    val vlist = ctx.integerLiteral().asScala.toList.map(_.getText.toInt)
    var v = 0
    for(a <- vlist) {
      val temp = 1 << (a-1)
      v = v | temp
    }
    Literal(v, SET_TYPE, Location(0,0))
  }

	override def visitVariable_declaration(ctx:AsaParser.Variable_declarationContext) = {
    var identList = ctx.IDENT().asScala.toList.map(_.getText)
    var typ = visit(ctx.atype())
    VarDecl(identList, typ)
  }

	override def visitIdent(ctx:AsaParser.IdentContext) = {
    Type(NamedType(ctx.IDENT().getText))
  }

	override def visitArray(ctx:AsaParser.ArrayContext) = {
    val start = Integer.decode(ctx.integerLiteral(0).getText)
    val end   = Integer.decode(ctx.integerLiteral(1).getText)
    val elementType = visit(ctx.atype())
    Type(ArrayType(Range(start,end,1), elementType))
  }

	override def visitSet(ctx:AsaParser.SetContext) = {
    //val elementType = visit(ctx.atype())
    //Type(SetType(elementType))
    Type(SetType)
  }

	override def visitStatement(ctx:AsaParser.StatementContext) = {
    visitChildren(ctx)
  }

	override def visitAssignment_statement(ctx:AsaParser.Assignment_statementContext) = {
    val lhs = visit(ctx.lhsreference())
    val rhs = visit(ctx.logicalexpression())
    AssignmentStatement(lhs, rhs)
  }

  override def visitLhsreference(ctx:AsaParser.LhsreferenceContext) = {
    val Identifier(name, loc) = visit(ctx.identifier())
    if (ctx.simpleexpression() == null) {
      VariableReference(name, loc)
    } else {
      val index = visit(ctx.simpleexpression())
      ArrayReference(name, index, loc)
    }
  }

  override def visitRhsvalue(ctx:AsaParser.RhsvalueContext) = {
    val Identifier(name, loc) = visit(ctx.identifier())
    if (ctx.simpleexpression() == null) {
      VariableReference(name, loc)
    } else {
      val index = visit(ctx.simpleexpression())
      ArrayReference(name, index, loc)
    }
  }

	override def visitCompound_statement(ctx:AsaParser.Compound_statementContext) = {
    val statements = ctx.statement().asScala.toList.map(visit(_))
    CompoundStatement(statements)
  }

	override def visitWhile_statement(ctx:AsaParser.While_statementContext) = {
    val cond = visit(ctx.logicalexpression())
    val body = visit(ctx.statement())
    WhileStatement(cond, body)
  }

	override def visitRepeat_statement(ctx:AsaParser.Repeat_statementContext) = {
    val cond = visit(ctx.logicalexpression())
    val statements = ctx.statement().asScala.toList.map(visit(_))
    RepeatStatement(cond, statements)
  }

	override def visitIf_statement(ctx:AsaParser.If_statementContext) = {
    val cond = visit(ctx.logicalexpression())
    val thenstat = visit(ctx.statement(0))
    val elsestat = if (ctx.statement.size > 1) Some(visit(ctx.statement(1))) else None
    IfStatement(cond, thenstat, elsestat)
  }

	override def visitFor_statement(ctx:AsaParser.For_statementContext) = {
    val indexvar = visit(ctx.identifier())
    val startexp = visit(ctx.simpleexpression(0))
    val endexp = visit(ctx.simpleexpression(1))
    val ascending = ctx.dir.getType == AsaParser.TO
    val body = visit(ctx.statement())
    ForStatement(indexvar, startexp, endexp, ascending, body)
  }

	override def visitPrintf_statement(ctx:AsaParser.Printf_statementContext) = {
    val tmp = ctx.simpleexpression().asScala.toList.map(visit(_))
    val fmt = tmp(0)
    val outexp = tmp drop 1
    PrintfStatement(fmt, outexp)
  }

	override def visitCase_statement(ctx:AsaParser.Case_statementContext) = {
    val caseExpression = visit(ctx.simpleexpression())
    val limbs = ctx.case_limb().asScala.toList.map(visit(_))
    CaseStatement(caseExpression, limbs)
  }

	override def visitCase_limb(ctx:AsaParser.Case_limbContext) = {
    val caseLabels = ctx.integerLiteral().asScala.toList.map(visit(_)).map((limb) => {
      limb match {
        case Literal(n, typ, loc) => n.asInstanceOf[Int]        
      }
    })
    val statement = visit(ctx.statement())
    CaseLimb(caseLabels, statement)
  }

	override def visitLogicalexpression(ctx:AsaParser.LogicalexpressionContext) = {
    var re1 = visit(ctx.relationalexpression(0))
    if (ctx.relationalexpression.size() > 1) { 
      val re2 = visit(ctx.relationalexpression(1))
      val op = ctx.op.getType
      val re = Binop(vocabulary.getSymbolicName(op), re1, re2)
      re
    }
    re1
  }

	override def visitRelationalexpression(ctx:AsaParser.RelationalexpressionContext) = {
    val se1 = visit(ctx.simpleexpression(0))
    if (ctx.simpleexpression.size() > 1) { 
      val se2 = visit(ctx.simpleexpression(1))
      val op = ctx.op.getType
      val se = Binop(vocabulary.getSymbolicName(op), se1, se2)
      se
    } else {
      se1
    }
  }

	override def visitSimpleexpression(ctx:AsaParser.SimpleexpressionContext) = {
    var t1 = visit(ctx.term(0))
    for (i <- 1 until ctx.term.size()) {
      val t2 = visit(ctx.term(i))
      val t = Binop(vocabulary.getSymbolicName(ctx.op.get(i-1).getType), t1, t2)
      t1 = t
    }
    t1
  }

	override def visitTerm(ctx:AsaParser.TermContext) = {
    var f1 = visit(ctx.factor(0))
    for (i <- 1 until ctx.factor.size()) {
      val f2 = visit(ctx.factor(i))
      val f = Binop(vocabulary.getSymbolicName(ctx.op.get(i-1).getType), f1, f2)
      f1 = f
    }
    f1
  }

  override def visitFactor(ctx:AsaParser.FactorContext) = { 
    if (ctx.fle != null) visit(ctx.fle)
    else if (ctx.fl != null) visit(ctx.fl)
    else if (ctx.fi != null) visit(ctx.fi)
    else visit(ctx.fn)
  }

	override def visitNegation(ctx:AsaParser.NegationContext) = {
    val value = visit(ctx.factor())
    Unop(vocabulary.getSymbolicName(AsaParser.NOT), value)
  }

	override def visitIdentifier(ctx:AsaParser.IdentifierContext) = {
    val ident = ctx.IDENT()
    Identifier(ident.getText, loc(ident))
  }

}