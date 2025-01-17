/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class Print extends Instruccion {
    private Instruccion expresion;

    public Print(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores) {
            return resultado;
        }
        // Si el resultado es una función, obtenemos su valor de retorno
        if (resultado instanceof Funcion) {
            resultado = ((Funcion) resultado).interpretar(arbol, tabla);
        }
        
        
        arbol.Print(resultado.toString()); // Imprimir el resultado
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        // PRINTT -> PRINT ( EXP ) ;
        String nodoPP = "n" + arbol.getContador();
        String nodoP = "n" + arbol.getContador();
        String nodoP1 = "n" + arbol.getContador();
        String nodoExp = "n" + arbol.getContador();
        String nodoP2 = "n" + arbol.getContador();
        String nodoPC = "n" + arbol.getContador();

        String resultado = nodoPP + "[label=\"PRINT\"];\n";
        resultado += anterior + " -> " + nodoPP + ";\n";

        resultado += nodoP + "[label=\"println\"];\n";
        resultado += nodoP1 + "[label=\"(\"];\n";
        resultado += nodoExp + "[label=\"EXPRESION\"];\n";
        resultado += nodoP2 + "[label=\")\"];\n";
        resultado += nodoPC + "[label=\";\"];\n";

        resultado += nodoPP + " -> " + nodoP + ";\n";
        resultado += nodoPP + " -> " + nodoP1 + ";\n";
        resultado += nodoPP + " -> " + nodoExp + ";\n";
        resultado += nodoPP + " -> " + nodoP2 + ";\n";
        resultado += nodoPP + " -> " + nodoPC + ";\n";

        resultado += this.expresion.generarast(arbol, nodoExp);

        return resultado;
    }
}