/*

Name: Jake Schinto
Lehigh Email: jjs220@lehigh.edu
Description: Adding Set Functionality

*/
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._
import scala.collection.JavaConverters._
import java.io._

object AsaCompiler extends App {
  val input = CharStreams.fromFileName(args(0))
  val lexer = new AsaLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new AsaParser(tokens); 

  println("Parsing...")
  val cst = parser.program()
  if (parser.getNumberOfSyntaxErrors() < 1) {
    val astbuilder = new ASTBuilder()
    println("Building AST...")
    val ast = astbuilder.visit(cst)

    val Program(programName, _) = ast // Extract the program name from the AST for use in the class and file name

    val cstw = new PrintWriter(new File(programName + ".cstx"))
    cstw.println(PrettyPrinter.format(cst.toStringTree(parser)))
    cstw.close

    val astw = new PrintWriter(new File(programName + ".astx"))
    astw.println(PrettyPrinter.format(ast.toString))
    astw.close

    try {
      // Perform semantic analysis on the instruction list for undefined variables, type compatibility, etc.
      println("Semantic Analysis...")
      val info = new SemanticAnalyzer(ast).analyze

      // Dump the symbol table to an html page for easy viewing
      val stw = new PrintWriter(new File(programName + ".html"))
      SymbolTable.dump(stw)
      stw.close

      // Traverse the AST and generate Java byte code instructions
      val codegen = new CodeGenerator(ast, info)
      println("Generating Code...")
      codegen.gen
      val bcw = new PrintWriter(new File(programName + ".bcx"))
      codegen.dump(bcw)
      bcw.close
      val ilist = codegen.code

      // Generate bytecodes from the list of instructions
      val byteCodes = ClassFile.generateBytecodes(ilist)

      // Write out the generated class to a .class file 
      var out = new FileOutputStream(programName + ".class")
      out.write(byteCodes)
      out.close

      // Create a Java classloader for loading the generated class.
      val myClassLoader = new MyClassLoader()

      // Use the classloader to load the generated class into the 
      // JVM and execute the main method.
      val klass = myClassLoader.defineMyClass(programName, byteCodes); 
      val main = klass.getDeclaredMethod("main", classOf[Array[String]])
      main.setAccessible(true)
      println("Running Program...")
      main.invoke(null, null)
    }
    catch {
      case e: Exception => { 
        println("[Error] " + e.getMessage)
        e.printStackTrace
      }
    }
    finally {
    }
  }
}