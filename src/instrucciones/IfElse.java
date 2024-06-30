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
public class IfElse extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private LinkedList<Instruccion> instruccionesElse;

    public IfElse(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, LinkedList<Instruccion> instruccionesElse, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // ver que cond sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida",
                    this.linea, this.col);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {
            for (var i : this.instruccionesIf) {
                if (i instanceof Break) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break) {
                    return resultado;
                }
                if (resultado instanceof Continue) {
                    return resultado;
                }
                
                if (resultado instanceof Return) {
                    return resultado;
                }
                /*
                    Manejo de errores
                 */
            }
        } else if (this.instruccionesElse != null) {
            for (var i : this.instruccionesElse) {
                if (i instanceof Break) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break) {
                    return resultado;
                }
                if (resultado instanceof Continue) {
                    return resultado;
                }
                if (resultado instanceof Return) {
                    return resultado;
                }
                /*
                    Manejo de errores
                 */
            }
        }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
            //if ( <EXPRESION> ) { <INSTRUCCIONES> } else { instrucciones }
        
        String nodoPP = "n" + arbol.getContador();
    String nodoFOR = "n" + arbol.getContador();
    String nodop1 = "n" + arbol.getContador();
    String nodoAS = "n" + arbol.getContador();
    String nodoASI = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();
    String nodoCON = "n" + arbol.getContador();
    String nododp2 = "n" + arbol.getContador();
    String nodoE = "n" + arbol.getContador();
    String nodoL2 = "n" + arbol.getContador();
    String nodoIN = "n" + arbol.getContador();
    String nodoLL2 = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"IF\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoFOR + "[label=\"IF\"];\n";
    resultado += nodop1 + "[label=\"(\"];\n";
    resultado += nodoAS + "[label=\"EXPRESION\"];\n";
    resultado += nododp + "[label=\")\"];\n";
    resultado += nodoCON + "[label=\"{\"];\n";
    resultado += nododp2 + "[label=\"INSTRUCCIONES\"];\n";
    resultado += nodoASI + "[label=\"}\"];\n";
    resultado += nodoE + "[label=\" ELSE \"];\n";
    resultado += nodoL2 + "[label=\"{\"];\n";
    resultado += nodoIN + "[label=\"INSTRUCCIONES\"];\n";
    resultado += nodoLL2 + "[label=\"}\"];\n";
    

    resultado += nodoPP + " -> " + nodoFOR + ";\n";
    resultado += nodoPP + " -> " + nodop1 + ";\n";
    resultado += nodoPP + " -> " + nodoAS + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    resultado += nodoPP + " -> " + nodoCON + ";\n";
    resultado += nodoPP + " -> " + nododp2 + ";\n";
    resultado += nodoPP + " -> " + nodoASI + ";\n";
    resultado += nodoPP + " -> " + nodoE + ";\n";
    resultado += nodoPP + " -> " + nodoL2 + ";\n";
    resultado += nodoPP + " -> " + nodoIN + ";\n";
    resultado += nodoPP + " -> " + nodoLL2 + ";\n";

    resultado += this.condicion.generarast(arbol, nodoAS);
    // Añadir las instrucciones dentro del for
    if (instruccionesIf != null && !instruccionesIf.isEmpty()) {
        for (Instruccion instr : instruccionesIf) {
            if (instr != null) {
                resultado += instr.generarast(arbol, nododp2);
            }
        }
    }
    
    if (instruccionesElse != null && !instruccionesElse.isEmpty()) {
        for (Instruccion instr : instruccionesElse) {
            if (instr != null) {
                resultado += instr.generarast(arbol, nodoIN);
            }
        }
    }
        return resultado;
    
    }
}