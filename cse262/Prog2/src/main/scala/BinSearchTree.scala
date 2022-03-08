/*
  Jake Schinto
*/
import scala.io.Source

case class Node(key:String, value:String, var left:Node, var right:Node)
object BinSearchTree extends App {
  val tree = Node("abc", "1", Node("aaa", "2", null, null), Node("bbb", "3", null, null))

  def find(key:String, tree:Node):Option[String] = {
    key match {
      case tree.key => Some(tree.value)
      case n if (n < tree.key) && (tree.left != null) => find(key, tree.left)
      case n if (n > tree.key) && (tree.right != null) => find(key, tree.right)
      case _ => None
    }
  }

  def add(key:String, value:String, tree:Node):Option[String] = {
    key match {
      case n if (n < tree.key) && (tree.left != null) => add(key, value, tree.left)
      case n if (n < tree.key) => {tree.left = Node(key, value, null, null); Some(key)}
      case n if (n > tree.key) && (tree.right != null) => add(key, value, tree.right)
      case n if (n > tree.key) => {tree.right = Node(key, value, null, null); Some(key)}
      case _ => None
    }
  }
  
  if (args.length >= 2) {
    val list = Source.fromFile(args(0)).getLines().toList.map(line => line.split(' ')).toList
    val list2 = Source.fromFile(args(1)).getLines().toList
    list.foreach(pair => add(pair.head, pair.tail.head, tree))
    list2.foreach(word => {val printVal = find(word, tree); if(printVal == None) {println("NONE")} else {println(printVal.head)}})
  }
}