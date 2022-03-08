object Matchingexamples  extends App{

def printNum(int: Int) {
  int match {
    case 0 => println("Zero")
    case 1 => println("One")
    case _ => println("more than one")
  }
}

def printNum2(int: Int) {
  int match {
   // case _ => println("more than one")
    case 0 => println("Zero")
    case 1 => println("One")
  }
}

def fibonacci(in: Int): Int = in match {
  case 0 => 0
  case 1 => 1
  case n => fibonacci(n - 1) + fibonacci(n - 2)
}

def fib2(in: Int): Int = in match {
  case n if n <= 0 => 0
  case 1 => 1
  case n => fib2(n - 1) + fib2(n - 2)
}

val anyList= List(1, "A", 2, 2.5, 'a')

for (m <- anyList) {
	m match { 
	  case i: Int => println("Integer: " + i)
	  case s: String => println("String: " + s)
	  case f: Double => println("Double: " + f)
	  case other => println("other: " + other)
	}
}


def test2(in: Any) = in match {
	case s: String => "String, length " + s.length
	case i: Int if i > 0 => "Natural Int"
	case i: Int => "Another Int"
	case a: AnyRef => a.getClass.getName
	case _ => "null"
}


def sumOdd(in: List[Int]): Int = in match {
case Nil => 0
case x :: rest if x % 2 == 1 => x + sumOdd(rest)
case _ :: rest => sumOdd(rest)
}

def noPairs[T](in: List[T]): List[T] = in match {
	case Nil  => Nil
	case a :: b :: rest if a == b => noPairs(a :: rest)
	case a :: rest => a :: noPairs(rest)
}

def ignore(in : List[String]) : List[String] = in match {
	case Nil => Nil
	case _ :: "ignore" :: rest => ignore(rest)
	case x :: rest => x :: ignore(rest)
}

def getStrings(in: List[Any]) : List[String] = in match {
	case Nil => Nil
	case (s: String) :: rest => s :: getStrings(rest)
	case _ :: rest => getStrings(rest)
}

case class Person(name: String, age: Int, valid: Boolean)

def older(p: Person) : Option[String] = p match {
	case Person(name, age, true) if age > 35 => Some(name)
	case _ => None
}

def test4 {
  anyList.filter( a => a match {
  	case s: String => true
  	case _ => false
  })


  anyList.filter {
  	case s: String => true
  	case _ => false  	
  }
}



val fpattern = 
"""(\w+)\s+(.+)\s+(\*?pts\/\d+|tty\d+)\s+(\d+d?|\d+\:\d+|)\s+(\w+\s+\d+\s+\d+\:\d+)\s+(\([0-9a-zA-Z.:/]+\))""".r

_ match { 
	           case fpattern(login, name, tty, idle, loginTime, office) => 
		       (host, login.trim, name.trim, tty.trim, idle.trim, 
                          loginTime.trim.replaceAll("  "," "), office.trim)
		   //case line => println("error: " + line)
                 }

*/

}