/*

Name: Jake Schinto
Lehigh Email: jjs220@lehigh.edu
Description: JVM byte code for fibonacci sequence

*/

object Prog3 extends App {
  val programName = "Prog3"
  var ilist = List[JVMNode]()

  def add(node:JVMNode) = {  
    ilist = node :: ilist
  }

  add(CLASS("Prog3", Set(ClassModifiers.PUBLIC)))
  add(METHOD("main", Set(MethodModifiers.PUBLIC, MethodModifiers.STATIC), "([Ljava/lang/String;)V"))
//==============================START

  add(ICONST_0)
  add(ISTORE(0))
  add(ICONST_1)
  add(ISTORE(1))

  add(GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;"))
  add(ILOAD(0))
  add(INVOKEVIRTUAL("java/io/PrintStream", "println", "(I)V"))

  add(GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;"))
  add(ILOAD(1))
  add(INVOKEVIRTUAL("java/io/PrintStream", "println", "(I)V"))

  add(LABEL("loop"))

  add(ILOAD(0))
  add(ILOAD(1))
  add(IADD)
  add(ISTORE(2))

  add(GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;"))
  add(ILOAD(2))
  add(INVOKEVIRTUAL("java/io/PrintStream", "println", "(I)V"))

  add(ILOAD(1))
  add(ISTORE(0))
  add(ILOAD(2))
  add(ISTORE(1))

  add(ILOAD(1))
  add(I2D)
  add(ILOAD(0))
  add(I2D)
  add(DDIV)
  add(LDC2_W(1.6180339887))
  add(DSUB)
  add(INVOKESTATIC("java/lang/Math", "abs", "(D)D"))
  add(LDC2_W(0.000000001))
  add(DCMPG)
  add(IFGT("loop"))
  
//==============================END
  add(RETURN)
  add(END_METHOD)
  add(END_CLASS)

  val instructions = ilist.reverse
  
  // Generate bytecodes from the list of instructions
  val b = ClassFile.generateBytecodes(instructions)

  // Create a Java classloader for loading the generated class.
  val myClassLoader = new MyClassLoader()

  // Use the classloader to load the generated class into the 
  // JVM and execute the main method.
  val c = myClassLoader.defineMyClass(programName, b);
  val main = c.getDeclaredMethod("main", classOf[Array[String]])
  main.setAccessible(true)
  main.invoke(null, null)
}