/*
    Name: Jake Schinto
    Email: jjs220@lehigh.edu
*/
package wordsearchpuzzle

import scala.io.Source
import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {
    // Read the words from a file into a List[String]
    // Sort the words into descending order by length. You can fit more words in the grid by placing 
    // the large words first.
    val wordList = Source.fromFile(args(0)).getLines().toList.map(_.toUpperCase).sortWith(_.length > _.length)
    val order = 23

    // Print out the list of words.
    printResults(wordList.mkString("\n"))

    // Create a new Puzzle object
    val p = new Puzzle()

    val BLANK = '*' // The character used to fill in the blank (unused) spaces in the answer key
    val grid = Array.fill[Char](order, order)(BLANK) // Create an order x order array of BLANK characters - no for loops needed :)
    val rand = new Random() // Create an object for generating "random" numbers
    val directions = List(0, 1, 2, 3, 4, 5, 6, 7) // The 8 possible directions that a word can be placed in the grid

    // Create a list of pairs containing all the possible starting cells for a word
    val startingCells = (for(
      r <- 0 until order;
      c <- 0 until order
    ) yield (r,c)).toList


    // Attempt to build the puzzle by placing all the words
    if (p.createPuzzle(wordList, rand, startingCells, directions, order, grid, BLANK)) {
      // Success!      
      // Print the answer key
      printResults(p.print(grid))
      // Print a blank line
      printResults("")
      // Fill in the blanks
      val grid2 = p.fillWithRandomLetters(grid, order, BLANK, rand)
      // Print the puzzle
      printResults(p.print(grid2))
    } else {
      // Failure (:
      // If it was close, running it again might succeed since we're using different numbers
      printResults("Can't create puzzle")
    }
  }

  def printResults(input: Any):Unit = {
    input match{
      case text : String => {
        println(text)
      }
      case text : List[_] => {
        text.foreach(println(_))
      }
      case text => {
        println(text.toString)
      }
    }
  }

}
