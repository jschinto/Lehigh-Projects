import scala.collection.immutable.Range

object IdentKind extends Enumeration {
  type IdentKind = Value
  val CONST_KIND, VAR_KIND = Value
}

import IdentKind._

case class SymbolTableEntry(name:String, kind:IdentKind, typ:AsaType, value:Any, vindex:Int)

object SymbolTable {
  var table = Map[String,SymbolTableEntry]()
  var vindex = 0

  def add(name:String, kind:IdentKind, typ:AsaType, value:Any) = {
    kind match {
      case CONST_KIND => table += (name -> SymbolTableEntry(name, kind, typ, value, 0)) 
      case VAR_KIND => {
        table += (name -> SymbolTableEntry(name, kind, typ, value, vindex)) 
        vindex += (if (typ == REAL_TYPE) 2 else 1)
      }
    }
  }
  
  def lookup(name:String):Option[SymbolTableEntry] = {
    if (table contains name) 
      Some(table(name))
    else
      None
  }

  def getVindex(name:String) = {
    val entry = lookup(name)
    entry match {
      case Some(SymbolTableEntry(name, kind, typ, value, vindex)) => Some(vindex)
      case None => None
    }
  }

  def dump() = {
    println("\nSymbol Table")
    println("NAME\tKIND\t\tTYPE\t\tVALUE\tVINDEX")
    table.foreach((e) => {
      val (k,v) = e;
      println(s"${v.name}\t${v.kind}\t${v.typ}\t${v.value}\t${v.vindex}")
    })
  }

  def getVarDefs() = {
    table.filter((e) => {
      val (k,v) = e;
      v.kind == VAR_KIND
    }).map((e) => {
      val (k,v) = e;
      v
    })
  }

}