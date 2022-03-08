import scala.io.Source
import scala.collection.mutable.ListBuffer
object Ngram {
  var wordcount: List[(String, List[String])] = Nil
  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      return
    }
    var list2 = ListBuffer[String]()
    val list = Source.fromFile(args(0)).getLines().toList
    list.foreach(line => line.split(' ').foreach(word => list2 += word.toLowerCase.replaceAll("""[*!?,.;\-':()"`]+""", "")))
    val tokenList = list2.toList.filterNot(_ == "")
    val pairList = tokenList.sliding(2).map(pair => (pair.head, pair.tail.head)).toList
    wordcount = pairList.groupBy(_._1).toList.map(pair => (pair._1, pair._2.map(tp => tp._2).toList)).toList
    wordcount.foreach(word => println(word._1 + " " + mostLikelyNextWord(word._1)))
  }

  def countAll(word: String): Int = {
    return wordcount.find(_._1 == word).head._2.length
  }

  def p1(of: String, given: String): Double = {
    val x = wordcount.find(_._1 == given).head._2.filter(_ == of).length
    val tot = wordcount.find(_._1 == given).head._2.length
    if (tot == 0) {
      return 0
    }
    return (x.toDouble)/(tot.toDouble)
  }

  def mostLikelyNextWord(given: String): String = {
    try{
      return wordcount.find(_._1 == given).head._2.maxBy(p1(_, given))
    }
    catch{
      case e: NoSuchElementException => return ""
    }
  }
}