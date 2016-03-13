package core;
import static core.Token.*;
%%
%class Lexer
%type Token
L = [a-z]
A = [A-Z]
D = [0-9]
white=[ ,\n,\t,\b,\r,\a]
%{
    public String lexeme;
%}
%%

<YYINITIAL> public {return RESERVADA_PUBLIC;}
<YYINITIAL> void {return RESERVADA_VOID;}
<YYINITIAL> main {return RESERVADA_MAIN;}
<YYINITIAL> _funtion_ {return RESERVADA_FUNTION;}
<YYINITIAL> String {return RESERVADA_STRING;}
<YYINITIAL> private {return RESERVADA_PRIVATE;}
<YYINITIAL> var {return RESERVADA_VAR;}

{white} {/*Ignore*/}
"//".* { /* Ignore */}
"=" {return ASSIGN;}
"==" {return EQUALS;}
({L}|{A}|_)({L}|{D}|{A}|_)* {lexeme=yytext(); return IDENTIFICADOR;}
[+-]?{D}+ {lexeme=yytext(); return INT;}
[+-]?{D}+[.]{D}+ {lexeme=yytext(); return FLOAT;}
\".*\" {lexeme=yytext(); return STRING;}
"+" {return PLUS;}
"*" {return TIMES;}
"-" {return MINUS;}
"{" {return OPENKEY;}
"}" {return CLOSEKEY;}
. {return ERROR;}