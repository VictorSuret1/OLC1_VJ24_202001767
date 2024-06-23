/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;

/**
 *
 * @author VictorS
 */
public class Funcion extends Instruccion {
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;
    public Tipo tipo;

    public Funcion(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.tipo = tipo;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object resultado = null;
        for (var i : this.instrucciones) {
            resultado = i.interpretar(arbol, tabla);
            if (resultado instanceof Return) {
                if (!((Return) resultado).getTipo().equals(this.tipo)) {
                    arbol.getErrores().add(new Errores("Semantico", "El tipo de retorno no coincide con el tipo de la función", linea, col));
                    return null;
                }
                return ((Return) resultado).getValor();
            }
        }
        arbol.getErrores().add(new Errores("Semantico", "La función debe tener una instrucción de retorno", linea, col));
        return null;
    }
}