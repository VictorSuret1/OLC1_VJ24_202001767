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
public class Find extends Instruccion {
    private String id;
    private Instruccion exp;

    public Find(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "Variable no declarada: " + id, linea, col);
        }

        Object valorSimbolo = simbolo.getValor();
        if (!(valorSimbolo instanceof LinkedList)) {
            return new Errores("SEMANTICO", "La variable no es una lista o vector: " + id, linea, col);
        }

        LinkedList<Object> lista = (LinkedList<Object>) valorSimbolo;
        Object valorExp = exp.interpretar(arbol, tabla);
        if (valorExp instanceof Errores) {
            return valorExp;
        }

        for (Object item : lista) {
            if (item.equals(valorExp)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}