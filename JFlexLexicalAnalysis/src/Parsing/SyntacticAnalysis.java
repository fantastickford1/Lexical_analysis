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

    public void dcplus(){
        funtion_main();
        System.out.println("Cadena valida");
    }

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
        }else{
            System.out.print("Se esperaba un acceso");
            System.exit(0);
        }
    }

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

    private void asignacion() {
        if (tokens.get(indice) == Token.ASSIGN){
            indice++;
            dato();
            restoDeclaracion();
        }
        //VACIO
    }

    private void dato() {
        if (tokens.get(indice) == Token.INT){
            indice++;
        }else if (tokens.get(indice) == Token.STRING){
            indice++;
        }else if (tokens.get(indice) == Token.FLOAT){
            indice++;
        }else {
            System.out.println("Se esperaba un dato");
            System.exit(0);
        }
    }




}
