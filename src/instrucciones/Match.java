/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.*;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Match extends Instruccion {
    private Instruccion expresion;
    private LinkedList<Case> cases;
    private DefaultCase defaultCase;

    public Match(Instruccion expresion, LinkedList<Case> cases, DefaultCase defaultCase, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.cases = cases;
        this.defaultCase = defaultCase;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = this.expresion.interpretar(arbol, tabla);
        if (valor instanceof Errores) {
            return valor; // Manejar error de interpretación de expresión
        }

        // Iterar sobre los casos
        for (Case c : cases) {
            Object caseValor = c.getExpresion().interpretar(arbol, tabla);
            if (caseValor instanceof Errores) {
                return caseValor; // Manejar error de interpretación de caso
            }
            if (caseValor.equals(valor)) {
                tablaSimbolos newTabla = new tablaSimbolos(tabla);
                for (Instruccion instr : c.getInstrucciones()) {
                    Object result = instr.interpretar(arbol, newTabla);
                    if (result instanceof Errores) {
                        return result; // Manejar error durante la ejecución de instrucciones
                    }
                }
                return null; // Retornar correctamente si se ejecutaron instrucciones del caso
            }
        }

        // Si no se encontró ningún caso, ejecutar el caso por defecto (DefaultCase) si existe
        if (defaultCase != null) {
    tablaSimbolos newTabla = new tablaSimbolos(tabla);
    for (Instruccion instr : defaultCase.getInstrucciones()) {
        Object result = instr.interpretar(arbol, newTabla);
        if (result instanceof Errores) {
            return result; // Manejar error durante la ejecución de instrucciones del caso por defecto
        }
    }
    return null; // Retornar correctamente si se ejecutaron instrucciones del caso por defecto
}

        return null; // No se ejecutó ningún caso ni el caso por defecto
    }
}