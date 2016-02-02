package core;
import static core.Token.*;
%%
%class Lexer
%type Token
L = [a-zA-Z_]
D = [0-9]
A=["public","void","main","_funtion_"]
white=[ ,\n]
%{
    public String lexeme;
%}
%%
{white} {/*Ignore*/}
"//".* { /* ignore */}
"=" {return ASSIGN;}
"==" {return EQUALS;}
{A} {lexeme=yytext(); return PALABRA_RESERVADA;}
{L}({L}|{D})* {lexeme=yytext(); return IDENTIFICADOR;}
["]({L}|{D})*["] {lexeme=yytext(); return STRING;}
[+-]?{D}+ {lexeme=yytext(); return INT;}
[+-]?{D}+[.]{D}+ {lexeme=yytext(); return FLOAT;}
"+" {return PLUS;}
"*" {return TIMES;}
"-" {return MINUS;}
. {return ERROR;}