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
public class Append extends Instruccion {
    private String id;
    private Instruccion valor;

    public Append(String id, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);

        if (simbolo == null) {
            arbol.getErrores().add(new Errores("Semántico", "Variable no declarada: " + id, linea, col));
            return null;
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            arbol.getErrores().add(new Errores("Semántico", "La variable no es una lista: " + id, linea, col));
            return null;
        }

        LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();
        Object valorInterpretado = valor.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        lista.add(valorInterpretado);
        simbolo.setValor(lista);

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
    
}