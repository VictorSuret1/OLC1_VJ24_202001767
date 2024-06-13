/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;
import excepciones.Errores;

/**
 *
 * @author VictorS
 */
public class Logicos extends Instruccion {
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos logico;

    public Logicos(Instruccion cond1, Instruccion cond2, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.logico = logico;
    }

    public Logicos(Instruccion cond1, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.logico = logico;
        this.cond2 = null; // Para el caso del operador NOT que solo necesita una condición
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        condIzq = convertirABooleano(condIzq);

        if (this.logico == OperadoresLogicos.NOT) {
            return this.not(condIzq);
        }

        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }

        condDer = convertirABooleano(condDer);

        return switch (logico) {
            case OR -> this.or(condIzq, condDer);
            case AND -> this.and(condIzq, condDer);
            case XOR -> this.xor(condIzq, condDer);
            default -> new Errores("SEMANTICO", "Operador Lógico Invalido", this.linea, this.col);
        };
    }

    private boolean convertirABooleano(Object valor) {
        if (valor instanceof Boolean) {
            return (Boolean) valor;
        }
        if (valor instanceof Integer) {
            return (Integer) valor != 0;
        }
        if (valor instanceof Double) {
            return (Double) valor != 0.0;
        }
        if (valor instanceof String) {
            return !((String) valor).isEmpty();
        }
        if (valor instanceof Character) {
            return ((Character) valor) != '\u0000';
        }
        throw new RuntimeException("Tipos incompatibles para operación lógica");
    }

    private Object or(Object condIzq, Object condDer) {
        if (condIzq instanceof Boolean && condDer instanceof Boolean) {
            boolean izq = (Boolean) condIzq;
            boolean der = (Boolean) condDer;
            return izq || der;
        }
        return new Errores("SEMANTICO", "Tipos incompatibles para el operador OR", this.linea, this.col);
    }

    private Object and(Object condIzq, Object condDer) {
        if (condIzq instanceof Boolean && condDer instanceof Boolean) {
            boolean izq = (Boolean) condIzq;
            boolean der = (Boolean) condDer;
            return izq && der;
        }
        return new Errores("SEMANTICO", "Tipos incompatibles para el operador AND", this.linea, this.col);
    }

    private Object xor(Object condIzq, Object condDer) {
        if (condIzq instanceof Boolean && condDer instanceof Boolean) {
            boolean izq = (Boolean) condIzq;
            boolean der = (Boolean) condDer;
            return izq ^ der;
        }
        return new Errores("SEMANTICO", "Tipos incompatibles para el operador XOR", this.linea, this.col);
    }

    private Object not(Object cond) {
        if (cond instanceof Boolean) {
            boolean valor = (Boolean) cond;
            return !valor;
        }
        return new Errores("SEMANTICO", "Tipos incompatibles para el operador NOT", this.linea, this.col);
    }
}