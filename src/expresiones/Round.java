/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class Round extends Instruccion {
    private Instruccion expresion;

    public Round(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;

        System.out.println("Round function created: " + expresion + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = expresion.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        if (!(valorInterpretado instanceof Double || valorInterpretado instanceof Integer)) {
            arbol.getErrores().add(new Errores("Semántico", "La expresión no es un número: " + valorInterpretado, linea, col));
            return null;
        }

        double valor = ((Number) valorInterpretado).doubleValue();
        int resultado = (valor - Math.floor(valor) >= 0.5) ? (int) Math.ceil(valor) : (int) Math.floor(valor);

        return resultado;
    }

    @Override
    public String generarast(Arbol arbol, String anterior) {

        return "";
    }
}