/*
 * Word Search puzzle class - Scala Version
 * Author: J. Femister
 */

/*
    Name: Jake Schinto
    Email: jjs220@lehigh.edu
*/

package wordsearchpuzzle

import scala.util.Random

class Puzzle() {
  // Convert current grid to List[String]
  def print(grid: Array[Array[Char]]) = grid.map((x:Array[Char]) => x.mkString(" ")).toList

  // Create a new puzzle by adding each of the words on the list to the puzzle.
  def createPuzzle(wordList: List[String], rand: Random, startingCells: List[Tuple2[Int,Int]], directions: List[Int], order: Int, grid: Array[Array[Char]], BLANK: Char) : Boolean = {
    wordList.forall(attemptPlaceWord(_, rand, startingCells, directions, order, grid, BLANK))
  }

  // Fill each BLANK cell in the grid with a random letter. This is how we go from an answer key to a ready to be solved puzzle.
  def fillWithRandomLetters(grid: Array[Array[Char]], order: Int, BLANK: Char, rand: Random) = {
    val grid2 = Array.tabulate[Char](order, order) ( 
      (r,c) => { if (grid(r)(c) == BLANK) ('A' + rand.nextInt(26)).asInstanceOf[Char] else grid(r)(c) } )
    grid2
  }
  
  // Attempt to place the given word in the puzzle, return true if successful, false otherwise.
  def attemptPlaceWord(word:String, rand: Random, startingCells: List[Tuple2[Int,Int]], directions: List[Int], order: Int, grid: Array[Array[Char]], BLANK: Char): Boolean = {

    // Shuffle/randomize the list of starting cell pairs
    val starts = rand.shuffle(startingCells)

    // The attemptPlaceWord method succeeds if there is some starting row/col for which we are able
    // to place the word.
    starts.exists( (x) => {val (row,col) = x; checkAndPlaceAt(row, col, rand, directions, word, order, grid, BLANK)} )
  }  

  // Check to see if the given word can be placed at a particular row/column
  def checkAndPlaceAt(row:Int, col:Int, rand: Random, directions: List[Int], word: String, order: Int, grid: Array[Array[Char]], BLANK: Char) : Boolean = {

  // Shuffle/randomize the list of possible directions to place the word.
    val dirs = rand.shuffle(directions)
      
    // See if the word can be placed in a particular direction, if it can, then copy the letters into the grid.

    // The checkAndPlaceAt method succeeded if there exists a direction for which we were able to 
    // place the word.
    dirs.exists(checkAndPlace(_, row, col, word, order, grid, BLANK))
  }

  def checkAndPlace(idir: Int, row:Int, col:Int, word: String, order: Int, grid: Array[Array[Char]], BLANK: Char): Boolean = {
    val r = row
    val c = col

    // These are the numbers you need to add to the current row and column to get to the next cell in a given direction:
    // For example, you need to add 1 to the row and -1 to the column to find the next cell in direction 3
    //                      0  1  2   3   4   5   6   7
    val deltaRow =    Array(0, 1, 1,  1,  0, -1, -1, -1)
    val deltaColumn = Array(1, 1, 0, -1, -1, -1,  0,  1)

    // For each letter in the word
    for (i <- 0 to word.length-1) {

      // If the current row and column are off the grid, then fail
      if (trans(r, i, idir, deltaRow) < 0 || trans(c, i, idir, deltaColumn) < 0 || trans(r, i, idir, deltaRow) >= order || trans(c, i, idir, deltaColumn) >= order) {
        return false;
      }

      // If there is already a character in the current grid cell and it doesn't match the character we're trying
      // to place, then fail (words can and often do cross each other in the grid when they share a common character).
      if (grid(trans(r, i, idir, deltaRow))(trans(c, i, idir, deltaColumn)) != word.charAt(i) && grid(trans(r, i, idir, deltaRow))(trans(c, i, idir, deltaColumn)) != BLANK) {
        return false;
      }

      // Update the row and column to the next cell based on the current direction we are trying
    }

    // If we didn't fail, then copy the letters in the word to the grid.
    for (i <- 0 to word.length-1) {
      grid(trans(r, i, idir, deltaRow))(trans(c, i, idir, deltaColumn)) = word.charAt(i);          
    }

    // If we get to here we succeeded
    true
  }

  def trans(input:Int, i: Int, idir: Int, inArray: Array[Int]): Int = {
    input + (inArray(idir) * i)
  }
}