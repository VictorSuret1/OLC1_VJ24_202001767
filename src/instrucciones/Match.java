/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import static analisis.scanner.listaErrores;
import excepciones.*;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Match extends Instruccion {
    private Instruccion expresion;
    private LinkedList<Case> casos;
    private LinkedList<Instruccion> defaultInstrucciones;

    public Match(Instruccion expresion, LinkedList<Case> casos, LinkedList<Instruccion> defaultInstrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.casos = casos;
        this.defaultInstrucciones = defaultInstrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorExpresion = expresion.interpretar(arbol, tabla);
        
        if (valorExpresion instanceof Errores) {
            return valorExpresion;
        }

        for (var caso : casos) {
            var valorCaso = caso.getExpresion().interpretar(arbol, tabla);
            if (valorCaso instanceof Errores) {
                return valorCaso;
            }

            if (valorExpresion.equals(valorCaso)) {
                return caso.interpretar(arbol, tabla);
            }
        }

        if (defaultInstrucciones != null) {
            var newTabla = new tablaSimbolos(tabla);
            for (var instr : defaultInstrucciones) {
                var result = instr.interpretar(arbol, newTabla);
                if (result instanceof Errores) {
                    return result;
                }
            }
        }

        return null; // Si no hay ningún caso que coincida y no hay cláusula default
    }

    public LinkedList<Instruccion> getDefaultInstrucciones() {
        return defaultInstrucciones;
    }
}