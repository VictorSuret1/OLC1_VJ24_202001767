/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class For extends Instruccion {
    private Instruccion asignacionInicial;
    private Instruccion condicion;
    private Instruccion incremento;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacionInicial, Instruccion condicion, Instruccion incremento, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.asignacionInicial = asignacionInicial;
        this.condicion = condicion;
        this.incremento = incremento;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Ejecutar asignación inicial
        this.asignacionInicial.interpretar(arbol, tabla);

        // Validar la condición -> Booleano
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condición debe ser bool", this.linea, this.col);
        }

        for (;;) {
            if ((boolean) this.condicion.interpretar(arbol, tabla)) {
                // Crear nuevo entorno para las instrucciones
                var newTabla = new tablaSimbolos(tabla);

                // Ejecutar instrucciones
                for (var instruccion : this.instrucciones) {
                    var res = instruccion.interpretar(arbol, newTabla);
                    if (res instanceof Break) {
                        return null; // Termina la ejecución del for por break
                    } else if (res instanceof Continue) {
                        break; // Continuar con la siguiente iteración del for
                    } else if (res instanceof Return) {
                        return res;
                    }
                    if (res instanceof Errores) {
                        return res;
                    }
                }

                // Ejecutar incremento
                this.incremento.interpretar(arbol, tabla);
            } else {
                break; // Salir del for cuando la condición sea falsa
            }
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        
    // for ( <ASIGNACIÓN> ; <CONDICIÓN> ; <ACTUALIZACIÓN> ) { <INSTRUCCIONES> }

    String nodoPP = "n" + arbol.getContador();
    String nodoFOR = "n" + arbol.getContador();
    String nodop1 = "n" + arbol.getContador();
    String nodoAS = "n" + arbol.getContador();
    String nodoASI = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();
    String nodoCON = "n" + arbol.getContador();
    String nododp2 = "n" + arbol.getContador();
    String nodoACT = "n" + arbol.getContador();
    String nodop2 = "n" + arbol.getContador();
    String nodoll = "n" + arbol.getContador();
    String nodoIN = "n" + arbol.getContador();
    String nodoll2 = "n" + arbol.getContador();
    String nodoINSTRUC = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"FOR\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoFOR + "[label=\"FOR\"];\n";
    resultado += nodop1 + "[label=\"(\"];\n";
    resultado += nodoAS + "[label=\"ASIGNACION\"];\n";
    resultado += nododp + "[label=\";\"];\n";
    resultado += nodoCON + "[label=\"CONDICION\"];\n";
    resultado += nododp2 + "[label=\";\"];\n";
    resultado += nodoACT + "[label=\"ACTUALIZACION\"];\n";
    resultado += nodop2 + "[label=\")\"];\n";
    resultado += nodoll + "[label=\"{\"];\n";
    resultado += nodoIN + "[label=\"INSTRUCCIONES\"];\n";
    resultado += nodoll2 + "[label=\"}\"];\n";

    resultado += nodoPP + " -> " + nodoFOR + ";\n";
    resultado += nodoPP + " -> " + nodop1 + ";\n";
    resultado += nodoPP + " -> " + nodoAS + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    resultado += nodoPP + " -> " + nodoCON + ";\n";
    resultado += nodoPP + " -> " + nododp2 + ";\n";
    resultado += nodoPP + " -> " + nodoACT + ";\n";
    resultado += nodoPP + " -> " + nodop2 + ";\n";
    resultado += nodoPP + " -> " + nodoll + ";\n";
    resultado += nodoPP + " -> " + nodoIN + ";\n";
    resultado += nodoPP + " -> " + nodoll2 + ";\n";

    

    // Añadir las instrucciones dentro del for
    if (instrucciones != null && !instrucciones.isEmpty()) {
        for (Instruccion instr : instrucciones) {
            if (instr != null) {
                resultado += instr.generarast(arbol, nodoIN);
            }
        }
    }

    return resultado;
}
}