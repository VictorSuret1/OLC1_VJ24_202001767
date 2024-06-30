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
public class IfElseIf extends Instruccion {
    private Instruccion condicionIf;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion condicionElseIf;
    private LinkedList<Instruccion> instruccionesElseIf;

    public IfElseIf(Instruccion condicionIf, LinkedList<Instruccion> instruccionesIf, Instruccion condicionElseIf, LinkedList<Instruccion> instruccionesElseIf, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicionIf = condicionIf;
        this.instruccionesIf = instruccionesIf;
        this.condicionElseIf = condicionElseIf;
        this.instruccionesElseIf = instruccionesElseIf;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIf = this.condicionIf.interpretar(arbol, tabla);
        if (condIf instanceof Errores) {
            return condIf;
        }

        if (this.condicionIf.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida en IF", this.linea, this.col);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) condIf) {
            for (var i : this.instruccionesIf) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Errores) {
                    return resultado;
                }
                if (resultado instanceof Continue) {
                    return resultado;
                }
                if (resultado instanceof Break) {
                    return resultado;
                }
            }
        } else {
            var condElseIf = this.condicionElseIf.interpretar(arbol, tabla);
            if (condElseIf instanceof Errores) {
                return condElseIf;
            }

            if (this.condicionElseIf.tipo.getTipo() != tipoDato.BOOLEANO) {
                return new Errores("SEMANTICO", "Expresion invalida en ELSE IF", this.linea, this.col);
            }

            if ((boolean) condElseIf) {
                for (var i : this.instruccionesElseIf) {
                    var resultado = i.interpretar(arbol, newTabla);
                    if (resultado instanceof Errores) {
                        return resultado;
                    }
                    if (resultado instanceof Continue) {
                    return resultado;
                }
                    if (resultado instanceof Break) {
                    return resultado;
                }
                }
            }
        }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}