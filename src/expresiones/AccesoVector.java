/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
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
public class AccesoVector extends Instruccion {
    private String id;
    private Instruccion indice;

    public AccesoVector(String id, Instruccion indice, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indice = indice;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            arbol.getErrores().add(new Errores("Semántico", "Variable no declarada: " + id, linea, col));
            return null;
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            arbol.getErrores().add(new Errores("Semántico", "La variable no es un vector: " + id, linea, col));
            return null;
        }

        LinkedList<Object> vector = (LinkedList<Object>) simbolo.getValor();
        Object valorIndice = indice.interpretar(arbol, tabla);
        if (!(valorIndice instanceof Integer)) {
            arbol.getErrores().add(new Errores("Semántico", "El índice debe ser un entero: " + id, linea, col));
            return null;
        }

        int i = (int) valorIndice;
        if (i < 0 || i >= vector.size()) {
            arbol.getErrores().add(new Errores("Semántico", "Índice fuera de rango: " + id, linea, col));
            return null;
        }

        return vector.get(i);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instruccion getIndice() {
        return indice;
    }

    public void setIndice(Instruccion indice) {
        this.indice = indice;
    }
    
    
}