/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;


/**
 *
 * @author VictorS
 */
public class Length extends Instruccion {
    private Instruccion expresion;

    public Length(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = expresion.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        if (valorInterpretado instanceof LinkedList) {
            return ((LinkedList<?>) valorInterpretado).size();
        } else if (valorInterpretado instanceof String) {
            return ((String) valorInterpretado).length();
        }

        return new Errores("Semántico", "Tipo de dato no permitido en length", linea, col);
    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}