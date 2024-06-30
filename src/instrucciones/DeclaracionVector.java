/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.Errores;
import expresiones.Nativo;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
import java.util.LinkedList;
import java.util.List;

public class DeclaracionVector extends Instruccion {
    private String mutabilidad;
    private String id;
    private LinkedList<Object> valores;

    public DeclaracionVector(String mutabilidad, String id, LinkedList<Object> valores, Tipo tipo, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = valores;
        
        System.out.println("DeclaracionVector created: " + mutabilidad + " " + id + " " + valores + " " + tipo + " " + linea + " " + col);
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
    String nodocig = "n" + arbol.getContador();
    String nodocc11 = "n" + arbol.getContador();
    String nodoccval = "n" + arbol.getContador();
    String nodocc22 = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"DECLARACION VECTOR\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoPP + " -> " + nodom + ";\n";
    resultado += nodoPP + " -> " + nodoi + ";\n";
    resultado += nodoPP + " -> " + nodod + ";\n";
    resultado += nodoPP + " -> " + nodoti + ";\n";
    resultado += nodoPP + " -> " + nodoc1 + ";\n";
    resultado += nodoPP + " -> " + nodoc2 + ";\n";
    resultado += nodoPP + " -> " + nodocig + ";\n";
    resultado += nodoPP + " -> " + nodocc11 + ";\n";
    resultado += nodoPP + " -> " + nodoccval + ";\n";
    resultado += nodoPP + " -> " + nodocc22 + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    //<MUTABILIDAD> <ID> : <TIPO> [ ] = [ <LISTAVALORES> ] ;
        resultado += nodom + "[label=\" "+mutabilidad.toString()+" \"];\n";
        resultado += nodoi + "[label=\" "+ id.toString() +"  \"];\n";
        resultado += nodod + "[label=\" : \"];\n";
        resultado += nodoti + "[label=\" "+ tipo.getTipo().toString() +" \"];\n";
        resultado += nodoc1 + "[label=\" [ \"];\n";
        resultado += nodoc2 + "[label=\" ] \"];\n";
        resultado += nodocig + "[label=\" = \"];\n";
        resultado += nodocc11 + "[label=\" [ \"];\n";
        resultado += nodoccval + "[label=\" "+valores.toString()+" \"];\n";
        resultado += nodocc22 + "[label=\" ] \"];\n";
        resultado += nododp + "[label=\" ; \"];\n";
        
        return resultado;
    }
}