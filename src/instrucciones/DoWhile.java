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
public class DoWhile extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public DoWhile(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        do {
            // Crear nuevo entorno para las instrucciones
            var newTabla = new tablaSimbolos(tabla);

            // Ejecutar instrucciones
            boolean continueLoop = false;
            for (var instruccion : this.instrucciones) {
                if (instruccion instanceof Break) {
                    return null; // Termina la ejecución del do-while por break
                } else if (instruccion instanceof Continue) {
                    continueLoop = true; // Marca que se debe continuar con la siguiente iteración
                    break; // Salir del for para reiniciar la iteración
                }
                
                var res = instruccion.interpretar(arbol, newTabla);
                if (res instanceof Break) {
                    return null; // Pasa el break hacia arriba
                }
                if (res instanceof Errores) {
                    return res;
                }
                
            }

            if (continueLoop) {
                continue; // Continuar con la siguiente iteración del do-while
            }

            // Validar la condición -> Booleano
            var cond = this.condicion.interpretar(arbol, tabla);
            if (cond instanceof Errores) {
                return cond;
            }

            if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
                return new Errores("SEMANTICO", "La condición debe ser bool", this.linea, this.col);
            }
        } while ((boolean) this.condicion.interpretar(arbol, tabla));

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}