/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author VictorS
 */

public class Continue extends Instruccion {

    public Continue(int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}