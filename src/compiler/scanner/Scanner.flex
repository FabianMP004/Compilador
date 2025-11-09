/* ============================================================
 * Scanner.flex
 * Analizador léxico para MiniC usando JFlex + JavaCUP
 * ============================================================
 */

package compiler.scanner;

import java_cup.runtime.Symbol;
import compiler.parser.sym;

%%

%public
%class Scanner
%unicode
%line
%column
%cup

%{

private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
}

private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
}

%}

/* Expresiones */
WHITESPACE       = [ \t\r\n]+
LETTER           = [a-zA-Z_]
DIGIT            = [0-9]
ID               = {LETTER}({LETTER}|{DIGIT})*
INT_LITERAL      = {DIGIT}+
FLOAT_LITERAL    = {DIGIT}+ "." {DIGIT}+
STRING_LITERAL   = \"([^\"\\]|\\.)*\"

LINE_COMMENT     = "//".*
BLOCK_COMMENT    = "/*"([^*]|\*+[^*/])*\*+"/"

%%

{WHITESPACE}     { }
{LINE_COMMENT}   { }
{BLOCK_COMMENT}  { }

/* Palabras reservadas */
"int"            { return symbol(sym.INT); }
"float"          { return symbol(sym.FLOAT); }
"if"             { return symbol(sym.IF); }
"else"           { return symbol(sym.ELSE); }
"while"          { return symbol(sym.WHILE); }
"return"         { return symbol(sym.RETURN); }
"void"           { return symbol(sym.VOID); }

/* Literales */
{FLOAT_LITERAL}  { return symbol(sym.FLOAT_LIT, yytext()); }
{INT_LITERAL}    { return symbol(sym.INT_LIT, yytext()); }
{STRING_LITERAL} { return symbol(sym.STRING_LIT, yytext()); }

/* Identificadores */
{ID}             { return symbol(sym.ID, yytext()); }

/* Operadores */
"+"              { return symbol(sym.PLUS); }
"-"              { return symbol(sym.MINUS); }
"*"              { return symbol(sym.MULT); }
"/"              { return symbol(sym.DIV); }
"%"              { return symbol(sym.MOD); }

"=="             { return symbol(sym.EQ); }
"!="             { return symbol(sym.NEQ); }
"<="             { return symbol(sym.LEQ); }
">="             { return symbol(sym.GEQ); }
"<"              { return symbol(sym.LT); }
">"              { return symbol(sym.GT); }

"&&"             { return symbol(sym.AND); }
"||"             { return symbol(sym.OR); }
"!"              { return symbol(sym.NOT); }

/* Asignación */
"="              { return symbol(sym.ASSIGN); }

/* Puntuación */
"("              { return symbol(sym.LPAREN); }
")"              { return symbol(sym.RPAREN); }
"{"              { return symbol(sym.LBRACE); }
"}"              { return symbol(sym.RBRACE); }
"["              { return symbol(sym.LBRACKET); }
"]"              { return symbol(sym.RBRACKET); }
","              { return symbol(sym.COMMA); }
";"              { return symbol(sym.SEMICOLON); }

/* Error */
. {
    System.err.println(
        "Error léxico '" + yytext() +
        "' línea " + (yyline + 1) +
        " columna " + (yycolumn + 1)
    );
}
