/* ============================================================
 * Scanner.flex
 * Analizador léxico para el lenguaje MiniC
 * Proyecto 001Scanner
 * Basado en el formato de lexer.flex utilizado en clase
 * ============================================================
 */

package compiler.scanner;

%%

/* ------------------------------------------------------------
   1. Configuración general de JFlex
   ------------------------------------------------------------ */
%public
%class Scanner
%unicode
%line
%column

%{

// Código Java embebido
// Este bloque se inserta directamente en el Scanner.java generado
// Aquí solo imprimimos los tokens (no usamos CUP todavía)
private void printToken(String token, String lexema) {
    System.out.println("TOKEN: " + token + "\tLEXEMA: " + lexema);
}

%}

/* ------------------------------------------------------------
   2. Expresiones regulares
   ------------------------------------------------------------ */
WHITESPACE       = [ \t\r\n]+
LETTER           = [a-zA-Z_]
DIGIT            = [0-9]
ID               = {LETTER}({LETTER}|{DIGIT})*
INT_LITERAL      = {DIGIT}+
FLOAT_LITERAL    = {DIGIT}+ "." {DIGIT}+
STRING_LITERAL   = \"([^\"\\]|\\.)*\"

/* Comentarios */
LINE_COMMENT     = "//".*
BLOCK_COMMENT    = "/*"([^*]|\*+[^*/])*\*+"/"

/* ------------------------------------------------------------
   3. Reglas léxicas
   ------------------------------------------------------------ */
%%

/* Ignorar espacios y comentarios */
{WHITESPACE}     { /* Ignorar */ }
{LINE_COMMENT}   { /* Ignorar */ }
{BLOCK_COMMENT}  { /* Ignorar */ }

/* ---------------------
   Palabras reservadas
   --------------------- */
"int"            { printToken("INT", yytext()); }
"float"          { printToken("FLOAT", yytext()); }
"if"             { printToken("IF", yytext()); }
"else"           { printToken("ELSE", yytext()); }
"while"          { printToken("WHILE", yytext()); }
"return"         { printToken("RETURN", yytext()); }
"void"           { printToken("VOID", yytext()); }

/* ---------------------
   Literales
   --------------------- */
{FLOAT_LITERAL}  { printToken("FLOAT_LIT", yytext()); }
{INT_LITERAL}    { printToken("INT_LIT", yytext()); }
{STRING_LITERAL} { printToken("STRING_LIT", yytext()); }

/* ---------------------
   Identificadores
   --------------------- */
{ID}             { printToken("ID", yytext()); }

/* ---------------------
   Operadores aritméticos
   --------------------- */
"+"              { printToken("PLUS", yytext()); }
"-"              { printToken("MINUS", yytext()); }
"*"              { printToken("MULT", yytext()); }
"/"              { printToken("DIV", yytext()); }
"%"              { printToken("MOD", yytext()); }

/* ---------------------
   Operadores relacionales
   --------------------- */
"=="             { printToken("EQ", yytext()); }
"!="             { printToken("NEQ", yytext()); }
"<="             { printToken("LEQ", yytext()); }
">="             { printToken("GEQ", yytext()); }
"<"              { printToken("LT", yytext()); }
">"              { printToken("GT", yytext()); }

/* ---------------------
   Operadores lógicos
   --------------------- */
"&&"             { printToken("AND", yytext()); }
"||"             { printToken("OR", yytext()); }
"!"              { printToken("NOT", yytext()); }

/* ---------------------
   Asignación
   --------------------- */
"="              { printToken("ASSIGN", yytext()); }

/* ---------------------
   Puntuación
   --------------------- */
"("              { printToken("LPAREN", yytext()); }
")"              { printToken("RPAREN", yytext()); }
"{"              { printToken("LBRACE", yytext()); }
"}"              { printToken("RBRACE", yytext()); }
"["              { printToken("LBRACKET", yytext()); }
"]"              { printToken("RBRACKET", yytext()); }
","              { printToken("COMMA", yytext()); }
";"              { printToken("SEMICOLON", yytext()); }

/* ---------------------
   Error léxico
   --------------------- */
.                { System.err.println("Error léxico: '" + yytext() + 
                                     "' en línea " + (yyline + 1) + 
                                     ", columna " + (yycolumn + 1)); }
