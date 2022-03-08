abstract class AsaType
case object INTEGER_TYPE extends AsaType
case object REAL_TYPE extends AsaType 
case object STRING_TYPE extends AsaType
case object BOOLEAN_TYPE extends AsaType
case object NO_TYPE extends AsaType
case class ARRAY_TYPE(r:Range,t:AsaType) extends AsaType
case class SET_TYPE(t:AsaType) extends AsaType

object TypeKind extends Enumeration {
  type TypeKind = Value
  val PRIMITIVE, COMPOUND = Value
}

import TypeKind._

case class TypeTableEntry(name:String, kind:TypeKind, typ:AsaType)

object TypeTable {
  var table = Map[String,TypeTableEntry]()

  def add(name:String, kind:TypeKind, typ:AsaType) = {
    table += (name -> TypeTableEntry(name, kind, typ)) 
  }

  add("integer", PRIMITIVE, INTEGER_TYPE)
  add("real",    PRIMITIVE, REAL_TYPE)
  add("string",  PRIMITIVE, STRING_TYPE)
  add("boolean", PRIMITIVE, BOOLEAN_TYPE)

  def elementType(node:AsaType) = {
    node match {
      case ARRAY_TYPE(r,t) => t
      case SET_TYPE(t) => t
    }
  }

  def arrayRangeStart(node:AsaType) = {
    val ARRAY_TYPE(r,t) = node
    r.start
  }

  def gettype(name:String) = {
    if (table contains name) 
      table(name).typ
    else
      NO_TYPE
  }

  def createType(ast:ASTNode):AsaType = {
    val Type(typeNodes) = ast
    typeNodes match {
      case NamedType(name) => gettype(name)
      case ArrayType(index, elementType) => ARRAY_TYPE(index, createType(elementType))
      case SetType(elementType) => SET_TYPE(createType(elementType))
    }
  }

  def dump() = {
    println("\nType Table")
    println("NAME\tKIND\t\tTYPE")
    table.foreach((e) => {
      val (k,v) = e;
      println(s"${v.name}\t${v.kind}\t${v.typ}")
    })
  }

}
