/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class InstanciacionStruct extends Instruccion {
    private String id;
    private String nombreStruct;
    private HashMap<String, Instruccion> valores;
    private String mutabilidad;

    public InstanciacionStruct(String mutabilidad, String id, String nombreStruct, HashMap<String, Instruccion> valores, int linea, int col) {
        super(new Tipo(tipoDato.STRUCT), linea, col);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.nombreStruct = nombreStruct;
        this.valores = valores;
        
        System.out.println("Instanciacion Struct created: " + id + " " + nombreStruct + " " + valores + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo structDef = tabla.getVariable(nombreStruct);
        if (structDef == null) {
            arbol.getErrores().add(new Errores("Semántico", "Struct no declarado: " + nombreStruct, linea, col));
            return null;
        }

        HashMap<String, Object> structValores = new HashMap<>();
        for (String atributo : valores.keySet()) {
            Instruccion valorInstruccion = valores.get(atributo);
            Object valor = valorInstruccion.interpretar(arbol, tabla);
            structValores.put(atributo, valor);
        }

        Simbolo simbolo = new Simbolo(new Tipo(tipoDato.STRUCT), id, structValores);

        if (!tabla.setVariable(simbolo)) {
            arbol.getErrores().add(new Errores("Semántico", "Variable ya declarada: " + id, linea, col));
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}