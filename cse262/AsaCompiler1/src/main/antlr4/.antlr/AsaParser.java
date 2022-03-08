// Generated from /Users/jake/Documents/cse262/Prog5/src/main/antlr4/Asa.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AsaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, AND=3, ASSIGN=4, ARRAY=5, BEGIN=6, CASE=7, COLON=8, COMMA=9, 
		CONST=10, DIV=11, DIVIDE=12, DO=13, DOTDOT=14, DOWNTO=15, ELSE=16, END=17, 
		EQUALS=18, FOR=19, GREATERTHAN=20, GREATERTHANOREQUALTO=21, IF=22, IN=23, 
		LBRACE=24, LESSTHAN=25, LBRACKET=26, LESSTHANOREQUALTO=27, LPAREN=28, 
		LSHIFT=29, MINUS=30, MOD=31, NOTEQUALTO=32, OF=33, OR=34, NOT=35, PERIOD=36, 
		PLUS=37, PRINTF=38, PROGRAM=39, RBRACE=40, RBRACKET=41, REPEAT=42, RPAREN=43, 
		RSHIFT=44, SEMICOLON=45, SET=46, SYMMETRIC_DIFFERENCE=47, THEN=48, TIMES=49, 
		TO=50, UNTIL=51, VAR=52, WHILE=53, DECIMALINTEGERLITERAL=54, HEXADECIMALINTEGERLITERAL=55, 
		OCTALINTEGERLITERAL=56, FLOATINGPOINTLITERAL=57, STRINGLITERAL=58, IDENT=59, 
		UnterminatedStringLiteral=60, COMMENT=61, WS=62;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_constant_definition = 2, RULE_literal = 3, 
		RULE_integerLiteral = 4, RULE_booleanLiteral = 5, RULE_setLiteral = 6, 
		RULE_variable_declaration = 7, RULE_atype = 8, RULE_statement = 9, RULE_assignment_statement = 10, 
		RULE_lhsreference = 11, RULE_rhsvalue = 12, RULE_compound_statement = 13, 
		RULE_while_statement = 14, RULE_repeat_statement = 15, RULE_for_statement = 16, 
		RULE_if_statement = 17, RULE_printf_statement = 18, RULE_case_statement = 19, 
		RULE_case_limb = 20, RULE_logicalexpression = 21, RULE_relationalexpression = 22, 
		RULE_simpleexpression = 23, RULE_term = 24, RULE_factor = 25, RULE_negation = 26, 
		RULE_identifier = 27;
	public static final String[] ruleNames = {
		"program", "block", "constant_definition", "literal", "integerLiteral", 
		"booleanLiteral", "setLiteral", "variable_declaration", "atype", "statement", 
		"assignment_statement", "lhsreference", "rhsvalue", "compound_statement", 
		"while_statement", "repeat_statement", "for_statement", "if_statement", 
		"printf_statement", "case_statement", "case_limb", "logicalexpression", 
		"relationalexpression", "simpleexpression", "term", "factor", "negation", 
		"identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'true'", "'false'", "'and'", "':='", "'array'", "'begin'", "'case'", 
		"':'", "','", "'const'", "'div'", "'/'", "'do'", "'..'", "'downto'", "'else'", 
		"'end'", "'='", "'for'", "'>'", "'>='", "'if'", "'in'", "'{'", "'<'", 
		"'['", "'<='", "'('", "'<<'", "'-'", "'mod'", "'<>'", "'of'", "'or'", 
		"'not'", "'.'", "'+'", "'printf'", "'program'", "'}'", "']'", "'repeat'", 
		"')'", "'>>'", "';'", "'set'", "'><'", "'then'", "'*'", "'to'", "'until'", 
		"'var'", "'while'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "AND", "ASSIGN", "ARRAY", "BEGIN", "CASE", "COLON", 
		"COMMA", "CONST", "DIV", "DIVIDE", "DO", "DOTDOT", "DOWNTO", "ELSE", "END", 
		"EQUALS", "FOR", "GREATERTHAN", "GREATERTHANOREQUALTO", "IF", "IN", "LBRACE", 
		"LESSTHAN", "LBRACKET", "LESSTHANOREQUALTO", "LPAREN", "LSHIFT", "MINUS", 
		"MOD", "NOTEQUALTO", "OF", "OR", "NOT", "PERIOD", "PLUS", "PRINTF", "PROGRAM", 
		"RBRACE", "RBRACKET", "REPEAT", "RPAREN", "RSHIFT", "SEMICOLON", "SET", 
		"SYMMETRIC_DIFFERENCE", "THEN", "TIMES", "TO", "UNTIL", "VAR", "WHILE", 
		"DECIMALINTEGERLITERAL", "HEXADECIMALINTEGERLITERAL", "OCTALINTEGERLITERAL", 
		"FLOATINGPOINTLITERAL", "STRINGLITERAL", "IDENT", "UnterminatedStringLiteral", 
		"COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Asa.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AsaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(AsaParser.PROGRAM, 0); }
		public TerminalNode IDENT() { return getToken(AsaParser.IDENT, 0); }
		public TerminalNode SEMICOLON() { return getToken(AsaParser.SEMICOLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode PERIOD() { return getToken(AsaParser.PERIOD, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(PROGRAM);
			setState(57);
			match(IDENT);
			setState(58);
			match(SEMICOLON);
			setState(59);
			block();
			setState(60);
			match(PERIOD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public TerminalNode CONST() { return getToken(AsaParser.CONST, 0); }
		public TerminalNode VAR() { return getToken(AsaParser.VAR, 0); }
		public List<Constant_definitionContext> constant_definition() {
			return getRuleContexts(Constant_definitionContext.class);
		}
		public Constant_definitionContext constant_definition(int i) {
			return getRuleContext(Constant_definitionContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(AsaParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(AsaParser.SEMICOLON, i);
		}
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(62);
				match(CONST);
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(63);
					constant_definition();
					setState(64);
					match(SEMICOLON);
					}
					}
					setState(68); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENT );
				}
			}

			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(72);
				match(VAR);
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(73);
					variable_declaration();
					setState(74);
					match(SEMICOLON);
					}
					}
					setState(78); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENT );
				}
			}

			setState(82);
			compound_statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_definitionContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(AsaParser.IDENT, 0); }
		public TerminalNode EQUALS() { return getToken(AsaParser.EQUALS, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Constant_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_definition; }
	}

	public final Constant_definitionContext constant_definition() throws RecognitionException {
		Constant_definitionContext _localctx = new Constant_definitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constant_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(IDENT);
			setState(85);
			match(EQUALS);
			setState(86);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanLiteralAltContext extends LiteralContext {
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public BooleanLiteralAltContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	public static class FloatingLiteralAltContext extends LiteralContext {
		public TerminalNode FLOATINGPOINTLITERAL() { return getToken(AsaParser.FLOATINGPOINTLITERAL, 0); }
		public FloatingLiteralAltContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	public static class StringLiteralAltContext extends LiteralContext {
		public TerminalNode STRINGLITERAL() { return getToken(AsaParser.STRINGLITERAL, 0); }
		public StringLiteralAltContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	public static class IntegerLiteralAltContext extends LiteralContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public IntegerLiteralAltContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	public static class SetLiteralAltContext extends LiteralContext {
		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class,0);
		}
		public SetLiteralAltContext(LiteralContext ctx) { copyFrom(ctx); }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literal);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMALINTEGERLITERAL:
			case HEXADECIMALINTEGERLITERAL:
			case OCTALINTEGERLITERAL:
				_localctx = new IntegerLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				integerLiteral();
				}
				break;
			case FLOATINGPOINTLITERAL:
				_localctx = new FloatingLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(FLOATINGPOINTLITERAL);
				}
				break;
			case STRINGLITERAL:
				_localctx = new StringLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(90);
				match(STRINGLITERAL);
				}
				break;
			case T__0:
			case T__1:
				_localctx = new BooleanLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				booleanLiteral();
				}
				break;
			case LBRACKET:
				_localctx = new SetLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(92);
				setLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
	 
		public IntegerLiteralContext() { }
		public void copyFrom(IntegerLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OctalIntegerLiteralAltContext extends IntegerLiteralContext {
		public TerminalNode OCTALINTEGERLITERAL() { return getToken(AsaParser.OCTALINTEGERLITERAL, 0); }
		public OctalIntegerLiteralAltContext(IntegerLiteralContext ctx) { copyFrom(ctx); }
	}
	public static class DecimalIntegerLiteralAltContext extends IntegerLiteralContext {
		public TerminalNode DECIMALINTEGERLITERAL() { return getToken(AsaParser.DECIMALINTEGERLITERAL, 0); }
		public DecimalIntegerLiteralAltContext(IntegerLiteralContext ctx) { copyFrom(ctx); }
	}
	public static class HexadecimalIntegerLiteralAltContext extends IntegerLiteralContext {
		public TerminalNode HEXADECIMALINTEGERLITERAL() { return getToken(AsaParser.HEXADECIMALINTEGERLITERAL, 0); }
		public HexadecimalIntegerLiteralAltContext(IntegerLiteralContext ctx) { copyFrom(ctx); }
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_integerLiteral);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMALINTEGERLITERAL:
				_localctx = new DecimalIntegerLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(DECIMALINTEGERLITERAL);
				}
				break;
			case HEXADECIMALINTEGERLITERAL:
				_localctx = new HexadecimalIntegerLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(HEXADECIMALINTEGERLITERAL);
				}
				break;
			case OCTALINTEGERLITERAL:
				_localctx = new OctalIntegerLiteralAltContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				match(OCTALINTEGERLITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanLiteralContext extends ParserRuleContext {
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(AsaParser.LBRACKET, 0); }
		public List<IntegerLiteralContext> integerLiteral() {
			return getRuleContexts(IntegerLiteralContext.class);
		}
		public IntegerLiteralContext integerLiteral(int i) {
			return getRuleContext(IntegerLiteralContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(AsaParser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AsaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AsaParser.COMMA, i);
		}
		public SetLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setLiteral; }
	}

	public final SetLiteralContext setLiteral() throws RecognitionException {
		SetLiteralContext _localctx = new SetLiteralContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_setLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LBRACKET);
			setState(103);
			integerLiteral();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				integerLiteral();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declarationContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(AsaParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(AsaParser.IDENT, i);
		}
		public TerminalNode COLON() { return getToken(AsaParser.COLON, 0); }
		public AtypeContext atype() {
			return getRuleContext(AtypeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(AsaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AsaParser.COMMA, i);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(IDENT);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(114);
				match(COMMA);
				setState(115);
				match(IDENT);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(COLON);
			setState(122);
			atype();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtypeContext extends ParserRuleContext {
		public AtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atype; }
	 
		public AtypeContext() { }
		public void copyFrom(AtypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayContext extends AtypeContext {
		public TerminalNode ARRAY() { return getToken(AsaParser.ARRAY, 0); }
		public TerminalNode LBRACKET() { return getToken(AsaParser.LBRACKET, 0); }
		public List<IntegerLiteralContext> integerLiteral() {
			return getRuleContexts(IntegerLiteralContext.class);
		}
		public IntegerLiteralContext integerLiteral(int i) {
			return getRuleContext(IntegerLiteralContext.class,i);
		}
		public TerminalNode DOTDOT() { return getToken(AsaParser.DOTDOT, 0); }
		public TerminalNode RBRACKET() { return getToken(AsaParser.RBRACKET, 0); }
		public TerminalNode OF() { return getToken(AsaParser.OF, 0); }
		public AtypeContext atype() {
			return getRuleContext(AtypeContext.class,0);
		}
		public ArrayContext(AtypeContext ctx) { copyFrom(ctx); }
	}
	public static class IdentContext extends AtypeContext {
		public TerminalNode IDENT() { return getToken(AsaParser.IDENT, 0); }
		public IdentContext(AtypeContext ctx) { copyFrom(ctx); }
	}
	public static class SetContext extends AtypeContext {
		public TerminalNode SET() { return getToken(AsaParser.SET, 0); }
		public SetContext(AtypeContext ctx) { copyFrom(ctx); }
	}

	public final AtypeContext atype() throws RecognitionException {
		AtypeContext _localctx = new AtypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atype);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				_localctx = new IdentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(IDENT);
				}
				break;
			case ARRAY:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				match(ARRAY);
				setState(126);
				match(LBRACKET);
				setState(127);
				integerLiteral();
				setState(128);
				match(DOTDOT);
				setState(129);
				integerLiteral();
				setState(130);
				match(RBRACKET);
				setState(131);
				match(OF);
				setState(132);
				atype();
				}
				break;
			case SET:
				_localctx = new SetContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(SET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public Repeat_statementContext repeat_statement() {
			return getRuleContext(Repeat_statementContext.class,0);
		}
		public For_statementContext for_statement() {
			return getRuleContext(For_statementContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Case_statementContext case_statement() {
			return getRuleContext(Case_statementContext.class,0);
		}
		public Printf_statementContext printf_statement() {
			return getRuleContext(Printf_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				assignment_statement();
				}
				break;
			case BEGIN:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				compound_statement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				while_statement();
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				repeat_statement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(141);
				for_statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(142);
				if_statement();
				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 7);
				{
				setState(143);
				case_statement();
				}
				break;
			case PRINTF:
				enterOuterAlt(_localctx, 8);
				{
				setState(144);
				printf_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assignment_statementContext extends ParserRuleContext {
		public LhsreferenceContext lhsreference() {
			return getRuleContext(LhsreferenceContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AsaParser.ASSIGN, 0); }
		public LogicalexpressionContext logicalexpression() {
			return getRuleContext(LogicalexpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(AsaParser.SEMICOLON, 0); }
		public Assignment_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_statement; }
	}

	public final Assignment_statementContext assignment_statement() throws RecognitionException {
		Assignment_statementContext _localctx = new Assignment_statementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignment_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			lhsreference();
			setState(148);
			match(ASSIGN);
			setState(149);
			logicalexpression();
			setState(150);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LhsreferenceContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(AsaParser.LBRACKET, 0); }
		public SimpleexpressionContext simpleexpression() {
			return getRuleContext(SimpleexpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(AsaParser.RBRACKET, 0); }
		public LhsreferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lhsreference; }
	}

	public final LhsreferenceContext lhsreference() throws RecognitionException {
		LhsreferenceContext _localctx = new LhsreferenceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lhsreference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			identifier();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(153);
				match(LBRACKET);
				setState(154);
				simpleexpression();
				setState(155);
				match(RBRACKET);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RhsvalueContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(AsaParser.LBRACKET, 0); }
		public SimpleexpressionContext simpleexpression() {
			return getRuleContext(SimpleexpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(AsaParser.RBRACKET, 0); }
		public RhsvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rhsvalue; }
	}

	public final RhsvalueContext rhsvalue() throws RecognitionException {
		RhsvalueContext _localctx = new RhsvalueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_rhsvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			identifier();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(160);
				match(LBRACKET);
				setState(161);
				simpleexpression();
				setState(162);
				match(RBRACKET);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_statementContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(AsaParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(AsaParser.END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Compound_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_statement; }
	}

	public final Compound_statementContext compound_statement() throws RecognitionException {
		Compound_statementContext _localctx = new Compound_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_compound_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(BEGIN);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BEGIN) | (1L << CASE) | (1L << FOR) | (1L << IF) | (1L << PRINTF) | (1L << REPEAT) | (1L << WHILE) | (1L << IDENT))) != 0)) {
				{
				{
				setState(167);
				statement();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(AsaParser.WHILE, 0); }
		public LogicalexpressionContext logicalexpression() {
			return getRuleContext(LogicalexpressionContext.class,0);
		}
		public TerminalNode DO() { return getToken(AsaParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(WHILE);
			setState(176);
			logicalexpression();
			setState(177);
			match(DO);
			setState(178);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Repeat_statementContext extends ParserRuleContext {
		public TerminalNode REPEAT() { return getToken(AsaParser.REPEAT, 0); }
		public TerminalNode UNTIL() { return getToken(AsaParser.UNTIL, 0); }
		public LogicalexpressionContext logicalexpression() {
			return getRuleContext(LogicalexpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(AsaParser.SEMICOLON, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Repeat_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_statement; }
	}

	public final Repeat_statementContext repeat_statement() throws RecognitionException {
		Repeat_statementContext _localctx = new Repeat_statementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_repeat_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(REPEAT);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BEGIN) | (1L << CASE) | (1L << FOR) | (1L << IF) | (1L << PRINTF) | (1L << REPEAT) | (1L << WHILE) | (1L << IDENT))) != 0)) {
				{
				{
				setState(181);
				statement();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(187);
			match(UNTIL);
			setState(188);
			logicalexpression();
			setState(189);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_statementContext extends ParserRuleContext {
		public Token dir;
		public TerminalNode FOR() { return getToken(AsaParser.FOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AsaParser.ASSIGN, 0); }
		public List<SimpleexpressionContext> simpleexpression() {
			return getRuleContexts(SimpleexpressionContext.class);
		}
		public SimpleexpressionContext simpleexpression(int i) {
			return getRuleContext(SimpleexpressionContext.class,i);
		}
		public TerminalNode DO() { return getToken(AsaParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode TO() { return getToken(AsaParser.TO, 0); }
		public TerminalNode DOWNTO() { return getToken(AsaParser.DOWNTO, 0); }
		public For_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_statement; }
	}

	public final For_statementContext for_statement() throws RecognitionException {
		For_statementContext _localctx = new For_statementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_for_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(FOR);
			setState(192);
			identifier();
			setState(193);
			match(ASSIGN);
			setState(194);
			simpleexpression();
			setState(195);
			((For_statementContext)_localctx).dir = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==DOWNTO || _la==TO) ) {
				((For_statementContext)_localctx).dir = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(196);
			simpleexpression();
			setState(197);
			match(DO);
			setState(198);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(AsaParser.IF, 0); }
		public LogicalexpressionContext logicalexpression() {
			return getRuleContext(LogicalexpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(AsaParser.THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AsaParser.ELSE, 0); }
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_if_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(IF);
			setState(201);
			logicalexpression();
			setState(202);
			match(THEN);
			setState(203);
			statement();
			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(204);
				match(ELSE);
				setState(205);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Printf_statementContext extends ParserRuleContext {
		public TerminalNode PRINTF() { return getToken(AsaParser.PRINTF, 0); }
		public TerminalNode LPAREN() { return getToken(AsaParser.LPAREN, 0); }
		public List<SimpleexpressionContext> simpleexpression() {
			return getRuleContexts(SimpleexpressionContext.class);
		}
		public SimpleexpressionContext simpleexpression(int i) {
			return getRuleContext(SimpleexpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(AsaParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(AsaParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AsaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AsaParser.COMMA, i);
		}
		public Printf_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printf_statement; }
	}

	public final Printf_statementContext printf_statement() throws RecognitionException {
		Printf_statementContext _localctx = new Printf_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_printf_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(PRINTF);
			setState(209);
			match(LPAREN);
			setState(210);
			simpleexpression();
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(211);
				match(COMMA);
				setState(212);
				simpleexpression();
				}
				}
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(218);
			match(RPAREN);
			setState(219);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_statementContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(AsaParser.CASE, 0); }
		public SimpleexpressionContext simpleexpression() {
			return getRuleContext(SimpleexpressionContext.class,0);
		}
		public TerminalNode OF() { return getToken(AsaParser.OF, 0); }
		public TerminalNode END() { return getToken(AsaParser.END, 0); }
		public List<Case_limbContext> case_limb() {
			return getRuleContexts(Case_limbContext.class);
		}
		public Case_limbContext case_limb(int i) {
			return getRuleContext(Case_limbContext.class,i);
		}
		public Case_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_statement; }
	}

	public final Case_statementContext case_statement() throws RecognitionException {
		Case_statementContext _localctx = new Case_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_case_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(CASE);
			setState(222);
			simpleexpression();
			setState(223);
			match(OF);
			setState(225); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(224);
				case_limb();
				}
				}
				setState(227); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMALINTEGERLITERAL) | (1L << HEXADECIMALINTEGERLITERAL) | (1L << OCTALINTEGERLITERAL))) != 0) );
			setState(229);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_limbContext extends ParserRuleContext {
		public List<IntegerLiteralContext> integerLiteral() {
			return getRuleContexts(IntegerLiteralContext.class);
		}
		public IntegerLiteralContext integerLiteral(int i) {
			return getRuleContext(IntegerLiteralContext.class,i);
		}
		public TerminalNode COLON() { return getToken(AsaParser.COLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(AsaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AsaParser.COMMA, i);
		}
		public Case_limbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_limb; }
	}

	public final Case_limbContext case_limb() throws RecognitionException {
		Case_limbContext _localctx = new Case_limbContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_case_limb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			integerLiteral();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(232);
				match(COMMA);
				setState(233);
				integerLiteral();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(239);
			match(COLON);
			setState(240);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalexpressionContext extends ParserRuleContext {
		public Token op;
		public List<RelationalexpressionContext> relationalexpression() {
			return getRuleContexts(RelationalexpressionContext.class);
		}
		public RelationalexpressionContext relationalexpression(int i) {
			return getRuleContext(RelationalexpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(AsaParser.AND, 0); }
		public TerminalNode OR() { return getToken(AsaParser.OR, 0); }
		public LogicalexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalexpression; }
	}

	public final LogicalexpressionContext logicalexpression() throws RecognitionException {
		LogicalexpressionContext _localctx = new LogicalexpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_logicalexpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			relationalexpression();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND || _la==OR) {
				{
				setState(243);
				((LogicalexpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
					((LogicalexpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(244);
				relationalexpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalexpressionContext extends ParserRuleContext {
		public Token op;
		public List<SimpleexpressionContext> simpleexpression() {
			return getRuleContexts(SimpleexpressionContext.class);
		}
		public SimpleexpressionContext simpleexpression(int i) {
			return getRuleContext(SimpleexpressionContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(AsaParser.EQUALS, 0); }
		public TerminalNode NOTEQUALTO() { return getToken(AsaParser.NOTEQUALTO, 0); }
		public TerminalNode LESSTHAN() { return getToken(AsaParser.LESSTHAN, 0); }
		public TerminalNode LESSTHANOREQUALTO() { return getToken(AsaParser.LESSTHANOREQUALTO, 0); }
		public TerminalNode GREATERTHAN() { return getToken(AsaParser.GREATERTHAN, 0); }
		public TerminalNode GREATERTHANOREQUALTO() { return getToken(AsaParser.GREATERTHANOREQUALTO, 0); }
		public TerminalNode IN() { return getToken(AsaParser.IN, 0); }
		public RelationalexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalexpression; }
	}

	public final RelationalexpressionContext relationalexpression() throws RecognitionException {
		RelationalexpressionContext _localctx = new RelationalexpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_relationalexpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			simpleexpression();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << GREATERTHAN) | (1L << GREATERTHANOREQUALTO) | (1L << IN) | (1L << LESSTHAN) | (1L << LESSTHANOREQUALTO) | (1L << NOTEQUALTO))) != 0)) {
				{
				setState(248);
				((RelationalexpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << GREATERTHAN) | (1L << GREATERTHANOREQUALTO) | (1L << IN) | (1L << LESSTHAN) | (1L << LESSTHANOREQUALTO) | (1L << NOTEQUALTO))) != 0)) ) {
					((RelationalexpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(249);
				simpleexpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleexpressionContext extends ParserRuleContext {
		public Token PLUS;
		public List<Token> op = new ArrayList<Token>();
		public Token MINUS;
		public Token _tset574;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(AsaParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(AsaParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(AsaParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(AsaParser.MINUS, i);
		}
		public SimpleexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleexpression; }
	}

	public final SimpleexpressionContext simpleexpression() throws RecognitionException {
		SimpleexpressionContext _localctx = new SimpleexpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_simpleexpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			term();
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MINUS || _la==PLUS) {
				{
				{
				setState(253);
				((SimpleexpressionContext)_localctx)._tset574 = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
					((SimpleexpressionContext)_localctx)._tset574 = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				((SimpleexpressionContext)_localctx).op.add(((SimpleexpressionContext)_localctx)._tset574);
				setState(254);
				term();
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Token TIMES;
		public List<Token> op = new ArrayList<Token>();
		public Token DIVIDE;
		public Token DIV;
		public Token MOD;
		public Token LSHIFT;
		public Token RSHIFT;
		public Token _tset597;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(AsaParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(AsaParser.TIMES, i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(AsaParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(AsaParser.DIVIDE, i);
		}
		public List<TerminalNode> DIV() { return getTokens(AsaParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(AsaParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(AsaParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(AsaParser.MOD, i);
		}
		public List<TerminalNode> LSHIFT() { return getTokens(AsaParser.LSHIFT); }
		public TerminalNode LSHIFT(int i) {
			return getToken(AsaParser.LSHIFT, i);
		}
		public List<TerminalNode> RSHIFT() { return getTokens(AsaParser.RSHIFT); }
		public TerminalNode RSHIFT(int i) {
			return getToken(AsaParser.RSHIFT, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			factor();
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DIV) | (1L << DIVIDE) | (1L << LSHIFT) | (1L << MOD) | (1L << RSHIFT) | (1L << TIMES))) != 0)) {
				{
				{
				setState(261);
				((TermContext)_localctx)._tset597 = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DIV) | (1L << DIVIDE) | (1L << LSHIFT) | (1L << MOD) | (1L << RSHIFT) | (1L << TIMES))) != 0)) ) {
					((TermContext)_localctx)._tset597 = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				((TermContext)_localctx).op.add(((TermContext)_localctx)._tset597);
				setState(262);
				factor();
				}
				}
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public LogicalexpressionContext fle;
		public LiteralContext fl;
		public RhsvalueContext fi;
		public NegationContext fn;
		public TerminalNode LPAREN() { return getToken(AsaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AsaParser.RPAREN, 0); }
		public LogicalexpressionContext logicalexpression() {
			return getRuleContext(LogicalexpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public RhsvalueContext rhsvalue() {
			return getRuleContext(RhsvalueContext.class,0);
		}
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_factor);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				match(LPAREN);
				setState(269);
				((FactorContext)_localctx).fle = logicalexpression();
				setState(270);
				match(RPAREN);
				}
				break;
			case T__0:
			case T__1:
			case LBRACKET:
			case DECIMALINTEGERLITERAL:
			case HEXADECIMALINTEGERLITERAL:
			case OCTALINTEGERLITERAL:
			case FLOATINGPOINTLITERAL:
			case STRINGLITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				((FactorContext)_localctx).fl = literal();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(273);
				((FactorContext)_localctx).fi = rhsvalue();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(274);
				((FactorContext)_localctx).fn = negation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(AsaParser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(NOT);
			setState(278);
			factor();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(AsaParser.IDENT, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u011d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\6\3E\n\3\r\3\16\3F\5\3I\n\3\3\3\3\3\3\3\3\3\6\3O\n\3\r\3\16"+
		"\3P\5\3S\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5`\n\5\3\6"+
		"\3\6\3\6\5\6e\n\6\3\7\3\7\3\b\3\b\3\b\3\b\7\bm\n\b\f\b\16\bp\13\b\3\b"+
		"\3\b\3\t\3\t\3\t\7\tw\n\t\f\t\16\tz\13\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008a\n\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u0094\n\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r"+
		"\u00a0\n\r\3\16\3\16\3\16\3\16\3\16\5\16\u00a7\n\16\3\17\3\17\7\17\u00ab"+
		"\n\17\f\17\16\17\u00ae\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\7\21\u00b9\n\21\f\21\16\21\u00bc\13\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5"+
		"\23\u00d1\n\23\3\24\3\24\3\24\3\24\3\24\7\24\u00d8\n\24\f\24\16\24\u00db"+
		"\13\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\6\25\u00e4\n\25\r\25\16\25\u00e5"+
		"\3\25\3\25\3\26\3\26\3\26\7\26\u00ed\n\26\f\26\16\26\u00f0\13\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\5\27\u00f8\n\27\3\30\3\30\3\30\5\30\u00fd\n"+
		"\30\3\31\3\31\3\31\7\31\u0102\n\31\f\31\16\31\u0105\13\31\3\32\3\32\3"+
		"\32\7\32\u010a\n\32\f\32\16\32\u010d\13\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\5\33\u0116\n\33\3\34\3\34\3\34\3\35\3\35\3\35\2\2\36\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\b\3\2\3\4\4\2"+
		"\21\21\64\64\4\2\5\5$$\b\2\24\24\26\27\31\31\33\33\35\35\"\"\4\2  \'\'"+
		"\7\2\r\16\37\37!!..\63\63\2\u0124\2:\3\2\2\2\4H\3\2\2\2\6V\3\2\2\2\b_"+
		"\3\2\2\2\nd\3\2\2\2\ff\3\2\2\2\16h\3\2\2\2\20s\3\2\2\2\22\u0089\3\2\2"+
		"\2\24\u0093\3\2\2\2\26\u0095\3\2\2\2\30\u009a\3\2\2\2\32\u00a1\3\2\2\2"+
		"\34\u00a8\3\2\2\2\36\u00b1\3\2\2\2 \u00b6\3\2\2\2\"\u00c1\3\2\2\2$\u00ca"+
		"\3\2\2\2&\u00d2\3\2\2\2(\u00df\3\2\2\2*\u00e9\3\2\2\2,\u00f4\3\2\2\2."+
		"\u00f9\3\2\2\2\60\u00fe\3\2\2\2\62\u0106\3\2\2\2\64\u0115\3\2\2\2\66\u0117"+
		"\3\2\2\28\u011a\3\2\2\2:;\7)\2\2;<\7=\2\2<=\7/\2\2=>\5\4\3\2>?\7&\2\2"+
		"?\3\3\2\2\2@D\7\f\2\2AB\5\6\4\2BC\7/\2\2CE\3\2\2\2DA\3\2\2\2EF\3\2\2\2"+
		"FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2H@\3\2\2\2HI\3\2\2\2IR\3\2\2\2JN\7\66\2"+
		"\2KL\5\20\t\2LM\7/\2\2MO\3\2\2\2NK\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2"+
		"\2QS\3\2\2\2RJ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\5\34\17\2U\5\3\2\2\2VW\7"+
		"=\2\2WX\7\24\2\2XY\5\b\5\2Y\7\3\2\2\2Z`\5\n\6\2[`\7;\2\2\\`\7<\2\2]`\5"+
		"\f\7\2^`\5\16\b\2_Z\3\2\2\2_[\3\2\2\2_\\\3\2\2\2_]\3\2\2\2_^\3\2\2\2`"+
		"\t\3\2\2\2ae\78\2\2be\79\2\2ce\7:\2\2da\3\2\2\2db\3\2\2\2dc\3\2\2\2e\13"+
		"\3\2\2\2fg\t\2\2\2g\r\3\2\2\2hi\7\34\2\2in\5\n\6\2jk\7\13\2\2km\5\n\6"+
		"\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7+\2"+
		"\2r\17\3\2\2\2sx\7=\2\2tu\7\13\2\2uw\7=\2\2vt\3\2\2\2wz\3\2\2\2xv\3\2"+
		"\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\n\2\2|}\5\22\n\2}\21\3\2\2\2~\u008a"+
		"\7=\2\2\177\u0080\7\7\2\2\u0080\u0081\7\34\2\2\u0081\u0082\5\n\6\2\u0082"+
		"\u0083\7\20\2\2\u0083\u0084\5\n\6\2\u0084\u0085\7+\2\2\u0085\u0086\7#"+
		"\2\2\u0086\u0087\5\22\n\2\u0087\u008a\3\2\2\2\u0088\u008a\7\60\2\2\u0089"+
		"~\3\2\2\2\u0089\177\3\2\2\2\u0089\u0088\3\2\2\2\u008a\23\3\2\2\2\u008b"+
		"\u0094\5\26\f\2\u008c\u0094\5\34\17\2\u008d\u0094\5\36\20\2\u008e\u0094"+
		"\5 \21\2\u008f\u0094\5\"\22\2\u0090\u0094\5$\23\2\u0091\u0094\5(\25\2"+
		"\u0092\u0094\5&\24\2\u0093\u008b\3\2\2\2\u0093\u008c\3\2\2\2\u0093\u008d"+
		"\3\2\2\2\u0093\u008e\3\2\2\2\u0093\u008f\3\2\2\2\u0093\u0090\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0093\u0092\3\2\2\2\u0094\25\3\2\2\2\u0095\u0096\5\30\r"+
		"\2\u0096\u0097\7\6\2\2\u0097\u0098\5,\27\2\u0098\u0099\7/\2\2\u0099\27"+
		"\3\2\2\2\u009a\u009f\58\35\2\u009b\u009c\7\34\2\2\u009c\u009d\5\60\31"+
		"\2\u009d\u009e\7+\2\2\u009e\u00a0\3\2\2\2\u009f\u009b\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\31\3\2\2\2\u00a1\u00a6\58\35\2\u00a2\u00a3\7\34\2\2\u00a3"+
		"\u00a4\5\60\31\2\u00a4\u00a5\7+\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a2\3"+
		"\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\33\3\2\2\2\u00a8\u00ac\7\b\2\2\u00a9"+
		"\u00ab\5\24\13\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3"+
		"\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00b0\7\23\2\2\u00b0\35\3\2\2\2\u00b1\u00b2\7\67\2\2\u00b2\u00b3\5,\27"+
		"\2\u00b3\u00b4\7\17\2\2\u00b4\u00b5\5\24\13\2\u00b5\37\3\2\2\2\u00b6\u00ba"+
		"\7,\2\2\u00b7\u00b9\5\24\13\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2"+
		"\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba"+
		"\3\2\2\2\u00bd\u00be\7\65\2\2\u00be\u00bf\5,\27\2\u00bf\u00c0\7/\2\2\u00c0"+
		"!\3\2\2\2\u00c1\u00c2\7\25\2\2\u00c2\u00c3\58\35\2\u00c3\u00c4\7\6\2\2"+
		"\u00c4\u00c5\5\60\31\2\u00c5\u00c6\t\3\2\2\u00c6\u00c7\5\60\31\2\u00c7"+
		"\u00c8\7\17\2\2\u00c8\u00c9\5\24\13\2\u00c9#\3\2\2\2\u00ca\u00cb\7\30"+
		"\2\2\u00cb\u00cc\5,\27\2\u00cc\u00cd\7\62\2\2\u00cd\u00d0\5\24\13\2\u00ce"+
		"\u00cf\7\22\2\2\u00cf\u00d1\5\24\13\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1"+
		"\3\2\2\2\u00d1%\3\2\2\2\u00d2\u00d3\7(\2\2\u00d3\u00d4\7\36\2\2\u00d4"+
		"\u00d9\5\60\31\2\u00d5\u00d6\7\13\2\2\u00d6\u00d8\5\60\31\2\u00d7\u00d5"+
		"\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\7-\2\2\u00dd\u00de\7/\2"+
		"\2\u00de\'\3\2\2\2\u00df\u00e0\7\t\2\2\u00e0\u00e1\5\60\31\2\u00e1\u00e3"+
		"\7#\2\2\u00e2\u00e4\5*\26\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7\23"+
		"\2\2\u00e8)\3\2\2\2\u00e9\u00ee\5\n\6\2\u00ea\u00eb\7\13\2\2\u00eb\u00ed"+
		"\5\n\6\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\7\n"+
		"\2\2\u00f2\u00f3\5\24\13\2\u00f3+\3\2\2\2\u00f4\u00f7\5.\30\2\u00f5\u00f6"+
		"\t\4\2\2\u00f6\u00f8\5.\30\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"-\3\2\2\2\u00f9\u00fc\5\60\31\2\u00fa\u00fb\t\5\2\2\u00fb\u00fd\5\60\31"+
		"\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd/\3\2\2\2\u00fe\u0103"+
		"\5\62\32\2\u00ff\u0100\t\6\2\2\u0100\u0102\5\62\32\2\u0101\u00ff\3\2\2"+
		"\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\61"+
		"\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u010b\5\64\33\2\u0107\u0108\t\7\2\2"+
		"\u0108\u010a\5\64\33\2\u0109\u0107\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109"+
		"\3\2\2\2\u010b\u010c\3\2\2\2\u010c\63\3\2\2\2\u010d\u010b\3\2\2\2\u010e"+
		"\u010f\7\36\2\2\u010f\u0110\5,\27\2\u0110\u0111\7-\2\2\u0111\u0116\3\2"+
		"\2\2\u0112\u0116\5\b\5\2\u0113\u0116\5\32\16\2\u0114\u0116\5\66\34\2\u0115"+
		"\u010e\3\2\2\2\u0115\u0112\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0114\3\2"+
		"\2\2\u0116\65\3\2\2\2\u0117\u0118\7%\2\2\u0118\u0119\5\64\33\2\u0119\67"+
		"\3\2\2\2\u011a\u011b\7=\2\2\u011b9\3\2\2\2\31FHPR_dnx\u0089\u0093\u009f"+
		"\u00a6\u00ac\u00ba\u00d0\u00d9\u00e5\u00ee\u00f7\u00fc\u0103\u010b\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}