/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class DeclaracionStruct extends Instruccion {
    private String id;
    private HashMap<String, Tipo> atributos;

    public DeclaracionStruct(String id, HashMap<String, Tipo> atributos, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.atributos = atributos;
        
        System.out.println("Declaracion Struct created: " + id + " " + atributos + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<Object> struct = new LinkedList<>();
        Simbolo simbolo = new Simbolo(tipo, id, struct);

        if (!tabla.setVariable(simbolo)) {
            arbol.getErrores().add(new Errores("Semántico", "Struct ya declarado: " + id, linea, col));
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        
        // STRUCT { LISTA } ID ;
         String nodoPP = "n" + arbol.getContador();
    String nodot = "n" + arbol.getContador();
    String nodot1 = "n" + arbol.getContador();
    String nodottipo = "n" + arbol.getContador();
    String nodot2 = "n" + arbol.getContador();
    String nodoid = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"DECLARACION STRUCT\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoPP + " -> " + nodot + ";\n";
    resultado += nodoPP + " -> " + nodot1 + ";\n";
    resultado += nodoPP + " -> " + nodottipo + ";\n";
    resultado += nodoPP + " -> " + nodot2 + ";\n";
    resultado += nodoPP + " -> " + nodoid + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    
        resultado += nodot + "[label=\" STRUCT \"];\n";
        resultado += nodot1 + "[label=\" {  \"];\n";
        resultado += nodottipo + "[label=\" ATRIBUTOS \"];\n";
        resultado += nodot2 + "[label=\" } \"];\n";
        resultado += nodoid + "[label=\" "+ id.toString() +" \"];\n";
        resultado += nododp + "[label=\" ; \"];\n";
        
        return resultado;
    }
}