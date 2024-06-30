/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class Return extends Instruccion {
    private Object valor;
    private Tipo tipo;

    public Return(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = valor;
        this.tipo = tipo;
    }

    public Return(int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.valor = null;
        this.tipo = new Tipo(tipoDato.VOID); // Asegurarse de que el tipo sea VOID
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this;
    }

    public Object getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
     //if ( <EXPRESION> ) { <INSTRUCCIONES> } else { instrucciones }
        
        String nodoPP = "n" + arbol.getContador();
    String nodoFOR = "n" + arbol.getContador();
    String nodop1 = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();
    

    String resultado = nodoPP + "[label=\"RETURN\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoFOR + "[label=\"RETURN \"];\n";
    resultado += nodop1 + "[label=\"EXPRESION\"];\n";
    resultado += nododp + "[label=\";\"];\n";
   

    resultado += nodoPP + " -> " + nodoFOR + ";\n";
    resultado += nodoPP + " -> " + nodop1 + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";

    
        return resultado;
    
    }
    
}