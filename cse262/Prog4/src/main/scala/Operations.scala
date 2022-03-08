//import scala.tools.asm.Label
// JVM Instructions

object ClassModifiers extends Enumeration {
  type ClassModifiers = Value
  val PUBLIC, FINAL, SUPER, INTERFACE, ABSTRACT, STRICTFP = Value
}

object FieldModifiers extends Enumeration {
  type FieldModifiers = Value
  val PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, VOLATILE, TRANSIENT = Value
}

object MethodModifiers extends Enumeration {
  type MethodModifiers = Value
  val PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, SYNCHRONIZED, NATIVE, ABSTRACT, STRICTFP = Value
}

abstract class JVMNode
abstract class JVMDirective extends JVMNode

case class CLASS(fullName:String, modifiers:Set[ClassModifiers.Value]) extends JVMDirective
case object END_CLASS extends JVMDirective
case class FIELD(name:String, modifiers:Set[FieldModifiers.Value], value:String) extends JVMDirective
case class METHOD(name:String, modifiers:Set[MethodModifiers.Value], typ:String) extends JVMDirective
case object END_METHOD extends JVMDirective
case class SUPER(name:String) extends JVMDirective
case class LABEL(name:String) extends JVMDirective

abstract class JVMInstruction extends JVMNode
abstract class Branch extends JVMInstruction

// Constants
case object NOP extends JVMInstruction
case object ACONST_NULL extends JVMInstruction
case object ICONST_M1 extends JVMInstruction
case object ICONST_0 extends JVMInstruction
case object ICONST_1 extends JVMInstruction
case object ICONST_2 extends JVMInstruction
case object ICONST_3 extends JVMInstruction
case object ICONST_4 extends JVMInstruction
case object ICONST_5 extends JVMInstruction
case object LCONST_0 extends JVMInstruction
case object LCONST_1 extends JVMInstruction
case object FCONST_0 extends JVMInstruction
case object FCONST_1 extends JVMInstruction
case object FCONST_2 extends JVMInstruction
case object DCONST_0 extends JVMInstruction
case object DCONST_1 extends JVMInstruction
case class BIPUSH(cval: Byte) extends JVMInstruction
case class SIPUSH(cval: Short) extends JVMInstruction

case class LDC(cval: Any) extends JVMInstruction // Other const types?
case class LDC_W(cval: Any) extends JVMInstruction
case class LDC2_W(cval: Any) extends JVMInstruction

// Loads
case class ILOAD(vindex: Int) extends JVMInstruction
case class LLOAD(vindex: Int) extends JVMInstruction
case class FLOAD(vindex: Int) extends JVMInstruction
case class DLOAD(vindex: Int) extends JVMInstruction
case class ALOAD(vindex: Int) extends JVMInstruction
case object ILOAD_0 extends JVMInstruction
case object ILOAD_1 extends JVMInstruction
case object ILOAD_2 extends JVMInstruction
case object ILOAD_3 extends JVMInstruction
case object LLOAD_0 extends JVMInstruction
case object LLOAD_1 extends JVMInstruction
case object LLOAD_2 extends JVMInstruction
case object LLOAD_3 extends JVMInstruction
case object FLOAD_0 extends JVMInstruction
case object FLOAD_1 extends JVMInstruction
case object FLOAD_2 extends JVMInstruction
case object FLOAD_3 extends JVMInstruction
case object DLOAD_0 extends JVMInstruction
case object DLOAD_1 extends JVMInstruction
case object DLOAD_2 extends JVMInstruction
case object DLOAD_3 extends JVMInstruction
case object ALOAD_0 extends JVMInstruction
case object ALOAD_1 extends JVMInstruction
case object ALOAD_2 extends JVMInstruction
case object ALOAD_3 extends JVMInstruction
case object IALOAD  extends JVMInstruction
case object LALOAD  extends JVMInstruction
case object FALOAD  extends JVMInstruction
case object DALOAD  extends JVMInstruction
case object AALOAD  extends JVMInstruction
case object BALOAD  extends JVMInstruction
case object CALOAD  extends JVMInstruction
case object SALOAD  extends JVMInstruction

// Stores
case class ISTORE(vindex: Int) extends JVMInstruction
case class LSTORE(vindex: Int) extends JVMInstruction
case class FSTORE(vindex: Int) extends JVMInstruction
case class DSTORE(vindex: Int) extends JVMInstruction
case class ASTORE(vindex: Int) extends JVMInstruction
case object ISTORE_0 extends JVMInstruction
case object ISTORE_1 extends JVMInstruction
case object ISTORE_2 extends JVMInstruction
case object ISTORE_3 extends JVMInstruction
case object LSTORE_0 extends JVMInstruction
case object LSTORE_1 extends JVMInstruction
case object LSTORE_2 extends JVMInstruction
case object LSTORE_3 extends JVMInstruction
case object FSTORE_0 extends JVMInstruction
case object FSTORE_1 extends JVMInstruction
case object FSTORE_2 extends JVMInstruction
case object FSTORE_3 extends JVMInstruction
case object DSTORE_0 extends JVMInstruction
case object DSTORE_1 extends JVMInstruction
case object DSTORE_2 extends JVMInstruction
case object DSTORE_3 extends JVMInstruction
case object ASTORE_0 extends JVMInstruction
case object ASTORE_1 extends JVMInstruction
case object ASTORE_2 extends JVMInstruction
case object ASTORE_3 extends JVMInstruction
case object IASTORE  extends JVMInstruction
case object LASTORE  extends JVMInstruction
case object FASTORE  extends JVMInstruction
case object DASTORE  extends JVMInstruction
case object AASTORE  extends JVMInstruction
case object BASTORE  extends JVMInstruction
case object CASTORE  extends JVMInstruction
case object SASTORE  extends JVMInstruction

// Stack
case object POP     extends JVMInstruction
case object POP2    extends JVMInstruction
case object DUP     extends JVMInstruction
case object DUP_X1  extends JVMInstruction
case object DUP_X2  extends JVMInstruction
case object DUP2    extends JVMInstruction
case object DUP2_X1 extends JVMInstruction
case object DUP2_X2 extends JVMInstruction
case object SWAP    extends JVMInstruction

// Math
case object IADD  extends JVMInstruction
case object LADD  extends JVMInstruction
case object FADD  extends JVMInstruction
case object DADD  extends JVMInstruction
case object ISUB  extends JVMInstruction
case object LSUB  extends JVMInstruction
case object FSUB  extends JVMInstruction
case object DSUB  extends JVMInstruction
case object IMUL  extends JVMInstruction
case object LMUL  extends JVMInstruction
case object FMUL  extends JVMInstruction
case object DMUL  extends JVMInstruction
case object IDIV  extends JVMInstruction
case object LDIV  extends JVMInstruction
case object FDIV  extends JVMInstruction
case object DDIV  extends JVMInstruction
case object IREM  extends JVMInstruction
case object LREM  extends JVMInstruction
case object FREM  extends JVMInstruction
case object DREM  extends JVMInstruction
case object INEG  extends JVMInstruction
case object LNEG  extends JVMInstruction
case object FNEG  extends JVMInstruction
case object DNEG  extends JVMInstruction
case object ISHL  extends JVMInstruction
case object LSHL  extends JVMInstruction
case object ISHR  extends JVMInstruction
case object LSHR  extends JVMInstruction
case object IUSHR extends JVMInstruction
case object LUSHR extends JVMInstruction
case object IAND  extends JVMInstruction
case object LAND  extends JVMInstruction
case object IOR   extends JVMInstruction
case object LOR   extends JVMInstruction
case object IXOR  extends JVMInstruction
case object LXOR  extends JVMInstruction
case class IINC(vindex:Int, value:Byte) extends JVMInstruction

// Conversions
case object I2L  extends JVMInstruction
case object I2F  extends JVMInstruction
case object I2D  extends JVMInstruction
case object L2I  extends JVMInstruction
case object L2F  extends JVMInstruction
case object L2D  extends JVMInstruction
case object F2I  extends JVMInstruction
case object F2L  extends JVMInstruction
case object F2D  extends JVMInstruction
case object D2I  extends JVMInstruction
case object D2L  extends JVMInstruction
case object D2F  extends JVMInstruction
case object I2B  extends JVMInstruction
case object I2C  extends JVMInstruction
case object I2S  extends JVMInstruction

// Comparisons

case object LCMP  extends JVMInstruction
case object FCMPL extends JVMInstruction
case object FCMPG extends JVMInstruction
case object DCMPL extends JVMInstruction
case object DCMPG extends JVMInstruction
case class IFEQ(label: String) extends JVMInstruction
case class IFNE(label: String) extends JVMInstruction
case class IFLT(label: String) extends JVMInstruction
case class IFGE(label: String) extends JVMInstruction
case class IFGT(label: String) extends JVMInstruction
case class IFLE(label: String) extends JVMInstruction

case class IF_ICMPEQ(label: String) extends JVMInstruction
case class IF_ICMPNE(label: String) extends JVMInstruction
case class IF_ICMPLT(label: String) extends JVMInstruction
case class IF_ICMPGE(label: String) extends JVMInstruction
case class IF_ICMPGT(label: String) extends JVMInstruction
case class IF_ICMPLE(label: String) extends JVMInstruction
case class IF_ACMPEQ(label: String) extends JVMInstruction
case class IF_ACMPNE(label: String) extends JVMInstruction

// Control
case class GOTO(label: String)  extends JVMInstruction
case class JSR(label: String)   extends JVMInstruction
case object RET                 extends JVMInstruction
case class  TABLESWITCH(min:Int, max:Int, dflt:String, labels:List[String]) extends JVMInstruction
case class  LOOKUPSWITCH(dflt:String, keys:List[Int], labels:List[String]) extends JVMInstruction
case object IRETURN extends JVMInstruction
case object LRETURN extends JVMInstruction
case object FRETURN extends JVMInstruction
case object DRETURN extends JVMInstruction
case object ARETURN extends JVMInstruction
case object RETURN  extends JVMInstruction

// References
case class GETSTATIC(owner: String, name:String, desc:String) extends JVMInstruction
case class PUTSTATIC(owner: String, name:String, desc:String) extends JVMInstruction
case class GETFIELD(owner: String, name:String, desc:String) extends JVMInstruction
case class PUTFIELD(owner: String, name:String, desc:String) extends JVMInstruction
case class INVOKEVIRTUAL(owner: String, name:String, desc:String) extends JVMInstruction
case class INVOKESPECIAL(owner: String, name:String, desc:String) extends JVMInstruction
case class INVOKESTATIC(owner: String, name:String, desc:String) extends JVMInstruction
case class INVOKEINTERFACE(owner: String, name:String, desc:String) extends JVMInstruction
case class INVOKEDYNAMIC(owner: String, name:String, desc:String) extends JVMInstruction
case class NEW(desc:String) extends JVMInstruction
case class NEWARRAY(t:Int) extends JVMInstruction
case class ANEWARRAY(desc: String) extends JVMInstruction
case object ARRAYLENGTH extends JVMInstruction
case object ATHROW extends JVMInstruction
case class CHECKCAST(desc: String) extends JVMInstruction
case class INSTANCEOF(desc: String) extends JVMInstruction
case object MONITORENTER extends JVMInstruction
case object MONITOREXIT extends JVMInstruction

// Extended
case object WIDE extends JVMInstruction
case class MULTIANEWARRAY(desc:String, dimensions:Int) extends JVMInstruction
case class IFNULL(label: String) extends Branch
case class IFNONNULL(label: String) extends Branch
case class GOTO_W(label: String) extends Branch
case class JSR_W(label: String) extends Branch

// Reserved
case object BREAKPOINT extends JVMInstruction
case object IMPDEP1 extends JVMInstruction
case object IMPDEP2 extends JVMInstruction
