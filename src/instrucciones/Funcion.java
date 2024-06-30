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
import simbolo.tipoDato;

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
        for (var instruccion : this.instrucciones) {
            var resultado = instruccion.interpretar(arbol, tabla);

            if (resultado instanceof Return) {
            Return retorno = (Return) resultado;

            // Devolver el valor del return
            return retorno.getValor();
        }

            if (resultado instanceof Errores) {
                arbol.getErrores().add((Errores) resultado);
                return null;
            }

            if (instruccion instanceof Break || instruccion instanceof Continue) {
                arbol.getErrores().add(new Errores("Semantico", "Instrucción break o continue fuera de ciclo", this.linea, this.col));
                return null;
            }
        }

        if (this.tipo.getTipo() != tipoDato.VOID) {
        arbol.getErrores().add(new Errores("Semántico", "Falta instrucción return en la función: " + this.id, this.linea, this.col));
    }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String resultado = "";
        for (var i : this.instrucciones) {
            resultado += i.generarast(arbol, anterior);
        }
        return resultado;
    }
}