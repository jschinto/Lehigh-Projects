/* RomanNumber
   Class and object to do arithmetic on roman numerals.
   Demonstration of using an implicit class to effect a conversion

   This is a new version that overcomes the + problem by using Scala Symbols. Check out the 
   two examples at the bottom of this file.


   J. Femister
*/


class RomanNumber2(val sval: Symbol) {
  private var rval = 0
  private var lastChar = ' '

  def this(ival:Int) { 
    this('x)
    rval = ival
  }

  private def process(ch : Char) = {
    ch match {
      case 'M' =>  rval += 1000
      case 'D' =>  rval += 500
      case 'C' =>  if (lastChar == 'D'|| lastChar == 'M') 
        rval -= 100 else rval += 100
      case 'L' =>  rval += 50
      case 'X' =>  if (lastChar == 'L'|| lastChar == 'C') 
        rval -= 10 else rval += 10
      case 'V' =>  rval += 5
      case 'I' =>  if (lastChar == 'V'|| lastChar == 'X') 
        rval -= 1 else rval += 1
    }
    lastChar = ch
  }

  sval.name.toUpperCase.reverse.foreach(process)

  override def toString = rval.toString
  val values = Array(1000, 500, 100, 50, 10, 5, 1)
  val letters= Array("M", "D", "C", "L", "X", "V", "I")

  def toRoman() = {
    var tmp = rval
    var out = ""
    for (i <- 0 to 6) {
      val rep = tmp / values(i)
      if (rep > 0) {
        out += letters(i) * rep
	      tmp -= values(i) * rep
      }
    }
    out
  }
}

object RomanNumber2 {
  implicit class ConvertToRoman(sval1: Symbol) {

    private def c(s1:Symbol, s2:Symbol, f: (Int,Int) => Int) = 
      new RomanNumber2(f(new RomanNumber2(s1).rval, new RomanNumber2(s2).rval)).toRoman

    def +(sval2: Symbol) = c(sval1, sval2, (a,b) => a + b)
    def -(sval2: Symbol) = c(sval1, sval2, (a,b) => a - b)
    def *(sval2: Symbol) = c(sval1, sval2, (a,b) => a * b)
    def /(sval2: Symbol) = c(sval1, sval2, (a,b) => a / b)

  }
}


import RomanNumber2._

object TestRoman2 extends App {
  println('CIV * 'III) // prints CCCXII
  println('mmxv + 'IV) // prints MMXVIIII

}
