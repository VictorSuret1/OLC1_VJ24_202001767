/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;

/**
 *
 * @author VictorS
 */
public class Return extends Instruccion {
    private Object valor;
    private Tipo tipo;

    public Return(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this;
    }

    public Object getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }
}