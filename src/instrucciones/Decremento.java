/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.*;
import excepciones.Errores;
import expresiones.*;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class Decremento extends Instruccion {
    private String id;

    public Decremento(String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no existente", this.linea, this.col);
        }

        if (variable.getTipo().getTipo() == tipoDato.ENTERO) {
            int valor = (Integer) variable.getValor();
            variable.setValor(valor - 1);
        } else if (variable.getTipo().getTipo() == tipoDato.DECIMAL) {
            double valor = (Double) variable.getValor();
            variable.setValor(valor - 1.0);
        } else {
            return new Errores("SEMANTICO", "Tipo no soportado para decremento", this.linea, this.col);
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        // DECREMENTO -> ID - - ;
        String nodoPP = "n" + arbol.getContador();
        String nodoP = "n" + arbol.getContador();
        String nodoP1 = "n" + arbol.getContador();
        String nodoExp = "n" + arbol.getContador();
        String nodoP2 = "n" + arbol.getContador();
        String nodoPc = "n" + arbol.getContador();
        

        String resultado = nodoPP + "[label=\"DECREMENTO \"];\n";
        resultado += anterior + " -> " + nodoPP + ";\n";

        
        resultado += nodoExp + "[label=\""+id.toString()+"\"];\n";
        resultado += nodoP1 + "[label=\"-\"];\n";
        resultado += nodoP2 + "[label=\"-\"];\n";
        resultado += nodoPc + "[label=\";\"];\n";
  

        resultado += nodoPP + " -> " + nodoP + ";\n";
        resultado += nodoPP + " -> " + nodoExp + ";\n";
        resultado += nodoPP + " -> " + nodoP1 + ";\n";
        resultado += nodoPP + " -> " + nodoP2 + ";\n";
        resultado += nodoPP + " -> " + nodoPc + ";\n";


        return resultado;
    }
}