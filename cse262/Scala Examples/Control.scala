
object Main {
  var count = 0;
  def fcn = 3
 
  def when(testCondition: => Boolean)( codeBlock: => Unit) {
    if (testCondition)
      codeBlock
  }
 
  def doitTwice(code: => Unit) {
    code
    code
  }
  
  
  def active(code: => Unit) {
    val run = new Runnable { def run() = code }
    new Thread(run).start
  }

   def main(args: Array[String]): Unit = {
     doitTwice { 
      count += 1; println(count); 
     }

     doitTwice { 
      count += 1; println(count) 
     }
   
    //doitTwice(println("hi" + x))
    val x = 4
   
   when (x < 5) {
     println("Hello")
   }

    active { 
      while(true) { println("Thread A"); Thread.sleep(1000) } 
    }
    active { while(true) { println("Thread B"); Thread.sleep(1000) } }
    active { while(true) { println("Thread C"); Thread.sleep(1000) } }
    active { while(true) { println("Thread D"); Thread.sleep(1000) } }
    active { while(true) { println("Thread E"); Thread.sleep(1000) } }


   }
  
}

/*
// Original version with duplicated code

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles
  
  def filesEnding(query: String) =
    for (file <- filesHere; if file.getName.h(query))
      yield file

  def filesContaining(query: String) =
    for (file <- filesHere; if file.getName.contains(query))
      yield file

  def filesRegex(query: String) =
    for (file <- filesHere; if file.getName.matches(query))
      yield file
}


  // What we would like to do but can't  - pass a method to the function
  def filesMatching(query: String, method) =
    for (file <- filesHere; if file.getName.method(query))
      yield file


  // But we can do this - pass a function object
  def filesMatching(query: String,
                  matcher: (String, String) => Boolean) = {
  
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }
    
  // Final version with function objects and closures
  object FileMatcher {
    private def filesHere = (new java.io.File(".")).listFiles
  
    private def filesMatching(matcher: String => Boolean) =
      for (file <- filesHere; if matcher(file.getName))
        yield file
  
    def filesEnding(query: String) =
      filesMatching(_.endsWith(query))
  
    def filesContaining(query: String) =
      filesMatching(_.contains(query))
  
    def filesRegex(query: String) =
      filesMatching(_.matches(query))
  }


  // Reducing code on the client side

  // Original method
  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }

  // Using the exists control abstraction

  def containsNeg(nums: List[Int]) = nums.exists(_ < 0)

  // Doesn't look like a build in control structure

  // Common control pattern - open - use - close

  def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrintWriter (
    new File("date.txt"),
    writer => writer.println(new java.util.Date)
  )

  // We could use curly braces if the withPrintWriter method only had one parameter
  // Currying to the rescue!

  def withPrintWriter(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  // Getting close to a built in control structure
  val file = new File("date.txt")
  
  withPrintWriter(file) { writer.println(new java.util.Date) }

  // Would like to get rid of the writer => part of the function
  // By name parameters to the rescue!
  

//  var count = 0;
//  def fcn = 3
//  
//  def when(testCondition: => Boolean)( codeBlock: => Unit) {
//    if (testCondition)
//      codeBlock
//  }
//  
//  def doitTwice(code: => Unit) {
//    code
//    code
//  }
//    val x = 4
//    
//    when (x < 5) {
//      println("Hello")
//    }
//    
//    doitTwice { count += 1; println(count) }
//    
//    doitTwice(println("hi" + x))
//    
  // def active(code: => Unit) {
  //   val run = new Runnable { def run() = code }
  //   new Thread(run).start
  // }
  
  
  // def main(args: Array[String]): Unit = {
  //   active { 
  //     while(true) { println("Thread A"); Thread.sleep(1000) } 
  //   }
  //   active { while(true) { println("Thread B"); Thread.sleep(1000) } }
  //   active { while(true) { println("Thread C"); Thread.sleep(1000) } }
  //   active { while(true) { println("Thread D"); Thread.sleep(1000) } }
  //   active { while(true) { println("Thread E"); Thread.sleep(1000) } }
  // }
*/