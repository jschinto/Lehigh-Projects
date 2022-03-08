// Generated from /Users/jake/Documents/cse262/AsaCompiler1/src/main/antlr4/Asa.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AsaLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "AND", "ASSIGN", "ARRAY", "BEGIN", "CASE", "COLON", "COMMA", 
		"CONST", "DIV", "DIVIDE", "DO", "DOTDOT", "DOWNTO", "ELSE", "END", "EQUALS", 
		"FOR", "GREATERTHAN", "GREATERTHANOREQUALTO", "IF", "IN", "LBRACE", "LESSTHAN", 
		"LBRACKET", "LESSTHANOREQUALTO", "LPAREN", "LSHIFT", "MINUS", "MOD", "NOTEQUALTO", 
		"OF", "OR", "NOT", "PERIOD", "PLUS", "PRINTF", "PROGRAM", "RBRACE", "RBRACKET", 
		"REPEAT", "RPAREN", "RSHIFT", "SEMICOLON", "SET", "SYMMETRIC_DIFFERENCE", 
		"THEN", "TIMES", "TO", "UNTIL", "VAR", "WHILE", "DECIMALINTEGERLITERAL", 
		"HEXADECIMALINTEGERLITERAL", "OCTALINTEGERLITERAL", "FLOATINGPOINTLITERAL", 
		"STRINGLITERAL", "IDENT", "UnterminatedStringLiteral", "COMMENT", "WS"
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


	public AsaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Asa.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2@\u0197\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3"+
		"\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3%\3%\3"+
		"&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3"+
		"+\3+\3+\3+\3+\3+\3+\3,\3,\3-\3-\3-\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3"+
		"\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\7"+
		"\67\u0143\n\67\f\67\16\67\u0146\13\67\38\38\38\68\u014b\n8\r8\168\u014c"+
		"\39\39\79\u0151\n9\f9\169\u0154\139\3:\3:\7:\u0158\n:\f:\16:\u015b\13"+
		":\3:\3:\6:\u015f\n:\r:\16:\u0160\3:\3:\5:\u0165\n:\3:\3:\7:\u0169\n:\f"+
		":\16:\u016c\13:\5:\u016e\n:\3;\3;\3;\3<\3<\7<\u0175\n<\f<\16<\u0178\13"+
		"<\3=\3=\3=\3=\3=\5=\u017f\n=\7=\u0181\n=\f=\16=\u0184\13=\3>\3>\7>\u0188"+
		"\n>\f>\16>\u018b\13>\3>\3>\3>\3>\3?\6?\u0192\n?\r?\16?\u0193\3?\3?\2\2"+
		"@\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o"+
		"9q:s;u<w=y>{?}@\3\2\17\3\2\63;\3\2\62;\4\2ZZzz\5\2\62;CHch\3\2\629\3\2"+
		"\60\60\4\2GGgg\4\2--//\4\2C\\c|\6\2\62;C\\aac|\6\2\f\f\17\17$$^^\3\2\177"+
		"\177\5\2\13\f\17\17\"\"\2\u01a4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2"+
		"\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2"+
		"\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u"+
		"\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\3\177\3\2\2\2\5\u0084"+
		"\3\2\2\2\7\u008a\3\2\2\2\t\u008e\3\2\2\2\13\u0091\3\2\2\2\r\u0097\3\2"+
		"\2\2\17\u009d\3\2\2\2\21\u00a2\3\2\2\2\23\u00a4\3\2\2\2\25\u00a6\3\2\2"+
		"\2\27\u00ac\3\2\2\2\31\u00b0\3\2\2\2\33\u00b2\3\2\2\2\35\u00b5\3\2\2\2"+
		"\37\u00b8\3\2\2\2!\u00bf\3\2\2\2#\u00c4\3\2\2\2%\u00c8\3\2\2\2\'\u00ca"+
		"\3\2\2\2)\u00ce\3\2\2\2+\u00d0\3\2\2\2-\u00d3\3\2\2\2/\u00d6\3\2\2\2\61"+
		"\u00d9\3\2\2\2\63\u00db\3\2\2\2\65\u00dd\3\2\2\2\67\u00df\3\2\2\29\u00e2"+
		"\3\2\2\2;\u00e4\3\2\2\2=\u00e7\3\2\2\2?\u00e9\3\2\2\2A\u00ed\3\2\2\2C"+
		"\u00f0\3\2\2\2E\u00f3\3\2\2\2G\u00f6\3\2\2\2I\u00fa\3\2\2\2K\u00fc\3\2"+
		"\2\2M\u00fe\3\2\2\2O\u0105\3\2\2\2Q\u010d\3\2\2\2S\u010f\3\2\2\2U\u0111"+
		"\3\2\2\2W\u0118\3\2\2\2Y\u011a\3\2\2\2[\u011d\3\2\2\2]\u011f\3\2\2\2_"+
		"\u0123\3\2\2\2a\u0126\3\2\2\2c\u012b\3\2\2\2e\u012d\3\2\2\2g\u0130\3\2"+
		"\2\2i\u0136\3\2\2\2k\u013a\3\2\2\2m\u0140\3\2\2\2o\u0147\3\2\2\2q\u014e"+
		"\3\2\2\2s\u0155\3\2\2\2u\u016f\3\2\2\2w\u0172\3\2\2\2y\u0179\3\2\2\2{"+
		"\u0185\3\2\2\2}\u0191\3\2\2\2\177\u0080\7v\2\2\u0080\u0081\7t\2\2\u0081"+
		"\u0082\7w\2\2\u0082\u0083\7g\2\2\u0083\4\3\2\2\2\u0084\u0085\7h\2\2\u0085"+
		"\u0086\7c\2\2\u0086\u0087\7n\2\2\u0087\u0088\7u\2\2\u0088\u0089\7g\2\2"+
		"\u0089\6\3\2\2\2\u008a\u008b\7c\2\2\u008b\u008c\7p\2\2\u008c\u008d\7f"+
		"\2\2\u008d\b\3\2\2\2\u008e\u008f\7<\2\2\u008f\u0090\7?\2\2\u0090\n\3\2"+
		"\2\2\u0091\u0092\7c\2\2\u0092\u0093\7t\2\2\u0093\u0094\7t\2\2\u0094\u0095"+
		"\7c\2\2\u0095\u0096\7{\2\2\u0096\f\3\2\2\2\u0097\u0098\7d\2\2\u0098\u0099"+
		"\7g\2\2\u0099\u009a\7i\2\2\u009a\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c"+
		"\16\3\2\2\2\u009d\u009e\7e\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7u\2\2\u00a0"+
		"\u00a1\7g\2\2\u00a1\20\3\2\2\2\u00a2\u00a3\7<\2\2\u00a3\22\3\2\2\2\u00a4"+
		"\u00a5\7.\2\2\u00a5\24\3\2\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a8\7q\2\2\u00a8"+
		"\u00a9\7p\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7v\2\2\u00ab\26\3\2\2\2\u00ac"+
		"\u00ad\7f\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7x\2\2\u00af\30\3\2\2\2\u00b0"+
		"\u00b1\7\61\2\2\u00b1\32\3\2\2\2\u00b2\u00b3\7f\2\2\u00b3\u00b4\7q\2\2"+
		"\u00b4\34\3\2\2\2\u00b5\u00b6\7\60\2\2\u00b6\u00b7\7\60\2\2\u00b7\36\3"+
		"\2\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7y\2\2\u00bb"+
		"\u00bc\7p\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7q\2\2\u00be \3\2\2\2\u00bf"+
		"\u00c0\7g\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7g\2\2"+
		"\u00c3\"\3\2\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7f"+
		"\2\2\u00c7$\3\2\2\2\u00c8\u00c9\7?\2\2\u00c9&\3\2\2\2\u00ca\u00cb\7h\2"+
		"\2\u00cb\u00cc\7q\2\2\u00cc\u00cd\7t\2\2\u00cd(\3\2\2\2\u00ce\u00cf\7"+
		"@\2\2\u00cf*\3\2\2\2\u00d0\u00d1\7@\2\2\u00d1\u00d2\7?\2\2\u00d2,\3\2"+
		"\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7h\2\2\u00d5.\3\2\2\2\u00d6\u00d7"+
		"\7k\2\2\u00d7\u00d8\7p\2\2\u00d8\60\3\2\2\2\u00d9\u00da\7}\2\2\u00da\62"+
		"\3\2\2\2\u00db\u00dc\7>\2\2\u00dc\64\3\2\2\2\u00dd\u00de\7]\2\2\u00de"+
		"\66\3\2\2\2\u00df\u00e0\7>\2\2\u00e0\u00e1\7?\2\2\u00e18\3\2\2\2\u00e2"+
		"\u00e3\7*\2\2\u00e3:\3\2\2\2\u00e4\u00e5\7>\2\2\u00e5\u00e6\7>\2\2\u00e6"+
		"<\3\2\2\2\u00e7\u00e8\7/\2\2\u00e8>\3\2\2\2\u00e9\u00ea\7o\2\2\u00ea\u00eb"+
		"\7q\2\2\u00eb\u00ec\7f\2\2\u00ec@\3\2\2\2\u00ed\u00ee\7>\2\2\u00ee\u00ef"+
		"\7@\2\2\u00efB\3\2\2\2\u00f0\u00f1\7q\2\2\u00f1\u00f2\7h\2\2\u00f2D\3"+
		"\2\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7t\2\2\u00f5F\3\2\2\2\u00f6\u00f7"+
		"\7p\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7v\2\2\u00f9H\3\2\2\2\u00fa\u00fb"+
		"\7\60\2\2\u00fbJ\3\2\2\2\u00fc\u00fd\7-\2\2\u00fdL\3\2\2\2\u00fe\u00ff"+
		"\7r\2\2\u00ff\u0100\7t\2\2\u0100\u0101\7k\2\2\u0101\u0102\7p\2\2\u0102"+
		"\u0103\7v\2\2\u0103\u0104\7h\2\2\u0104N\3\2\2\2\u0105\u0106\7r\2\2\u0106"+
		"\u0107\7t\2\2\u0107\u0108\7q\2\2\u0108\u0109\7i\2\2\u0109\u010a\7t\2\2"+
		"\u010a\u010b\7c\2\2\u010b\u010c\7o\2\2\u010cP\3\2\2\2\u010d\u010e\7\177"+
		"\2\2\u010eR\3\2\2\2\u010f\u0110\7_\2\2\u0110T\3\2\2\2\u0111\u0112\7t\2"+
		"\2\u0112\u0113\7g\2\2\u0113\u0114\7r\2\2\u0114\u0115\7g\2\2\u0115\u0116"+
		"\7c\2\2\u0116\u0117\7v\2\2\u0117V\3\2\2\2\u0118\u0119\7+\2\2\u0119X\3"+
		"\2\2\2\u011a\u011b\7@\2\2\u011b\u011c\7@\2\2\u011cZ\3\2\2\2\u011d\u011e"+
		"\7=\2\2\u011e\\\3\2\2\2\u011f\u0120\7u\2\2\u0120\u0121\7g\2\2\u0121\u0122"+
		"\7v\2\2\u0122^\3\2\2\2\u0123\u0124\7@\2\2\u0124\u0125\7>\2\2\u0125`\3"+
		"\2\2\2\u0126\u0127\7v\2\2\u0127\u0128\7j\2\2\u0128\u0129\7g\2\2\u0129"+
		"\u012a\7p\2\2\u012ab\3\2\2\2\u012b\u012c\7,\2\2\u012cd\3\2\2\2\u012d\u012e"+
		"\7v\2\2\u012e\u012f\7q\2\2\u012ff\3\2\2\2\u0130\u0131\7w\2\2\u0131\u0132"+
		"\7p\2\2\u0132\u0133\7v\2\2\u0133\u0134\7k\2\2\u0134\u0135\7n\2\2\u0135"+
		"h\3\2\2\2\u0136\u0137\7x\2\2\u0137\u0138\7c\2\2\u0138\u0139\7t\2\2\u0139"+
		"j\3\2\2\2\u013a\u013b\7y\2\2\u013b\u013c\7j\2\2\u013c\u013d\7k\2\2\u013d"+
		"\u013e\7n\2\2\u013e\u013f\7g\2\2\u013fl\3\2\2\2\u0140\u0144\t\2\2\2\u0141"+
		"\u0143\t\3\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2"+
		"\2\2\u0144\u0145\3\2\2\2\u0145n\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148"+
		"\7\62\2\2\u0148\u014a\t\4\2\2\u0149\u014b\t\5\2\2\u014a\u0149\3\2\2\2"+
		"\u014b\u014c\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014dp\3"+
		"\2\2\2\u014e\u0152\7\62\2\2\u014f\u0151\t\6\2\2\u0150\u014f\3\2\2\2\u0151"+
		"\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153r\3\2\2\2"+
		"\u0154\u0152\3\2\2\2\u0155\u0159\t\2\2\2\u0156\u0158\t\3\2\2\u0157\u0156"+
		"\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a"+
		"\u015c\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015e\t\7\2\2\u015d\u015f\t\3"+
		"\2\2\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u016d\3\2\2\2\u0162\u0164\t\b\2\2\u0163\u0165\t\t"+
		"\2\2\u0164\u0163\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u016a\t\2\2\2\u0167\u0169\t\3\2\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2"+
		"\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016e\3\2\2\2\u016c"+
		"\u016a\3\2\2\2\u016d\u0162\3\2\2\2\u016d\u016e\3\2\2\2\u016et\3\2\2\2"+
		"\u016f\u0170\5y=\2\u0170\u0171\7$\2\2\u0171v\3\2\2\2\u0172\u0176\t\n\2"+
		"\2\u0173\u0175\t\13\2\2\u0174\u0173\3\2\2\2\u0175\u0178\3\2\2\2\u0176"+
		"\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177x\3\2\2\2\u0178\u0176\3\2\2\2"+
		"\u0179\u0182\7$\2\2\u017a\u0181\n\f\2\2\u017b\u017e\7^\2\2\u017c\u017f"+
		"\13\2\2\2\u017d\u017f\7\2\2\3\u017e\u017c\3\2\2\2\u017e\u017d\3\2\2\2"+
		"\u017f\u0181\3\2\2\2\u0180\u017a\3\2\2\2\u0180\u017b\3\2\2\2\u0181\u0184"+
		"\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183z\3\2\2\2\u0184"+
		"\u0182\3\2\2\2\u0185\u0189\7}\2\2\u0186\u0188\n\r\2\2\u0187\u0186\3\2"+
		"\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a"+
		"\u018c\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u018d\7\177\2\2\u018d\u018e\3"+
		"\2\2\2\u018e\u018f\b>\2\2\u018f|\3\2\2\2\u0190\u0192\t\16\2\2\u0191\u0190"+
		"\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0196\b?\2\2\u0196~\3\2\2\2\21\2\u0144\u014c\u0152"+
		"\u0159\u0160\u0164\u016a\u016d\u0176\u017e\u0180\u0182\u0189\u0193\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}