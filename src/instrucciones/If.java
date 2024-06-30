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

public class If extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // Ver que cond sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida",
                    this.linea, this.col);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {
            for (var i : this.instrucciones) {
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
                if (resultado instanceof Errores) {
                    arbol.getErrores().add((Errores) resultado);
                }
            }
        }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        //if ( <EXPRESION> ) { <INSTRUCCIONES> }
        
        String nodoPP = "n" + arbol.getContador();
    String nodoFOR = "n" + arbol.getContador();
    String nodop1 = "n" + arbol.getContador();
    String nodoAS = "n" + arbol.getContador();
    String nodoASI = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();
    String nodoCON = "n" + arbol.getContador();
    String nododp2 = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"IF\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoFOR + "[label=\"IF\"];\n";
    resultado += nodop1 + "[label=\"(\"];\n";
    resultado += nodoAS + "[label=\"EXPRESION\"];\n";
    resultado += nododp + "[label=\")\"];\n";
    resultado += nodoCON + "[label=\"{\"];\n";
    resultado += nododp2 + "[label=\"INSTRUCCIONES\"];\n";
    resultado += nodoASI + "[label=\"}\"];\n";
    

    resultado += nodoPP + " -> " + nodoFOR + ";\n";
    resultado += nodoPP + " -> " + nodop1 + ";\n";
    resultado += nodoPP + " -> " + nodoAS + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    resultado += nodoPP + " -> " + nodoCON + ";\n";
    resultado += nodoPP + " -> " + nododp2 + ";\n";
    resultado += nodoPP + " -> " + nodoASI + ";\n";

    resultado += this.condicion.generarast(arbol, nodoAS);
    // Añadir las instrucciones dentro del for
    if (instrucciones != null && !instrucciones.isEmpty()) {
        for (Instruccion instr : instrucciones) {
            if (instr != null) {
                resultado += instr.generarast(arbol, nododp2);
            }
        }
    }
    
    


    return resultado;
    
    }
}