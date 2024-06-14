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
public class IfElse extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private LinkedList<Instruccion> instruccionesElse;

    public IfElse(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, LinkedList<Instruccion> instruccionesElse, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida", this.linea, this.col);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {
            for (var i : this.instruccionesIf) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Errores) {
                    return resultado;
                }
            }
        } else {
            for (var i : this.instruccionesElse) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Errores) {
                    return resultado;
                }
            }
        }
        return null;
    }
}