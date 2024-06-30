/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.AccesoVector;
import java.util.HashMap;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class ToString extends Instruccion {
    private Instruccion expresion;

    public ToString(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.CADENA), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = expresion.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        if (valorInterpretado instanceof Integer ||
            valorInterpretado instanceof Double ||
            valorInterpretado instanceof Boolean ||
            valorInterpretado instanceof Character ||
            valorInterpretado instanceof HashMap) {
            return valorInterpretado.toString();
        }

        return new Errores("Semántico", "Tipo de dato no permitido en toString", linea, col);
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}