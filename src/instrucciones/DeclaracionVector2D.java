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
}