package core;
import static core.Token.*;
%%
%class Lexer
%type Token
L = [a-z]
A = [A-Z]
D = [0-9]
white=[ ,\n]
%{
    public String lexeme;
%}
%%

<YYINITIAL> public {return PALABRA_RESERVADA;}
<YYINITIAL> void {return PALABRA_RESERVADA;}
<YYINITIAL> main {return PALABRA_RESERVADA;}
<YYINITIAL> _funtion_ {return PALABRA_RESERVADA;}
<YYINITIAL> String {return PALABRA_RESERVADA}

{white} {/*Ignore*/}
"//".* { /* Ignore */}
"=" {return ASSIGN;}
"==" {return EQUALS;}
({L}|{A}|_)({L}|{D}|{A}|_)* {lexeme=yytext(); return IDENTIFICADOR;}
[+-]?{D}+ {lexeme=yytext(); return INT;}
[+-]?{D}+[.]{D}+ {lexeme=yytext(); return FLOAT;}
"+" {return PLUS;}
"*" {return TIMES;}
"-" {return MINUS;}
. {return ERROR;}