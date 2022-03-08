import scala.collection.immutable.Range
import java.io._

object IdentKind extends Enumeration {
  type IdentKind = Value
  val CONST_KIND, VAR_KIND = Value
}

import IdentKind._

case class SymbolTableEntry(name:String, kind:IdentKind, typ:AsaType, value:Any, vindex:Int)

object SymbolTable {
  var table = Map[String,SymbolTableEntry]()
  var vindex = 1

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

  def dump(pw:PrintWriter) = {
    pw.println("<html><head></head><body>")
    pw.println("<table border='2'><thead><tr><th colspan='5'>Symbol Table</th></tr>")
    pw.println("<tr><th>NAME</th><th>KIND</th><th>TYPE</th><th>VALUE</th><th>VINDEX</th></tr></thead><tbody>")
    table.foreach((e) => {
      val (k,v) = e;
      pw.println(s"<tr><td>${v.name}</td><td>${v.kind}</td><td>${v.typ}</td><td>${v.value}</td><td>${v.vindex}</td></tr>")
    })
    pw.println("</tbody></table></body></html>")
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