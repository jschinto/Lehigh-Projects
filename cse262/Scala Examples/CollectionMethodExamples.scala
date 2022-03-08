object CommonCollectionMethods extends App {

/* 
This is a collection of methods commonly used on Scala collections. Will be added
to over time.
Author: J Femister
Version: 1.0
Date: 9/20/17
*/

/*
    Concatenating the elements in a collection into a String - mkString
*/

// No separator between elements
println(List("one", "two", "three", "four").mkString)

// Comma between elements
println(Set("red", "green", "blue").mkString(","))

// Comma separator and brackets on the ends
println(List("one", "two", "three", "four").mkString("[", ",", "]"))

/*
    Applying a test to every element in a collection and returning true if the test passes
    for all the elements - forall
*/

// Test for all elements in list being even.
println(List(2, 4, 6, 8, 10).forall( x => x % 2 == 0 ))
println(List(1, 2, 4, 6, 8, 10).forall( x => x % 2 == 0 ))

/*
    Applying a test to every element in a collection and returning true if the test passes
    for <em>at least one of</em> the elements - exists
*/

// Test for at least one of the elements in list being odd
println(List(2, 4, 6, 7).exists( x => x % 2 == 1 ))

/* 
    Transforming a collection into another collection by applying a function to 
    each element - map
*/

// Doubling the values in a collection
val nums = List(1, 2, 3, 4)
val numsx2 = nums.map( x => 2 * x )
println(numsx2)

// Turning a list of Strings into a list of their lengths
println(List("alpha", "beta", "gamma", "delta", "epsilon").map( x => x.length))

/*
    Creating a new collection by selecting elements from another collection 
    - filter/filterNot
*/

// Filtering the odd and even numbers from a Range of Ints
val isOdd = (x:Int) => x % 2 == 1
val numbs = 1 to 10
val odds = numbs.filter(isOdd)
val evens = numbs.filterNot(isOdd)
println(s"odds= $odds")
println(s"evens= $evens")

/*
    Reducing a list into a single value by applying a function to the elements of 
    the list.
*/

// Adding up the elements in a list - sum
println(List(1, 2, 3, 4).sum)
// or
println(List(1, 2, 3, 4).foldLeft(0)(_ + _))


// Multiplying together the elements in a list - product
println(List(1, 2, 3, 4).product)
// or
println(List(1, 2, 3, 4).foldLeft(1)(_ * _))

// Finding the largest element in a list - max
println(List(3, 2, 1, 400, 7, 9, 8).max)
// or
println(List(3, 2, 1, 400, 7, 9, 8).reduceLeft(_ max _))

// Finding the smallest element in a list - min
println(List(900, 800, 300, 4, 500, 600, 100, 200).min)
// or
println(List(900, 800, 300, 4, 500, 600, 100, 200).reduceLeft(_ min _))

// Counting the elements in a list that pass a test - count
println(List(1, 2, 3, 4, 5, 6, 7, 8).count(x => x > 5))
// or
println(List(1, 2, 3, 4, 5, 6, 7, 8).filter(x => x > 5).map( x => 1 ).sum)

/*
    Fun with lists - combining lists in various ways
*/

// Create New List by Combining Lists in Sequence
println(List("one", "two", "three", "four", "five") ++ List("six", "seven", "eight", "nine", "ten"))
println(List("one", "two", "three", "four", "five") ::: List("six", "seven", "eight", "nine", "ten"))

// Prepend an element to a list
println("zero" :: List("one", "two", "three", "four", "five"))

// Create New List by Dropping Elements From List
println(List("one", "two", "three", "four", "five").drop(2)) // drop first 2
println(List("one", "two", "three", "four", "five").dropRight(2)) // drop last 2
println(List(1, 2, 3, 4, 5).dropWhile(_ <= 2)) // keep dropping as long as <= 2

// Generate Combinations/Permutations of List
List(1, 2, 3).combinations(2).foreach(println) // every combo of 2 elements
List(1, 2, 3).permutations.foreach(println) // every permutation of the whole list

// Testing for Elements in List 
println(List("one", "two", "three", "four").contains("three"))
println(List("one", "two", "three", "four").containsSlice(List("two", "three")))

}