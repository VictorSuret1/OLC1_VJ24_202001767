/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class Logicos extends Instruccion{
    private Instruccion exp1;
    private Instruccion exp2;
    private OperadoresLogicos logico;

    public Logicos(Tipo tipo, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.logico = logico;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        

    }

    
}
