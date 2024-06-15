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
}