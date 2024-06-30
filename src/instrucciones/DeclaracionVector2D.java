/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import java.util.List;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */

public class DeclaracionVector2D extends Instruccion {
    private String mutabilidad;
    private String id;
    private LinkedList<LinkedList<Object>> valores;

    public DeclaracionVector2D(String mutabilidad, String id, LinkedList<LinkedList<Object>> valores, Tipo tipo, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = valores;
        
        System.out.print("DeclaracionVector2D created: " + mutabilidad + " " + id + " [");
        for (int i = 0; i < valores.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print("[");
            for (int j = 0; j < valores.get(i).size(); j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(valores.get(i).get(j));
            }
            System.out.print("]");
        }
        System.out.println("] " + tipo + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Crear el vector y agregarlo a la tabla de símbolos
        Simbolo simbolo = new Simbolo(tipo, id, valores);
        simbolo.setMutabilidad(mutabilidad);
        if (!tabla.setVariable(simbolo)) {
            arbol.getErrores().add(new Errores("Semántico", "Variable ya declarada: " + id, linea, col));
        }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
         String nodoPP = "n" + arbol.getContador();
    String nodom = "n" + arbol.getContador();
    String nodoi = "n" + arbol.getContador();
    String nodod = "n" + arbol.getContador();
    String nodoti = "n" + arbol.getContador();
    String nodoc1 = "n" + arbol.getContador();
    String nodoc2 = "n" + arbol.getContador();
    String nodoc21 = "n" + arbol.getContador();
    String nodoc212= "n" + arbol.getContador();
    String nodocig = "n" + arbol.getContador();
    String nodocc11 = "n" + arbol.getContador();
    String nodoccval = "n" + arbol.getContador();
    String nodoccvalo = "n" + arbol.getContador();
    String nodocc22 = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();
    String nodotipo = "n" + arbol.getContador();
    String nodoMM = "n" + arbol.getContador();
    String nodoID = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"DECLARACION VECTOR 2D\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoPP + " -> " + nodoMM + ";\n";
    resultado += nodoPP + " -> " + nodoID + ";\n";
    resultado += nodoPP + " -> " + nodod + ";\n";
    resultado += nodoPP + " -> " + nodotipo + ";\n";
    resultado += nodoPP + " -> " + nodoc1 + ";\n";
    resultado += nodoPP + " -> " + nodoc2 + ";\n";
    resultado += nodoPP + " -> " + nodoc21 + ";\n";
    resultado += nodoPP + " -> " + nodoc212 + ";\n";
    resultado += nodoPP + " -> " + nodocig + ";\n";
    resultado += nodoPP + " -> " + nodocc11 + ";\n";
    resultado += nodoPP + " -> " + nodoccval + ";\n";
    resultado += nodoPP + " -> " + nodocc22 + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    //<MUTABILIDAD> <ID> : <TIPO> [ ] [ ] = [ <LISTAVALORES> ] ;
        resultado += nodoMM + "[label=\" MUTABLIDAD \"];\n";
        resultado += nodom + "[label=\" "+mutabilidad.toString()+" \"];\n";
        resultado += nodoID + "[label=\" ID \"];\n";
        resultado += nodoi + "[label=\" "+ id.toString() +"  \"];\n";
        resultado += nodod + "[label=\" : \"];\n";
        resultado += nodotipo + "[label=\" Tipo \"];\n";
        resultado += nodoti + "[label=\" "+ tipo.getTipo().toString() +" \"];\n";
        resultado += nodoc1 + "[label=\" [ \"];\n";
        resultado += nodoc2 + "[label=\" ] \"];\n";
        resultado += nodoc21 + "[label=\" [ \"];\n";
        resultado += nodoc212 + "[label=\" ] \"];\n";
        resultado += nodocig + "[label=\" = \"];\n";
        resultado += nodocc11 + "[label=\" [ \"];\n";
        resultado += nodoccval + "[label=\" Valores \"];\n";
        resultado += nodoccvalo + "[label=\" "+ valores.toString()+" \"];\n";
        resultado += nodocc22 + "[label=\" ] \"];\n";
        resultado += nododp + "[label=\" ; \"];\n";
        
        
        resultado += nodoccval + " -> " + nodoccvalo+";\n";
        resultado += nodotipo + " -> " + nodoti+";\n";
        resultado += nodoMM + " -> " + nodom+";\n";
        resultado += nodoID + " -> " + nodoi+";\n";
        return resultado;
    }
}