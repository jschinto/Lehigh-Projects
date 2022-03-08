import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io._

import IdentKind._

class CodeGenerator(root:ASTNode, info:HashMap[ASTNode,Sdata]) {
  var ilist = List[JVMNode]()
  var nlabel = 0
  val conversions = Map[(AsaType,AsaType),JVMInstruction](
    (INTEGER_TYPE, REAL_TYPE) -> I2D,
    (REAL_TYPE, INTEGER_TYPE) -> D2I
  )

  def gen() = {
    program(root)
  }

  def error(mess:String) = {
    throw new Exception(mess)
  }

  def dump(pw:PrintWriter) = {
    ilist.foreach(pw.println(_))
  }

  def initializeVariables() = {
    SymbolTable.getVarDefs.foreach((s) => {
      s.typ match {
        case INTEGER_TYPE => {
          add(ICONST_0)
          add(ISTORE(s.vindex))
        }
        case REAL_TYPE => {
          add(FCONST_0)
          add(FSTORE(s.vindex))
        }
        case BOOLEAN_TYPE => {
          add(ICONST_0)
          add(ISTORE(s.vindex))
        }
        case STRING_TYPE => {
          add(ACONST_NULL)
          add(ASTORE(s.vindex))
        }
        case ARRAY_TYPE(range,etype) => {
          val t = ClassFile.typeMap(etype)
          val size = range.end - range.start + 1
          add(LDC(size))
          add(NEWARRAY(t))
          add(ASTORE(s.vindex))
        }
        case SET_TYPE => {
          add(ICONST_0)
          add(ISTORE(s.vindex))
        }
      }
    })
  }

  def code() = {
    ilist
  }
  
  def add(node:JVMNode) = {  
    ilist = ilist ::: List(node)
  }

  def newLabel() = {
    nlabel += 1
    val lab = "#" + nlabel
    lab
  } 

  def program(prog:ASTNode) =  {
    prog match {
      case Program(name, blk) =>  {
        add(CLASS(name, Set(ClassModifiers.PUBLIC)))
        add(METHOD("main", Set(MethodModifiers.PUBLIC, MethodModifiers.STATIC), "([Ljava/lang/String;)V"))
        block(blk)
        add(RETURN)
        add(END_METHOD)
        add(END_CLASS)
      }
    }
  }

  def block(blk:ASTNode) =  {
    blk match {
      case Block(cdlist, vdlist, statements) =>  {
        initializeVariables
        statements match {
          case CompoundStatement(slist) => {
            slist.foreach(statement(_))
          }
        }
      }
    }
  }

  def statement(st:ASTNode):Unit =  {
    st match {

      case AssignmentStatement(lhs, rhs) =>  {
        lhs match {
          case ArrayReference(vname, index, loc) => {
            val Some(SymbolTableEntry(name, kind, typ, value, vindex)) = SymbolTable.lookup(vname)
            val etyp = TypeTable.elementType(typ)
            val start = TypeTable.arrayRangeStart(typ)
            val typs = (info(rhs).typ, etyp)
            add(ALOAD(vindex))
            expression(index)
            add(LDC(start))
            add(ISUB)
            expression(rhs)
            if (conversions contains typs) add(conversions(typs))
            etyp match {
              case INTEGER_TYPE => add(IASTORE)
              case REAL_TYPE    => add(DASTORE)
              case BOOLEAN_TYPE => add(IASTORE)
              case STRING_TYPE  => add(AASTORE)
            }
          }
          case VariableReference(vname, loc) => {
            expression(rhs)
            val Some(SymbolTableEntry(name, kind, typ, value, vindex)) = SymbolTable.lookup(vname)
            val typs = (info(rhs).typ, typ)
            if (conversions contains typs) add(conversions(typs))
            typ match {
              case INTEGER_TYPE => add(ISTORE(vindex))
              case REAL_TYPE    => add(DSTORE(vindex))
              case BOOLEAN_TYPE => add(ISTORE(vindex))
              case STRING_TYPE  => add(ASTORE(vindex))
              case SET_TYPE     => add(ISTORE(vindex))
            }
          }
        }
        
      }

      case CompoundStatement(statementlist) =>  {
        statementlist.foreach(statement(_))
      }

      case IfStatement(condition, thenstat, elsestat) =>  {
        val falseLab = newLabel
        expression(condition)
        add(IFEQ(falseLab))
        statement(thenstat)
        if (elsestat == None) {
          add(LABEL(falseLab))
        } else {
          val afterLab = newLabel
          add(GOTO(afterLab))
          add(LABEL(falseLab))
          val Some(stat) = elsestat
          statement(stat)
          add(LABEL(afterLab))
        }
      }

      case WhileStatement(condition, body) =>  {
        val top = newLabel
        val afterLoop = newLabel
        add(LABEL(top))
        expression(condition)
        add(IFEQ(afterLoop))
        statement(body)
        add(GOTO(top))
        add(LABEL(afterLoop))
      }

      case RepeatStatement(condition, body) =>  {
        val top = newLabel
        add(LABEL(top))
        body.foreach(statement(_))
        expression(condition)
        add(IFEQ(top))
      }

      case ForStatement(indexVar, startExp, endExp, ascending, stat) =>  {
        val top = newLabel
        val after = newLabel
        val Identifier(name, loc) = indexVar
        val Some(vindex) = SymbolTable.getVindex(name)
        expression(startExp)
        add(ISTORE(vindex))
        add(LABEL(top))
        add(ILOAD(vindex))
        expression(endExp)
        if (ascending) {
          add(IF_ICMPGT(after))
        } else {
          add(IF_ICMPLT(after))
        }
        statement(stat)
        if (ascending) {
          add(IINC(vindex,1))
        } else {
          add(IINC(vindex,-1))
        }
        add(GOTO(top))
        add(LABEL(after))
      }

      case PrintfStatement(fmt, outexp) =>  {

        // This methods adds the instructions to take an integer representing a set
        // and convert it to a string of the form [ i1 i2 .....]
        def convertSet = {
          val p70 = newLabel
          val p64 = newLabel
          val p27 = newLabel
          val sval = 11
          val sb = 12
          val index = 13
          val mask = 14
          add(ISTORE(sval))
          add(NEW("java/lang/StringBuilder"))
          add(DUP)
          add(INVOKESPECIAL("java/lang/StringBuilder", "<init>", "()V"))
          add(ASTORE(sb))
          add(ALOAD(sb))
          add(BIPUSH(91))
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;"))
          add(POP)
          add(ALOAD(sb))
          add(BIPUSH(32))
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;"))
          add(POP)
          add(ICONST_0)
          add(ISTORE(index))
          add(LABEL(p27))
          add(ILOAD(index))
          add(BIPUSH(32))
          add(IF_ICMPGE(p70))
          add(ICONST_1)
          add(ILOAD(index))
          add(ISHL)
          add(ISTORE(mask))
          add(ILOAD(sval))
          add(ILOAD(mask))
          add(IAND)
          add(IFEQ(p64))
          add(ALOAD(sb))
          add(BIPUSH(48))
          add(ILOAD(index))
          add(IADD)
          add(ICONST_1)
          add(IADD)
          add(I2C)
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;"))
          add(POP)
          add(ALOAD(sb))
          add(BIPUSH(32))
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;"))
          add(POP)
          add(LABEL(p64))
          add(IINC(index,1))
          add(GOTO(p27))
          add(LABEL(p70))
          add(ALOAD(sb))
          add(BIPUSH(93))
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;"))
          add(POP)
          add(ALOAD(sb))
          add(INVOKEVIRTUAL("java/lang/StringBuilder", "toString", "()Ljava/lang/String;"))
        }

        var n = 0
        add(GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;"))
        expression(fmt)
        val nparams = outexp.length
        add(BIPUSH(nparams.toByte))
        add(ANEWARRAY("java/lang/Object"))
        add(DUP)
        outexp.foreach( (exp) => {
          add(BIPUSH(n.toByte))
          n += 1
          expression(exp)
          info(exp).typ match {
            case INTEGER_TYPE => add(INVOKESTATIC("java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;"))
            case REAL_TYPE    => add(INVOKESTATIC("java/lang/Double", "valueOf", "(D)Ljava/lang/Double;"))
            case STRING_TYPE =>
            case SET_TYPE => convertSet
            case _ => error("invalid type in printf")
          }
          add(AASTORE)
          add(DUP)
        })
        add(POP)
        add(INVOKEVIRTUAL("java/io/PrintStream", "printf", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;"))
        add(POP)
      }

      case CaseStatement(caseExpression, limbs) => {
        var keylist = List[Int]()
        var labels = List[String]()
        val before = newLabel
        val after = newLabel
        expression(caseExpression)
        add(GOTO(before))
        limbs.foreach((limb) => {
          limb match {
            case CaseLimb(keys, caseStatement) => {
              val lab = newLabel
              keys.foreach( (key:Int) => {
                labels = labels ::: List(lab)
                keylist = keylist ::: List(key)
              })
              add(LABEL(lab))
              statement(caseStatement)
              add(GOTO(after))
            }
          }
        })
        add(LABEL(before))
        add(LOOKUPSWITCH(after, keylist, labels))
        add(LABEL(after))
      }

    }
  }

  def expression(exp:ASTNode) {
    val trueLab = newLabel
    val falseLab = newLabel

    def genComparisonOp(op:JVMInstruction) = {
      add(op)
      add(ICONST_0)
      add(GOTO(falseLab))
      add(LABEL(trueLab))
      add(ICONST_1)
      add(LABEL(falseLab))
    }

    def genInOp() = {
      add(SWAP)
      add(ICONST_1)
      add(ISUB)
      add(ICONST_1)
      add(SWAP)
      add(ISHL)
      add(IAND)
      add(IFEQ(falseLab))
      add(ICONST_1)
      add(GOTO(trueLab))
      add(LABEL(falseLab))
      add(ICONST_0)
      add(LABEL(trueLab))
    }

    def adjustTypes(parent:AsaType, child:AsaType) = {
      if (child == INTEGER_TYPE && parent == REAL_TYPE)
        add(I2D)
    }

    val eval = exp match {
      case Binop(op, x1, x2) =>  {
        val texp = info(exp).typ
        val tx1 = info(x1).typ
        val tx2 = info(x2).typ
        expression(x1)
        adjustTypes(texp,tx1)
        expression(x2)
        adjustTypes(texp,tx2)
        op match {
          case "EQUALS" => genComparisonOp(IF_ICMPEQ(trueLab))
          case "GREATERTHAN" => genComparisonOp(IF_ICMPGT(trueLab))
          case "GREATERTHANOREQUALTO" => genComparisonOp(IF_ICMPGE(trueLab))
          case "LESSTHAN" => genComparisonOp(IF_ICMPLT(trueLab))
          case "LESSTHANOREQUALTO" => genComparisonOp(IF_ICMPLE(trueLab))
          case "NOTEQUALTO" => genComparisonOp(IF_ICMPNE(trueLab))

          case "IN" => genInOp

          case "PLUS" => texp match {
                            case SET_TYPE => add(IOR)
                            case INTEGER_TYPE => add(IADD)
                            case _ => add(DADD)
                         } 
          case "MINUS" => texp match {
                            case SET_TYPE => {
                              add(ICONST_M1)
                              add(IXOR)
                              add(IAND)
                            }
                            case INTEGER_TYPE => add(ISUB)
                            case _ => add(DSUB)
                         }
          case "TIMES" => texp match {
                            case SET_TYPE => add(IAND)
                            case INTEGER_TYPE => add(IMUL)
                            case _ => add(DMUL)
                         }          
          case "DIVIDE" => add(DDIV)

          case "DIV" => add(IDIV)
          case "MOD" => add(IREM)

          case "RSHIFT" => add(ISHR)
          case "LSHIFT" => add(ISHL)

          case "AND" => add(IAND)
          case "OR" => add(IOR)
        }
      }

      case Literal(n, typ, loc) => add(LDC(n))

      case VariableReference(id, loc) => {
        val Some(SymbolTableEntry(name, kind, typ, value, vindex)) = SymbolTable.lookup(id)
        kind match {
          case CONST_KIND =>
            typ match {
              case INTEGER_TYPE => add(LDC(value))
              case REAL_TYPE    => add(LDC(value))
              case BOOLEAN_TYPE => add(LDC(value))
              case STRING_TYPE  => add(LDC(value))
              case SET_TYPE     => add(LDC(value))
            }
          case VAR_KIND =>
            typ match {
              case INTEGER_TYPE => add(ILOAD(vindex))
              case REAL_TYPE    => add(DLOAD(vindex))
              case BOOLEAN_TYPE => add(ILOAD(vindex))
              case STRING_TYPE  => add(ALOAD(vindex))
              case SET_TYPE     => add(ILOAD(vindex))
            }
        }
      }

      case ArrayReference(vname, index, loc) => {
        val Some(SymbolTableEntry(name, kind, typ, value, vindex)) = SymbolTable.lookup(vname)
        val etyp = TypeTable.elementType(typ)
        val start = TypeTable.arrayRangeStart(typ)
        //val typs = (info(rhs).typ, etyp)
        add(ALOAD(vindex))
        expression(index)
        add(LDC(start))
        add(ISUB)
        //if (conversions contains typs) add(conversions(typs))
        etyp match {
          case INTEGER_TYPE => add(IALOAD)
          case REAL_TYPE    => add(DALOAD)
          case BOOLEAN_TYPE => add(IALOAD)
          case STRING_TYPE  => add(AALOAD)
        }
      }

    }
  }  
}
