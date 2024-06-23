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
}