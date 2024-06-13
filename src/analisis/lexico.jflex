package analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

//codigo de usuario
%{
    public static LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
listaErrores = new LinkedList<>();
%init}

//caracteristicas de jflex
%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase

//simbolos del sistema
PAR1="("
PAR2=")"
MAS="+"
MENOS="-"
MULTIPLICACION = "*"
DIVISION = "/"
MODULO = "%"
IGUAL = "="
POTENCIA = "**"
FINCADENA=";"
DOSP = ":"

OR = "||"
AND = "&&"
XOR = "^"
NOT = "!"

NOTEQUALS = "!="
MENOR="<"
MAYOR=">"
MAYORQUE=">="
MENORQUE = "<="



BLANCOS=[\ \r\t\f\n]+
ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
CADENA = [\"]([^\"])*[\"]
CARACTER = [\']([^\'])*[\']
ID=[a-zA-z][a-zA-Z0-9_]*

//palabras reservadas
PRINTLN="PRINTLN"
TRUE = "true"
FALSE = "false"
INT="int"
DOUBLE="double"
STRING="string"
CHAR = "char"
BOOL = "bool"
CONST = "const"
VAR = "var"

%%
<YYINITIAL> {PRINTLN} {return new Symbol(sym.PRINTLN, yyline, yycolumn,yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING} {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {CHAR} {return new Symbol(sym.CHAR, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {CONST} {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}
<YYINITIAL> {VAR} {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}


<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn,yytext());}

<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }

<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
    }

<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {DOSP} {return new Symbol(sym.DOSP, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}

<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {MULTIPLICACION} {return new Symbol(sym.MULTIPLICACION, yyline, yycolumn,yytext());}
<YYINITIAL> {DIVISION} {return new Symbol(sym.DIVISION, yyline, yycolumn,yytext());}
<YYINITIAL> {MODULO} {return new Symbol(sym.MODULO, yyline, yycolumn,yytext());}
<YYINITIAL> {POTENCIA} {return new Symbol(sym.POTENCIA, yyline, yycolumn,yytext());}

<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {NOTEQUALS} {return new Symbol(sym.NOTEQUALS, yyline, yycolumn,yytext());}

<YYINITIAL> {OR} {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> {AND} {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> {XOR} {return new Symbol(sym.XOR, yyline, yycolumn,yytext());}
<YYINITIAL> {NOT} {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}


<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR} {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYORQUE} {return new Symbol(sym.MAYORQUE, yyline, yycolumn,yytext());}
<YYINITIAL> {MENORQUE} {return new Symbol(sym.MENORQUE, yyline, yycolumn,yytext());}




<YYINITIAL> {BLANCOS} {}

<YYINITIAL> . {listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext()+" NO pertenece al lenguaje", yyline, yycolumn));
}
