/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class DefaultCase extends Instruccion {
    private LinkedList<Instruccion> instrucciones;

    public DefaultCase(LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Implementar la lógica de interpretación del caso por defecto
        return null;
        // Implementar la lógica de interpretación del caso por defecto
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }
}