
grammar Asa ;

  /*****************
    Main Program
  *****************/

  program
    : PROGRAM IDENT SEMICOLON block PERIOD
    ;

  block
    :  (CONST ( constant_definition SEMICOLON )+)? (VAR ( variable_declaration SEMICOLON )+)? compound_statement
    ;

  /****************************
        Declarations
  ****************************/
  
  constant_definition
    : IDENT EQUALS literal
    ;

  literal
    : integerLiteral       # IntegerLiteralAlt
    | FLOATINGPOINTLITERAL # FloatingLiteralAlt
    | STRINGLITERAL        # StringLiteralAlt
    | booleanLiteral       # BooleanLiteralAlt
    | setLiteral           # SetLiteralAlt
    ;

  integerLiteral
    : DECIMALINTEGERLITERAL      # DecimalIntegerLiteralAlt
    | HEXADECIMALINTEGERLITERAL  # HexadecimalIntegerLiteralAlt
    | OCTALINTEGERLITERAL        # OctalIntegerLiteralAlt
    ;  
  
  booleanLiteral
    : 'true'
    | 'false'
    ;

  setLiteral
    : LBRACKET literal ( COMMA literal )* RBRACKET
    ;

  variable_declaration
    : IDENT ( COMMA IDENT )* COLON atype
    ;

  atype
    : IDENT # Ident
    | ARRAY LBRACKET DECIMALINTEGERLITERAL DOTDOT DECIMALINTEGERLITERAL RBRACKET OF atype # Array
    | SET OF atype # Set
    ;

  /***************************
      Statements
  ****************************/

  statement
    : assignment_statement 
    | compound_statement 
    | while_statement 
    | repeat_statement 
    | for_statement
    | if_statement 
    | case_statement
    | printf_statement
    ; 

  assignment_statement
    : lhsreference ASSIGN logicalexpression SEMICOLON
    ;

  lhsreference
    : identifier ( LBRACKET simpleexpression RBRACKET )?
    ;

  rhsvalue
    : identifier ( LBRACKET simpleexpression RBRACKET )?
    ;  

  compound_statement
    : BEGIN statement* END
    ;

  while_statement
    : WHILE logicalexpression DO statement
    ;

  repeat_statement
    : REPEAT statement* UNTIL logicalexpression SEMICOLON
    ;

  for_statement
    : FOR identifier ASSIGN simpleexpression dir=(TO | DOWNTO) simpleexpression DO statement
    ;

  if_statement
    : IF logicalexpression THEN statement ( ELSE statement )?
    ;

  printf_statement
    : PRINTF LPAREN simpleexpression ( COMMA simpleexpression )* RPAREN SEMICOLON
    ;

  case_statement
    : CASE simpleexpression OF case_limb+ END
    ;

  case_limb
    : integerLiteral ( COMMA integerLiteral )* COLON statement
    ;

  /*************************
    Expressions
  *************************/

  logicalexpression
    : relationalexpression (op+=(AND | OR) relationalexpression )*
    ;

  relationalexpression
    : simpleexpression (op=(EQUALS | NOTEQUALTO | LESSTHAN | LESSTHANOREQUALTO | GREATERTHAN | GREATERTHANOREQUALTO | IN) simpleexpression )?
    ;

  simpleexpression
    : term (op+=(PLUS | MINUS) term)*
    ;

  term
    : factor (op+=(TIMES | DIVIDE | DIV | MOD) factor)*
    ;

  factor
    : LPAREN fle=logicalexpression RPAREN
    | fl=literal
    | fi=rhsvalue
    | fn=negation
    ;

  negation
    : NOT factor
    ;

  identifier
    : IDENT
    ;

  /********************
    Lexical Defs
  *********************/

  AND
    : 'and'
    ;

  ASSIGN
    : ':='
    ;

  ARRAY
    : 'array'
    ;

  BEGIN
    : 'begin'
    ;

  CASE
    : 'case'
    ;

  COLON
    : ':'
    ;

  COMMA
    : ','
    ;

  CONST
    : 'const'
    ;

  DIV
    : 'div'
    ;

  DIVIDE 
    : '/'
    ;

  DO
    : 'do'
    ;

  DOTDOT
    : '..'
    ;

  DOWNTO
    : 'downto'
    ;

  ELSE
    : 'else'
    ;

  END
    : 'end'
    ;

  EQUALS
    : '='
    ;

  FOR
    : 'for'
    ;

  GREATERTHAN 
    : '>'
    ;

  GREATERTHANOREQUALTO 
    : '>='
    ;

  IF
    : 'if'
    ;

  IN
    : 'in'
    ;

  LBRACE
    : '{'
    ;

  LESSTHAN
    : '<'
    ;

  LBRACKET
    : '['
    ;

  LESSTHANOREQUALTO 
    : '<='
    ;

  LPAREN
    : '('
    ;

  MINUS
    : '-'
    ;

  MOD 
    : 'mod'
    ;
  
  NOTEQUALTO
    : '<>'
    ;

  OF
    : 'of'
    ;

  OR
    : 'or'
    ;

  NOT
    : 'not'
    ;

  PERIOD
    : '.'
    ;

  PLUS
    : '+'
    ;

  PRINTF
    : 'printf'
    ;

  PROGRAM
    : 'program'
    ;

  RBRACE
    : '}'
    ;

  RBRACKET
    : ']'
    ;

  REPEAT
    : 'repeat'
    ;

  RPAREN
    : ')'
    ;

  SEMICOLON
    : ';'
    ;

  SET
    : 'set'
    ;

  THEN
    : 'then'
    ;

  TIMES 
    : '*'
    ;

  TO
    : 'to'
    ;

  UNTIL
    : 'until'
    ;

  VAR
    : 'var'
    ;

  WHILE
    : 'while'
    ;

  DECIMALINTEGERLITERAL
    : [1-9][0-9]*
    ;

  HEXADECIMALINTEGERLITERAL
    : '0'[xX][0-9a-fA-F]+
    ;

  OCTALINTEGERLITERAL
    : '0'[0-7]*
    ;

  FLOATINGPOINTLITERAL    
    : [1-9][0-9]*[.][0-9]+([eE][+-]?[1-9][0-9]*)?
    ;
    
  STRINGLITERAL
    : UnterminatedStringLiteral '"'
    ;

  IDENT
    : [a-zA-Z][a-zA-Z0-9_]*
    ;

  UnterminatedStringLiteral
    : '"' (~["\\\r\n] | '\\' (. | EOF))*
    ;

  COMMENT 
    : '{' ~[}]* '}' -> skip 
    ;

  WS
    : [ \r\n\t] + -> skip
    ;
