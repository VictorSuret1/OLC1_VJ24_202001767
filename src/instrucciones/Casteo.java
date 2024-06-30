/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.*;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Casteo extends Instruccion {
    private Tipo tipoDestino;
    private Instruccion expresion;

    public Casteo(Tipo tipoDestino, Instruccion expresion, int linea, int col) {
        super(tipoDestino, linea, col);
        this.tipoDestino = tipoDestino;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = expresion.interpretar(arbol, tabla);
        if (valor instanceof Errores) {
            return valor;
        }

        switch (tipoDestino.getTipo()) {
            case ENTERO:
                if (expresion.tipo.getTipo() == tipoDato.DECIMAL) {
                    return ((Double) valor).intValue();
                } else if (expresion.tipo.getTipo() == tipoDato.CARACTER) {
                    return (int) ((Character) valor).charValue();
                }
                break;
            case DECIMAL:
                if (expresion.tipo.getTipo() == tipoDato.ENTERO) {
                    return ((Integer) valor).doubleValue();
                } else if (expresion.tipo.getTipo() == tipoDato.CARACTER) {
                    return (double) ((Character) valor).charValue();
                }
                break;
            case CARACTER:
                if (expresion.tipo.getTipo() == tipoDato.ENTERO) {
                    return (char) ((Integer) valor).intValue();
                }
                break;
        }
        return new Errores("SEMANTICO", "Casteo inválido", this.linea, this.col);
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}