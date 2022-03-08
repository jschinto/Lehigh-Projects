// Generated from /Users/jake/Documents/cse262/AsaCompiler1/src/main/antlr4/Asa.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AsaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AsaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AsaParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AsaParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(AsaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#constant_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_definition(AsaParser.Constant_definitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerLiteralAlt}
	 * labeled alternative in {@link AsaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteralAlt(AsaParser.IntegerLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatingLiteralAlt}
	 * labeled alternative in {@link AsaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingLiteralAlt(AsaParser.FloatingLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteralAlt}
	 * labeled alternative in {@link AsaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralAlt(AsaParser.StringLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanLiteralAlt}
	 * labeled alternative in {@link AsaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteralAlt(AsaParser.BooleanLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetLiteralAlt}
	 * labeled alternative in {@link AsaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetLiteralAlt(AsaParser.SetLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DecimalIntegerLiteralAlt}
	 * labeled alternative in {@link AsaParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalIntegerLiteralAlt(AsaParser.DecimalIntegerLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HexadecimalIntegerLiteralAlt}
	 * labeled alternative in {@link AsaParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexadecimalIntegerLiteralAlt(AsaParser.HexadecimalIntegerLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OctalIntegerLiteralAlt}
	 * labeled alternative in {@link AsaParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctalIntegerLiteralAlt(AsaParser.OctalIntegerLiteralAltContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(AsaParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#setLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetLiteral(AsaParser.SetLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(AsaParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link AsaParser#atype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(AsaParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link AsaParser#atype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(AsaParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Set}
	 * labeled alternative in {@link AsaParser#atype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(AsaParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AsaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#assignment_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_statement(AsaParser.Assignment_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#lhsreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLhsreference(AsaParser.LhsreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#rhsvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRhsvalue(AsaParser.RhsvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#compound_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_statement(AsaParser.Compound_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(AsaParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#repeat_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat_statement(AsaParser.Repeat_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#for_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_statement(AsaParser.For_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(AsaParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#printf_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintf_statement(AsaParser.Printf_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#case_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_statement(AsaParser.Case_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#case_limb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_limb(AsaParser.Case_limbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#logicalexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalexpression(AsaParser.LogicalexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#relationalexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalexpression(AsaParser.RelationalexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#simpleexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleexpression(AsaParser.SimpleexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(AsaParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(AsaParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(AsaParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsaParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(AsaParser.IdentifierContext ctx);
}