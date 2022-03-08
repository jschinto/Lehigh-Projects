/* 
  object ClassFile

  This object is used to generate an array of Java bytecodes using the ASM4 bytecode manipulation library.
  @Author James A. Femister
*/

import scala.collection.JavaConverters._
import org.objectweb.asm.{AnnotationVisitor, Attribute, ClassReader, ClassWriter, ConstantDynamic, FieldVisitor, 
Handle, Label, MethodVisitor, Opcodes, Type=>XType, TypePath}
import org.objectweb.asm.Opcodes._
import scala.collection.mutable.Map
import scala.language.postfixOps

object ClassFile {
  val T_BOOLEAN = 4
  val T_CHAR = 5
  val T_FLOAT = 6
  val T_DOUBLE = 7
  val T_BYTE = 8
  val T_SHORT = 9
  val T_INT = 10
  val T_LONG = 11

  val typeMap = Map[AsaType,Int](
    INTEGER_TYPE -> T_INT,
    REAL_TYPE -> T_DOUBLE, 
    BOOLEAN_TYPE -> T_BOOLEAN
  )

  def generateBytecodes(ilist:List[JVMNode]):Array[Byte] = {
    val classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES)
    var methodVisitor:MethodVisitor = null
    val labelmap = Map[String,Label]()
    var frameCount = 0

    def genIns(ins:JVMNode) = {

      def vi(op:Int) = {
        methodVisitor.visitInsn(op)
      }

      def vvi(op:Int, p:Int) = {
        methodVisitor.visitVarInsn(op, p);
      }

      def vii(op:Int, valu:Int) = {
        methodVisitor.visitIntInsn(op, valu);
      }

      def vji(op:Int, label:String) = {
        methodVisitor.visitJumpInsn(op, labelmap(label))
      }

      def vfi(op:Int, owner: String, name:String, desc:String) = {
        methodVisitor.visitFieldInsn(op, owner, name, desc);
      }

      def vmi(op:Int, owner: String, name:String, desc:String) = {
        methodVisitor.visitMethodInsn(op, owner, name, desc, false);
      }

      def vl(name:String) = {
        methodVisitor.visitLabel(labelmap(name))
      }

      def vti(op:Int, typ:String) = {
        methodVisitor.visitTypeInsn(op, typ);
      }

      def vlsi(dflt:String, keys:List[Int], labels:List[String]) = {
        val (sortedKeys, sortedLabs) = (keys zip labels) sortWith( (a1,a2) => a1._1 < a2._1 ) unzip
        val keyArray = sortedKeys.toArray
        val labelArray = sortedLabs.toArray.map(labelmap(_))
        methodVisitor.visitLookupSwitchInsn(labelmap(dflt), keyArray, labelArray);
      }

      def vtsi(min:Int, max:Int, dflt:String, labels:List[String]) = {
        val labelArray = labels.toArray.map(labelmap(_))
        methodVisitor.visitTableSwitchInsn(min, max, labelmap(dflt), labelArray: _*)
      }

      def error() = {
        throw new Exception("Unimplemented instruction.")
      }

      ins match {
        // Directives
        case CLASS(fullName:String, modifiers:Set[ClassModifiers.Value]) => {
          val programName = fullName
          classWriter.visit(V1_8, ACC_SUPER, programName, null, "java/lang/Object", null)
          classWriter.visitSource(programName + ".asa", null)

          methodVisitor = classWriter.visitMethod(0, "<init>", "()V", null, null)
          methodVisitor.visitCode()
          val label0 = new Label()
          methodVisitor.visitLabel(label0)
          methodVisitor.visitLineNumber(1, label0)
          methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
          methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
          methodVisitor.visitInsn(Opcodes.RETURN)
          methodVisitor.visitMaxs(1, 1)
          methodVisitor.visitEnd()

          methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
          methodVisitor.visitCode()

        }
        case END_CLASS => classWriter.visitEnd
        case FIELD(name:String, modifiers:Set[FieldModifiers.Value], value:String) => error
        case METHOD(name:String, modifiers:Set[MethodModifiers.Value], typ:String) => //error
        case END_METHOD => {
          methodVisitor.visitMaxs(0, 0)
          methodVisitor.visitEnd
        }
        case SUPER(name:String) => error

        case LABEL(name:String) => vl(name)

        // Constants
        case NOP => vi(Opcodes.NOP) 
        case ACONST_NULL => vi(Opcodes.ACONST_NULL) 
        case ICONST_M1 => vi(Opcodes.ICONST_M1) 
        case ICONST_0 => vi(Opcodes.ICONST_0)
        case ICONST_1 => vi(Opcodes.ICONST_1)
        case ICONST_2 => vi(Opcodes.ICONST_2)
        case ICONST_3 => vi(Opcodes.ICONST_3)
        case ICONST_4 => vi(Opcodes.ICONST_4)
        case ICONST_5 => vi(Opcodes.ICONST_5)
        case LCONST_0 => vi(Opcodes.LCONST_0)
        case LCONST_1 => vi(Opcodes.LCONST_1)
        case FCONST_0 => vi(Opcodes.FCONST_0)
        case FCONST_1 => vi(Opcodes.FCONST_1)
        case FCONST_2 => vi(Opcodes.FCONST_2)
        case DCONST_0 => vi(Opcodes.DCONST_0)
        case DCONST_1 => vi(Opcodes.DCONST_1)
        case BIPUSH(cval: Byte) => vii(Opcodes.BIPUSH, cval)
        case SIPUSH(cval: Short) => vii(Opcodes.SIPUSH, cval)

        case LDC(cval: Any) => methodVisitor.visitLdcInsn(cval)
        case LDC_W(cval: Any) => methodVisitor.visitLdcInsn(cval)
        case LDC2_W(cval: Any) => methodVisitor.visitLdcInsn(cval)

        // Loads
        case ILOAD(vindex: Int) => vvi(Opcodes.ILOAD, vindex)
        case LLOAD(vindex: Int) => vvi(Opcodes.LLOAD, vindex)
        case FLOAD(vindex: Int) => vvi(Opcodes.FLOAD, vindex)
        case DLOAD(vindex: Int) => vvi(Opcodes.DLOAD, vindex)
        case ALOAD(vindex: Int) => vvi(Opcodes.ALOAD, vindex)
        case ILOAD_0 => vvi(Opcodes.ILOAD, 0)
        case ILOAD_1 => vvi(Opcodes.ILOAD, 1)
        case ILOAD_2 => vvi(Opcodes.ILOAD, 2)
        case ILOAD_3 => vvi(Opcodes.ILOAD, 3)
        case LLOAD_0 => vvi(Opcodes.LLOAD, 0)
        case LLOAD_1 => vvi(Opcodes.LLOAD, 1)
        case LLOAD_2 => vvi(Opcodes.LLOAD, 2)
        case LLOAD_3 => vvi(Opcodes.LLOAD, 3)
        case FLOAD_0 => vvi(Opcodes.FLOAD, 0)
        case FLOAD_1 => vvi(Opcodes.FLOAD, 1)
        case FLOAD_2 => vvi(Opcodes.FLOAD, 2)
        case FLOAD_3 => vvi(Opcodes.FLOAD, 3)
        case DLOAD_0 => vvi(Opcodes.DLOAD, 0)
        case DLOAD_1 => vvi(Opcodes.DLOAD, 1)
        case DLOAD_2 => vvi(Opcodes.DLOAD, 2)
        case DLOAD_3 => vvi(Opcodes.DLOAD, 3)
        case ALOAD_0 => vvi(Opcodes.ALOAD, 0)
        case ALOAD_1 => vvi(Opcodes.ALOAD, 1)
        case ALOAD_2 => vvi(Opcodes.ALOAD, 2)
        case ALOAD_3 => vvi(Opcodes.ALOAD, 3)
        case IALOAD => vi(Opcodes.IALOAD)
        case LALOAD => vi(Opcodes.LALOAD)
        case FALOAD => vi(Opcodes.FALOAD) 
        case DALOAD => vi(Opcodes.DALOAD)
        case AALOAD => vi(Opcodes.AALOAD) 
        case BALOAD => vi(Opcodes.BALOAD) 
        case CALOAD => vi(Opcodes.CALOAD) 
        case SALOAD => vi(Opcodes.SALOAD) 

        // Stores
        case ISTORE(vindex: Int) => vvi(Opcodes.ISTORE, vindex)
        case LSTORE(vindex: Int) => vvi(Opcodes.LSTORE, vindex)
        case FSTORE(vindex: Int) => vvi(Opcodes.FSTORE, vindex)
        case DSTORE(vindex: Int) => vvi(Opcodes.DSTORE, vindex)
        case ASTORE(vindex: Int) => vvi(Opcodes.ASTORE, vindex)
        case ISTORE_0 => vvi(Opcodes.ISTORE, 0)
        case ISTORE_1 => vvi(Opcodes.ISTORE, 1)
        case ISTORE_2 => vvi(Opcodes.ISTORE, 2)
        case ISTORE_3 => vvi(Opcodes.ISTORE, 3)
        case LSTORE_0 => vvi(Opcodes.LSTORE, 0)
        case LSTORE_1 => vvi(Opcodes.LSTORE, 1)
        case LSTORE_2 => vvi(Opcodes.LSTORE, 2)
        case LSTORE_3 => vvi(Opcodes.LSTORE, 3)
        case FSTORE_0 => vvi(Opcodes.FSTORE, 0)
        case FSTORE_1 => vvi(Opcodes.FSTORE, 1)
        case FSTORE_2 => vvi(Opcodes.FSTORE, 2)
        case FSTORE_3 => vvi(Opcodes.FSTORE, 3)
        case DSTORE_0 => vvi(Opcodes.DSTORE, 0)
        case DSTORE_1 => vvi(Opcodes.DSTORE, 1)
        case DSTORE_2 => vvi(Opcodes.DSTORE, 2)
        case DSTORE_3 => vvi(Opcodes.DSTORE, 3)
        case ASTORE_0 => vvi(Opcodes.ASTORE, 0)
        case ASTORE_1 => vvi(Opcodes.ASTORE, 1)
        case ASTORE_2 => vvi(Opcodes.ASTORE, 2)
        case ASTORE_3 => vvi(Opcodes.ASTORE, 3)
        case IASTORE => vi(Opcodes.IASTORE)
        case LASTORE => vi(Opcodes.LASTORE)
        case FASTORE => vi(Opcodes.FASTORE)
        case DASTORE => vi(Opcodes.DASTORE)
        case AASTORE => vi(Opcodes.AASTORE)
        case BASTORE => vi(Opcodes.BASTORE)
        case CASTORE => vi(Opcodes.CASTORE)
        case SASTORE => vi(Opcodes.SASTORE)

        // Stack
        case POP => vi(Opcodes.POP)     
        case POP2 => vi(Opcodes.POP2)   
        case DUP => vi(Opcodes.DUP)    
        case DUP_X1 => vi(Opcodes.DUP_X1) 
        case DUP_X2 => vi(Opcodes.DUP_X2) 
        case DUP2 => vi(Opcodes.DUP2)   
        case DUP2_X1 => vi(Opcodes.DUP2_X1)
        case DUP2_X2 => vi(Opcodes.DUP2_X2)
        case SWAP => vi(Opcodes.SWAP)   

        // Math
        case IADD => vi(Opcodes.IADD) 
        case LADD => vi(Opcodes.LADD) 
        case FADD => vi(Opcodes.FADD) 
        case DADD => vi(Opcodes.DADD) 
        case ISUB => vi(Opcodes.ISUB) 
        case LSUB => vi(Opcodes.LSUB) 
        case FSUB => vi(Opcodes.FSUB) 
        case DSUB => vi(Opcodes.DSUB) 
        case IMUL => vi(Opcodes.IMUL) 
        case LMUL => vi(Opcodes.LMUL) 
        case FMUL => vi(Opcodes.FMUL) 
        case DMUL => vi(Opcodes.DMUL) 
        case IDIV => vi(Opcodes.IDIV) 
        case LDIV => vi(Opcodes.LDIV) 
        case FDIV => vi(Opcodes.FDIV) 
        case DDIV => vi(Opcodes.DDIV) 
        case IREM => vi(Opcodes.IREM) 
        case LREM => vi(Opcodes.LREM) 
        case FREM => vi(Opcodes.FREM) 
        case DREM => vi(Opcodes.DREM) 
        case INEG => vi(Opcodes.INEG) 
        case LNEG => vi(Opcodes.LNEG) 
        case FNEG => vi(Opcodes.FNEG) 
        case DNEG => vi(Opcodes.DNEG) 
        case ISHL => vi(Opcodes.ISHL) 
        case LSHL => vi(Opcodes.LSHL) 
        case ISHR => vi(Opcodes.ISHR) 
        case LSHR => vi(Opcodes.LSHR) 
        case IUSHR => vi(Opcodes.IUSHR) 
        case LUSHR => vi(Opcodes.LUSHR) 
        case IAND => vi(Opcodes.IAND) 
        case LAND => vi(Opcodes.LAND) 
        case IOR => vi(Opcodes.IOR) 
        case LOR => vi(Opcodes.LOR)  
        case IXOR => vi(Opcodes.IXOR) 
        case LXOR => vi(Opcodes.LXOR) 
        case IINC(vindex:Int, value:Byte) =>  methodVisitor.visitIincInsn(vindex,value)

        // Conversions
        case I2L => vi(Opcodes.I2L) 
        case I2F => vi(Opcodes.I2F) 
        case I2D => vi(Opcodes.I2D) 
        case L2I => vi(Opcodes.L2I) 
        case L2F => vi(Opcodes.L2F) 
        case L2D => vi(Opcodes.L2D) 
        case F2I => vi(Opcodes.F2I) 
        case F2L => vi(Opcodes.F2L) 
        case F2D => vi(Opcodes.F2D) 
        case D2I => vi(Opcodes.D2I) 
        case D2L => vi(Opcodes.D2L) 
        case D2F => vi(Opcodes.D2F) 
        case I2B => vi(Opcodes.I2B) 
        case I2C => vi(Opcodes.I2C) 
        case I2S => vi(Opcodes.I2S) 

        // Comparisons
        case LCMP => vi(Opcodes.LCMP) 
        case FCMPL => vi(Opcodes.FCMPL)
        case FCMPG => vi(Opcodes.FCMPG)
        case DCMPL => vi(Opcodes.DCMPL)
        case DCMPG => vi(Opcodes.DCMPG)
        case IFEQ(label: String) => vji(Opcodes.IFEQ, label)
        case IFNE(label: String) => vji(Opcodes.IFNE, label)
        case IFLT(label: String) => vji(Opcodes.IFLT, label)
        case IFGE(label: String) => vji(Opcodes.IFGE, label)
        case IFGT(label: String) => vji(Opcodes.IFGT, label)
        case IFLE(label: String) => vji(Opcodes.IFLE, label)

        case IF_ICMPEQ(label: String) => vji(Opcodes.IF_ICMPEQ, label)
        case IF_ICMPNE(label: String) => vji(Opcodes.IF_ICMPNE, label)
        case IF_ICMPLT(label: String) => vji(Opcodes.IF_ICMPLT, label)
        case IF_ICMPGE(label: String) => vji(Opcodes.IF_ICMPGE, label)
        case IF_ICMPGT(label: String) => vji(Opcodes.IF_ICMPGT, label)
        case IF_ICMPLE(label: String) => vji(Opcodes.IF_ICMPLE, label)
        case IF_ACMPEQ(label: String) => vji(Opcodes.IF_ACMPEQ, label)
        case IF_ACMPNE(label: String) => vji(Opcodes.IF_ACMPNE, label)

        // Control
        case GOTO(label: String) => vji(Opcodes.GOTO, label) 
        case JSR(label: String) => vji(Opcodes.JSR, label)  
        case RET => vi(Opcodes.RET)              
        case TABLESWITCH(min:Int, max:Int, dflt:String, labels:List[String]) => vtsi(min, max, dflt, labels)    
        case LOOKUPSWITCH(dflt:String, keys:List[Int], labels:List[String]) => vlsi(dflt, keys, labels)  
        case IRETURN => vi(Opcodes.IRETURN) 
        case LRETURN => vi(Opcodes.LRETURN) 
        case FRETURN => vi(Opcodes.FRETURN) 
        case DRETURN => vi(Opcodes.DRETURN) 
        case ARETURN => vi(Opcodes.ARETURN) 
        case RETURN =>  vi(Opcodes.RETURN) 

        // References
        case GETSTATIC(owner: String, name:String, desc:String) => vfi(Opcodes.GETSTATIC, owner, name, desc)
        case PUTSTATIC(owner: String, name:String, desc:String) => vfi(Opcodes.PUTSTATIC, owner, name, desc)
        case GETFIELD(owner: String, name:String, desc:String) => vfi(Opcodes.GETFIELD, owner, name, desc)
        case PUTFIELD(owner: String, name:String, desc:String) => vfi(Opcodes.PUTFIELD, owner, name, desc)
        case INVOKEVIRTUAL(owner: String, name:String, desc:String) => vmi(Opcodes.INVOKEVIRTUAL, owner, name, desc)
        case INVOKESPECIAL(owner: String, name:String, desc:String) => vmi(Opcodes.INVOKESPECIAL, owner, name, desc)
        case INVOKESTATIC(owner: String, name:String, desc:String) => vmi(Opcodes.INVOKESTATIC, owner, name, desc)
        case INVOKEINTERFACE(owner: String, name:String, desc:String) => vmi(Opcodes.INVOKEINTERFACE, owner, name, desc)
        case INVOKEDYNAMIC(owner: String, name:String, desc:String) => error
        case NEW(desc:String) => vti(Opcodes.NEW, desc) 
        case NEWARRAY(t:Int) => vii(Opcodes.NEWARRAY, t)
        case ANEWARRAY(desc: String) => vti(Opcodes.ANEWARRAY, desc) 
        case ARRAYLENGTH => vi(Opcodes.ARRAYLENGTH)
        case ATHROW => vi(Opcodes.ATHROW)
        case CHECKCAST(desc: String) => vti(Opcodes.CHECKCAST, desc) 
        case INSTANCEOF(desc: String) => vti(Opcodes.INSTANCEOF, desc) 
        case MONITORENTER => vi(Opcodes.MONITORENTER)
        case MONITOREXIT => vi(Opcodes.MONITOREXIT)

        // Extended
        case WIDE => error
        case MULTIANEWARRAY(desc:String, dimensions:Int) => methodVisitor.visitMultiANewArrayInsn(desc, dimensions)
        case IFNULL(label: String) => vji(Opcodes.IFNULL, label)
        case IFNONNULL(label: String) => vji(Opcodes.IFNONNULL, label)
        case GOTO_W(label: String) => error
        case JSR_W(label: String) => error

        // Reserved
        case BREAKPOINT => error
        case IMPDEP1 => error
        case IMPDEP2 => error
      }
    }

    def resolveLabels() = {
      ilist.foreach( (i:JVMNode) => {
        i match {
          case LABEL(name) => {
            labelmap(name) = new Label()
          }
          case _ =>
        }
      })
    }
      
    resolveLabels()
    ilist.foreach((ins:JVMNode) => {
      //println("generating " + ins)
      genIns(ins)
    })  
    classWriter.toByteArray()
  }
}

class MyClassLoader extends ClassLoader {
  def defineMyClass(name:String, b:Array[Byte]) = {
    defineClass(name, b, 0, b.length);
  }
}