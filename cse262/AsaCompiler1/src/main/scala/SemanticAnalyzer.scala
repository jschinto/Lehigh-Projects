import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

import IdentKind._
import scala.collection.mutable.Map

case class Sdata(var typ:AsaType, loc:Location)

class SemanticAnalyzer(root:ASTNode) {
  type Tmap = HashMap[ASTNode,Sdata]
  val info = new Tmap() 

  val compatable = Set[(AsaType,AsaType)](
    (INTEGER_TYPE, REAL_TYPE),
    (REAL_TYPE, INTEGER_TYPE),
    (INTEGER_TYPE, INTEGER_TYPE),
    (REAL_TYPE, REAL_TYPE),
    (STRING_TYPE, STRING_TYPE),
    (BOOLEAN_TYPE, BOOLEAN_TYPE),
    (SET_TYPE, SET_TYPE)
  )

  def loctext(loc:Location) = {
    s"on line ${loc.line} at position ${loc.pos} "
  }

  def error(mess:String) = {
    throw new Exception(mess)
  }

  def error0(mess:String, loc:Location) = {
    throw new Exception(s"$mess ${loctext(loc)}")
  }

  def analyze() = {
    program(root)
    info
  }
  
  def program(prog:ASTNode) =  {
    prog match {
      case Program(name, blk) =>  {
        block(blk)
      }
    }
  }

  def block(blk:ASTNode) =  {
    blk match {
      case Block(cdlist, vdlist, statements) =>  {
        cdlist.foreach(const_def(_))
        vdlist.foreach(var_decl(_))
        statements match {
          case CompoundStatement(slist) => {
            slist.foreach(statement(_))
          }
        }
      }
    }
  }

  def const_def(cd:ASTNode) =  {
    cd match {
      case ConstDef(name, Literal(value, typ, loc)) =>  {
        SymbolTable.add(name, CONST_KIND, typ, value)
      }
    }
  }

  def var_decl(vd:ASTNode) =  {
    vd match {
      case VarDecl(vlist, typ) => {
        val xtyp = TypeTable.createType(typ)
        vlist.foreach( (v) => {
          v match {
            case name => {
              SymbolTable.add(name, VAR_KIND, xtyp, 0)
            }
          }
        })
      }
    }
  }

  def checkTargetVar(id:String, loc: Location, trhs:AsaType, isCompound:Boolean) = {
    SymbolTable.lookup(id) match {
      case Some(SymbolTableEntry(name, kind, vartype, value, vindex)) => {
        val vt = if (isCompound) TypeTable.elementType(vartype) else vartype
        if (!(compatable contains ((trhs,vt))))
          error0(s"Expression of type $trhs cannot be assigned to a variable of type $vartype", loc)
      }
      case None => error0(s"Variable $id is undefined", loc)
    }    
  }

  def statement(st:ASTNode):Unit =  {
    st match {

      case AssignmentStatement(lhs, rhs) =>  {
        val trhs = expression(rhs)
        lhs match {
          case ArrayReference(name, index, loc) => {
              val tindex = expression(index)
              if (tindex != INTEGER_TYPE)
                error0("Array index must be integer type.", loc)
              checkTargetVar(name, loc, trhs, true)    
          }
          case VariableReference(name, loc) => {
            checkTargetVar(name, loc, trhs, false)
          }
        }
      }

      case CompoundStatement(statementlist) =>  {
        statementlist.foreach(statement(_))
      }

      case IfStatement(condition, thenstat, elsestat) =>  {
        val t = expression(condition)
        if (t != BOOLEAN_TYPE)
          throw new Exception(s"If statement condition requires boolean expression.")
        statement(thenstat)
        elsestat match {
          case Some(stat) => statement(stat)
          case None =>
        }
      }

      case WhileStatement(condition, body) =>  {
        val t = expression(condition)
        if (t != BOOLEAN_TYPE)
          throw new Exception(s"While statement condition requires boolean expression.")
        statement(body)
      }

      case RepeatStatement(condition, body) =>  {
        body.foreach(statement(_))
        val t = expression(condition)
        if (t != BOOLEAN_TYPE)
          throw new Exception(s"Repeat statement condition requires boolean expression.")
      }

      case ForStatement(indexVar, startExp, endExp, ascending, stat) =>  {
        val Identifier(id, loc) = indexVar
        val t1 = expression(startExp)
        val t2 = expression(endExp)
        checkTargetVar(id, loc, t1, false)
        statement(stat)
      }

      case PrintfStatement(fmt, outexp) =>  {
        val tfmt = expression(fmt)
        if (tfmt != STRING_TYPE)
          error("First parameter of printf must be a string")
        outexp.foreach( (exp) =>  { val t = expression(exp) } )
      }

      case CaseStatement(caseExpression, limbs) => {
        val expressionType = expression(caseExpression)
        if (expressionType != INTEGER_TYPE)
          error("Case expression must be integer type")
        limbs.foreach((limb) => {
          limb match {
            case CaseLimb(labels, caseStatement) => {
              val t = statement(caseStatement)  
            }
          }
        })
      }
    }
  }

  def expression(exp:ASTNode):AsaType = {

    def error(op:String, loc:Location) {
      throw new Exception(s"Invalid operand types for operator $op on line ${loc.line}")
    }

    def arithmeticExp(op:String, t1:AsaType, t2:AsaType) = {
      if (t1 == STRING_TYPE || t2 == STRING_TYPE || t1 == BOOLEAN_TYPE || t2 == BOOLEAN_TYPE ) {
        NO_TYPE
      } else if (t1 == INTEGER_TYPE && t2 == INTEGER_TYPE) {
        if (op == "DIVIDE")
          REAL_TYPE
        else
          INTEGER_TYPE
      } else {
        REAL_TYPE
      }
    }

    def intOnly(op:String, t1:AsaType, t2:AsaType) = {
      if (t1 == INTEGER_TYPE && t2 == INTEGER_TYPE) {
        INTEGER_TYPE
      } else {
        NO_TYPE
      }
    }    

    def logicalExp(op:String, t1:AsaType, t2:AsaType) = {
      if (t1 == BOOLEAN_TYPE && t2 == BOOLEAN_TYPE) {
        BOOLEAN_TYPE
      } else {
        NO_TYPE
      }
    }    

    def relationalExp(op:String, t1:AsaType, t2:AsaType) = {
      if (t1 == t2 || (op == "IN" && (t1 == INTEGER_TYPE && t2 == SET_TYPE))) {
        BOOLEAN_TYPE
      } else {
        NO_TYPE
      }
    }

    def setExp(op:String, t1:AsaType, t2:AsaType) = {
      if (t1 == SET_TYPE && t2 == SET_TYPE) {
        SET_TYPE
      } else {
        NO_TYPE
      }
    }

    val etype = exp match {

      case Binop(op, x1, x2) =>  {
        val t1 = expression(x1)
        val t2 = expression(x2)
        if (t1 == NO_TYPE || t2 == NO_TYPE) {
          error(op, info(x1).loc)
        }
        val texp = op match {
          case "EQUALS"               => relationalExp("EQUALS", t1, t2)
          case "GREATERTHAN"          => relationalExp("GREATERTHAN", t1, t2)
          case "GREATERTHANOREQUALTO" => relationalExp("GREATERTHANOREQUALTO", t1, t2)
          case "LESSTHAN"             => relationalExp("LESSTHAN", t1, t2)
          case "LESSTHANOREQUALTO"    => relationalExp("LESSTHANOREQUALTO", t1, t2)
          case "NOTEQUALTO"           => relationalExp("NOTEQUALTO", t1, t2)
          case "IN"                   => relationalExp("IN", t1, t2)

          case "PLUS"   =>  if (t1 == SET_TYPE) setExp("PLUS", t1, t2)
                            else arithmeticExp("PLUS", t1, t2)
          case "MINUS"  =>  if (t1 == SET_TYPE) setExp("MINUS", t1, t2)
                            else arithmeticExp("MINUS", t1, t2)
          case "TIMES"  =>  if (t1 == SET_TYPE) setExp("TIMES", t1, t2)
                            else arithmeticExp("TIMES", t1, t2)

          case "DIVIDE" =>  arithmeticExp("DIVIDE", t1, t2)
  
          case "SYMMETRIC_DIFFERENCE" => setExp("SYMMETRIC_DIFFERENCE", t1, t2)

          case "DIV" => intOnly("DIV", t1, t2)
          case "MOD" => intOnly("MOD", t1, t2)

          case "RSHIFT" => intOnly("RSHIFT", t1, t2)
          case "LSHIFT" => intOnly("LSHIFT", t1, t2)

          case "AND" => logicalExp("AND", t1, t2)
          case "OR"  => logicalExp("OR", t1, t2)
        }
        info(exp) = Sdata(texp, info(x1).loc)
        texp
      }

      case Literal(n, tconst, loc) => {
        info(exp) = Sdata(tconst, loc)
        tconst
      }

      case VariableReference(id, loc) => {
        var typ0:AsaType = NO_TYPE
        SymbolTable.lookup(id) match {
          case Some(SymbolTableEntry(name, kind, typ, value, vindex)) => {
            info(exp) = Sdata(typ, loc)
            typ0 = typ
          }
          case None => error0(s"Identifier $id is undefined", loc)
        }    
        typ0
      }

      case ArrayReference(id, index, loc) => {
        var typ0:AsaType = NO_TYPE
        SymbolTable.lookup(id) match {
          case Some(SymbolTableEntry(name, kind, typ, value, vindex)) => {
            val tindex = expression(index)
            if (tindex != INTEGER_TYPE)
              error0("Array index must be integer type.", loc)

            val etype = TypeTable.elementType(typ)
            info(exp) = Sdata(etype, loc)
            typ0 = etype
          }
          case None => error0(s"Identifier $id is undefined", loc)
        }    
        typ0
      }

      case _ => NO_TYPE
    }
    etype
  }  
}
