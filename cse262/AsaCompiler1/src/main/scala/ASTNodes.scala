import scala.collection.immutable.Range

abstract class ASTNode
case class Location(line:Int, pos:Int)
// Main program and declaration nodes
case class Program(name:String, blk:ASTNode) extends ASTNode
case class Block(cds:List[ASTNode],vds:List[ASTNode],stp:ASTNode) extends ASTNode
case class ConstDef(name:String, value:ASTNode) extends ASTNode
case class VarDecl(names:List[String], typ:ASTNode) extends ASTNode

// Values
case class Identifier(name:String, loc:Location) extends ASTNode
case class Literal(value:Any, typ:AsaType, loc:Location) extends ASTNode
case class ArrayReference(aname:String,index:ASTNode,loc:Location) extends ASTNode
case class VariableReference(aname:String,loc:Location) extends ASTNode
//case class SetLiteral(values:List[ASTNode]) extends ASTNode
case class SetLiteral(value:Int) extends ASTNode

// Expression nodes
class Expression extends ASTNode
case class Binop(op:String, t1:ASTNode, t2:ASTNode) extends ASTNode
case class Unop(op:String, e:ASTNode) extends ASTNode


// Data type nodes
case class Type(typ:ASTNode) extends ASTNode
case class NamedType(name:String) extends ASTNode
case class ArrayType(index:Range, elementType:ASTNode) extends ASTNode
case object SetType extends ASTNode
//case class SetType(elementType:ASTNode) extends ASTNode
//case class TypeDef(name:String, typ:ASTNode) extends ASTNode
//case class TypeDefPart(tdlist:List[TypeDef]) extends ASTNode

/*
class SimpleType extends PascalType
case class SubrangeType(lowerBound:Constant, upperBound:Constant) extends SimpleType
case class EnumeratedType(values:List[String]) extends SimpleType

case class SetType(baseType:SimpleType) extends PascalType
case class FileType(baseType:PascalType) extends PascalType
case class PointerType(baseType:String) extends PascalType


case class FieldDef(idlist:List[String], typ:PascalType) extends ASTNode
case class FieldList(flist:List[FieldDef]) extends ASTNode
case class RecordType(fields:FieldList) extends PascalType
case class NamedType(name:String) extends PascalType
*/

/*
case class IndexedVariable(name:String, indices:List[ASTNode]) extends ASTNode
case class FieldDesignator(record:String, field:String) extends ASTNode
case class ReferencedVariable(name:ASTNode) extends ASTNode
case class FunctionCall(name:ASTNode, acp:List[ASTNode]) extends ASTNode
case class FileBuffer(name:ASTNode) extends ASTNode
*/
// Statement nodes
case class AssignmentStatement(lhs:ASTNode, rhs:ASTNode) extends ASTNode
case class CompoundStatement(list:List[ASTNode]) extends ASTNode
case class WhileStatement(cond:ASTNode, body:ASTNode) extends ASTNode
case class RepeatStatement(cond:ASTNode, body:List[ASTNode]) extends ASTNode
case class IfStatement(cond:ASTNode, thenstat:ASTNode, elsestat:Option[ASTNode]) extends ASTNode
case class ForStatement(indexvar:ASTNode, startexp:ASTNode, endexp:ASTNode, ascending:Boolean, body:ASTNode) extends ASTNode
case class PrintfStatement(fmt:ASTNode, elist:List[ASTNode]) extends ASTNode
case class CaseStatement(expression:ASTNode, limbs:List[ASTNode]) extends ASTNode
case class CaseLimb(labels: List[Int], stat: ASTNode) extends ASTNode
