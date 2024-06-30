/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Case extends Instruccion {
    private Instruccion expresion;
    private LinkedList<Instruccion> instrucciones;

    public Case(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);
        for (var instr : this.instrucciones) {
            var result = instr.interpretar(arbol, newTabla);
            if (result instanceof Errores) {
                return result;
            }
        }
        return null;
    }

    public Instruccion getExpresion() {
        return expresion;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
    
}