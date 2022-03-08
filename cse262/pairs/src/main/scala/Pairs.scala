/* 
  Examples of pairing elements from a list.
*/

object Pairs extends App {

val lst = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

// Creating a list of pairs "by hand"
var pairs1 = List[Tuple2[Int,Int]]()
var i1 = 0
var i2 = lst.head
for (i <- lst.drop(1)) {
  i1 = i2
  i2 = i
  pairs1 = (i1,i2) :: pairs1
}
pairs1 = pairs1.reverse
println(pairs1)

// Creating a list of pairs using the .sliding method
val pairs2 = lst.sliding(2).map( (twoElementList) => (twoElementList.head, twoElementList.tail.head)).toList
println(pairs2)

// Creating a list of pairs using the zip method
val pairs3 = lst.zip(lst.drop(1))
println(pairs3)

}