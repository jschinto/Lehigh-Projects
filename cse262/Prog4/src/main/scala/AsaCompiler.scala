import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._
import scala.collection.JavaConverters._
import java.io._

object AsaCompiler extends App {
  val input = CharStreams.fromFileName(args(0))
  val lexer = new AsaLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new AsaParser(tokens);
  val parsetree = parser.program()
  println()
  println("===== CST ======")
  println(parsetree.toStringTree(parser))
  val builder = new ASTBuilder()
  val ast = builder.visit(parsetree)
  println()
  println("====== AST ======")
  println(ast)
  val Program(programName, _) = ast // Extract the program name from the AST for use in the class and file name

  val info = new SemanticAnalyzer(ast).analyze

  println("\n")
  SymbolTable.dump()
  println("\n")

  val codegen = new CodeGenerator(ast, info)
  codegen.gen
  codegen.dump
  val ilist = codegen.code

  // Create a Java classloader for loading the generated class.
  val myClassLoader = new MyClassLoader()

  // Generate bytecodes from the list of instructions
  val b = ClassFile.generateBytecodes(ilist)

  // Write out the generated class to a .class file
  var out = new FileOutputStream(programName + ".class")
  for (c <- b) {
    out.write(c)
  }
  out.close

  // Use the classloader to load the generated class into the 
  // JVM and execute the main method.
  val c = myClassLoader.defineMyClass(programName, b);
  val main = c.getDeclaredMethod("main", classOf[Array[String]])
  main.setAccessible(true)
  main.invoke(null, null)
}