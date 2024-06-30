/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.HashMap;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class AsignacionStruct extends Instruccion {
    private String idStruct;
    private String campo;
    private Instruccion valor;

    public AsignacionStruct(String idStruct, String campo, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.idStruct = idStruct;
        this.campo = campo;
        this.valor = valor;

        System.out.println("AsignacionStruct created: " + idStruct + "." + campo + " = " + valor + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(idStruct);

        if (simbolo == null) {
            arbol.getErrores().add(new Errores("Semántico", "Variable no declarada: " + idStruct, linea, col));
            return null;
        }

        if (!(simbolo.getValor() instanceof HashMap)) {
            arbol.getErrores().add(new Errores("Semántico", "La variable no es un struct: " + idStruct, linea, col));
            return null;
        }

        if ("CONST".equals(simbolo.getMutabilidad())) {
            arbol.getErrores().add(new Errores("Semántico", "No se puede modificar una variable const: " + idStruct, linea, col));
            return null;
        }

        HashMap<String, Object> structValores = (HashMap<String, Object>) simbolo.getValor();
        if (!structValores.containsKey(campo)) {
            arbol.getErrores().add(new Errores("Semántico", "El campo no existe en el struct: " + campo, linea, col));
            return null;
        }

        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        structValores.put(campo, valorInterpretado);
        simbolo.setValor(structValores);

        return null;
    }

    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}