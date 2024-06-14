/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.Errores;
import expresiones.*;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Incremento extends Instruccion {
    private String id;

    public Incremento(String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no existente", this.linea, this.col);
        }

        if (variable.getTipo().getTipo() == tipoDato.ENTERO) {
            int valor = (Integer) variable.getValor();
            variable.setValor(valor + 1);
        } else if (variable.getTipo().getTipo() == tipoDato.DECIMAL) {
            double valor = (Double) variable.getValor();
            variable.setValor(valor + 1.0);
        } else {
            return new Errores("SEMANTICO", "Tipo no soportado para incremento", this.linea, this.col);
        }

        return null;
    }
}