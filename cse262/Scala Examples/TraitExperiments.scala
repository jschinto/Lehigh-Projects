import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) { super.put(2 * x) }
}


trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

object Main {

	def main(args: Array[String]) = {
		val frog = new Frog()
		frog.philosophize
		println(frog.toString)
	}

}


// Adapted from Beginning Scala by Vishal Layka and David Pollak, Chapt 7

abstract class LivingThing
abstract class Plant extends LivingThing
abstract class Fungus extends LivingThing
abstract class Animal extends LivingThing

trait Philosophical {
  def philosophize() {
    println("I consume memory, therefore I am!")
  }
}

class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green"
}


trait HasLegs extends Animal {
  def walk() { println("Walking") }
}

trait HasWings extends Animal {
  def flap() { println("Flap Flap") }
}

trait Flies {
  this: HasWings =>
    def fly() { println("I'm flying") }
}

abstract class Bird extends Animal with HasWings with HasLegs

class Robin extends Bird with Flies
class Ostrich extends Bird

abstract class Mammal extends Animal {
  def bodyTemperature: Double
}

trait KnowsName extends Animal {
  def name: String
}

class Dog(val name: String) extends Mammal with HasLegs with KnowsName {
  def bodyTemperature: Double = 99.3
}

trait IgnoresName {
  this: KnowsName =>
  def ignoreName(when: String): Boolean

  def currentName(when: String): Option[String] =
    if (ignoreName(when)) None else Some(name)
}

class Cat(val name: String) extends Mammal with HasLegs with KnowsName with IgnoresName {
  def ignoreName(when: String) = when match {
    case "Dinner" => false
    case _ => true
  }
  def bodyTemperature: Double = 99.5
}

trait Athlete extends Animal

trait Runner {
  this: Athlete with HasLegs =>
  def run() { println("I'm running") }
}

class Person(val name: String) extends Mammal with HasLegs with KnowsName {
  def bodyTemperature: Double = 98.6
}

trait Biker extends Person {
  this: Athlete=>
  def ride() { println("I'm riding my bike") }
}

trait Gender
trait Male extends Gender
trait Female extends Gender

