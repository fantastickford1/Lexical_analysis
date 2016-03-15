package Parsing;

import core.Token;

import java.util.ArrayList;

/**
 * Created by Karlos on 3/12/2016.
 */
public class SyntacticAnalysis {

    private ArrayList tokens;
    private int indice;

    public SyntacticAnalysis(ArrayList tokens){
        this.tokens = tokens;
        this.indice = 0;
    }

    public void dcplus(){ //<DCPlus> -> <DCL> <FUNTION>
        declaracion();
        funtion_main();
        System.out.println("Cadena valida");
    }

    //<Funtion> -> public funtion identificador { <DCL> } <Funtion> | public void main { <DCL> } <Funtion> | private funtion identificador { <DCL> } <Funtion> | VACIO
    private void funtion_main() {
        if (tokens.get(indice) == Token.RESERVADA_PUBLIC){
            indice++;
            if (tokens.get(indice) == Token.RESERVADA_FUNTION){
                indice++;
                if (tokens.get(indice) == Token.IDENTIFICADOR){
                    indice++;
                    if (tokens.get(indice) == Token.OPENKEY){
                        indice++;
                        declaracion();
                        if (tokens.get(indice) == Token.CLOSEKEY){
                            indice++;
                            funtion_main();
                        }else {
                            System.out.println("Se esperaba un '}'");
                            System.exit(0);
                        }
                    }else {
                        System.out.println("Se esperaba un '{'");
                        System.exit(0);
                    }
                }else {
                    System.out.println("Se esperaba un identificador");
                    System.exit(0);
                }
            }else if (tokens.get(indice) == Token.RESERVADA_VOID){
                indice++;
                if (tokens.get(indice) == Token.RESERVADA_MAIN){
                    indice++;
                    if (tokens.get(indice) == Token.OPENKEY){
                        indice ++;
                        declaracion();
                        if (tokens.get(indice) == Token.CLOSEKEY){
                            indice ++;
                            funtion_main();
                        }else{
                            System.out.println("se esperaba '}'");
                            System.exit(0);
                        }
                    }else{
                        System.out.println("se esperaba '{'");
                        System.exit(0);
                    }
                }else{
                    System.out.println("se esperaba main");
                    System.exit(0);
                }
            }else {
                System.out.println("Se esperaba _funtion_ o void");
                System.exit(0);
            }
        }else if (tokens.get(indice) == Token.RESERVADA_PRIVATE){
            indice++;
            if (tokens.get(indice) == Token.RESERVADA_FUNTION){
                indice++;
                if (tokens.get(indice) == Token.IDENTIFICADOR){
                    indice++;
                    if (tokens.get(indice) == Token.OPENKEY){
                        indice++;
                        declaracion();
                        if (tokens.get(indice) == Token.CLOSEKEY){
                            indice++;
                            funtion_main();
                        }else {
                            System.out.println("Se esperaba un '}'");
                            System.exit(0);
                        }
                    }else {
                        System.out.println("Se esperaba un '{'");
                        System.exit(0);
                    }
                }else {
                    System.out.println("Se esperaba un identificador");
                    System.exit(0);
                }
            }else {
                System.out.println("Se esperaba la palabra reservada _funtion_");
                System.exit(0);
            }
        }
        //vacio
    }
// <DCL> -> var identificador <RDCL> <ASIG> <DCL> | identificador <RDCL> <ASIG> <DCL> | Vacio
    private void declaracion() {
        if (tokens.get(indice) == Token.RESERVADA_VAR){
            indice++;
            if (tokens.get(indice) == Token.IDENTIFICADOR){
                indice++;
                restoDeclaracion();
                asignacion();
                declaracion();
            }else {
                System.out.println("Se esperaba un identificador");
                System.exit(0);
            }
        }else if (tokens.get(indice) == Token.IDENTIFICADOR){
            indice++;
            restoDeclaracion();
            asignacion();
            declaracion();
        }
        //VACIO//
    }
// <RDCL> -> ; identificador <RDCL> <ASIG> | Vacio
    private void restoDeclaracion() {
        if (tokens.get(indice) == Token.COMA){
            indice++;
            if (tokens.get(indice) == Token.IDENTIFICADOR){
                indice++;
                restoDeclaracion();
                asignacion();
            }else {
                System.out.println("Se esperaba un identificador");
                System.exit(0);
            }
        }
        //VACIO
    }
// <ASIG> -> = <DATO> <OPR> <RDCL> | Vacio
    private void asignacion() {
        if (tokens.get(indice) == Token.ASSIGN){
            indice++;
            dato();
            operacion();
            restoDeclaracion();
        }
        //VACIO
    }
//<OPR> -> * <DATO> <RDCL>| +  <DATO> <RDCL>| - <DATO> <RDCL>
    private void operacion() {
        if (tokens.get(indice) == Token.TIMES){
            indice++;
            dato();
            restoDeclaracion();
        }else if (tokens.get(indice) == Token.PLUS){
            indice++;
            dato();
            restoDeclaracion();
        }else if (tokens.get(indice) == Token.MINUS){
            indice++;
            dato();
            restoDeclaracion();
        }
        //VACIO
    }

    // <DATO> -> int <OPR> | string <OPR> | float <OPR>
    private void dato() {
        if (tokens.get(indice) == Token.INT){
            indice++;
            operacion();
        }else if (tokens.get(indice) == Token.STRING){
            indice++;
            operacion();
        }else if (tokens.get(indice) == Token.FLOAT){
            indice++;
            operacion();
        }else if (tokens.get(indice) == Token.IDENTIFICADOR){
            indice++;
            operacion();
        }else {
            System.out.println("Se esperaba un dato o identificador");
            System.exit(0);
        }
    }


}
