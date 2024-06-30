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
public class DeclaracionLista extends Instruccion {
    private String id;
    private Tipo tipo;

    public DeclaracionLista(String id, Tipo tipo, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.tipo = tipo;
        
        System.out.println("Declaracion Lista created: " + id + " "   + tipo + " " + linea + " " + col);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<Object> lista = new LinkedList<>();
        Simbolo simbolo = new Simbolo(tipo, id, lista);

        if (!tabla.setVariable(simbolo)) {
            arbol.getErrores().add(new Errores("Semántico", "Variable ya declarada: " + id, linea, col));
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        // List< <TIPO> > <ID> = new List();

        String nodoPP = "n" + arbol.getContador();
    String nodot = "n" + arbol.getContador();
    String nodot1 = "n" + arbol.getContador();
    String nodottipo = "n" + arbol.getContador();
    String nodot2 = "n" + arbol.getContador();
    String nodoid = "n" + arbol.getContador();
    String nododos = "n" + arbol.getContador();
    String nodonew = "n" + arbol.getContador();
    String nodoli = "n" + arbol.getContador();
    String nodop1 = "n" + arbol.getContador();
    String nodop2 = "n" + arbol.getContador();
    String nododp = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"DECLARACION LISTA\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoPP + " -> " + nodot + ";\n";
    resultado += nodoPP + " -> " + nodot1 + ";\n";
    resultado += nodoPP + " -> " + nodottipo + ";\n";
    resultado += nodoPP + " -> " + nodot2 + ";\n";
    resultado += nodoPP + " -> " + nodoid + ";\n";
    resultado += nodoPP + " -> " + nododos + ";\n";
    resultado += nodoPP + " -> " + nodonew + ";\n";
    resultado += nodoPP + " -> " + nodoli + ";\n";
    resultado += nodoPP + " -> " + nodop1 + ";\n";
    resultado += nodoPP + " -> " + nodop2 + ";\n";
    resultado += nodoPP + " -> " + nododp + ";\n";
    
        resultado += nodot + "[label=\" LIST \"];\n";
        resultado += nodot1 + "[label=\" < \"];\n";
        resultado += nodottipo + "[label=\" " + tipo.getTipo().toString()+" \"];\n";
        resultado += nodot2 + "[label=\" > \"];\n";
        resultado += nodoid + "[label=\" "+ id.toString() +" \"];\n";
        resultado += nododos + "[label=\" + \"];\n";
        resultado += nodonew + "[label=\" NEW \"];\n";
        resultado += nodoli + "[label=\" LIST \"];\n";
        resultado += nodop1 + "[label=\" ( \"];\n";
        resultado += nodop2 + "[label=\" ) \"];\n";
        resultado += nododp + "[label=\" ; \"];\n";
        
        return resultado;
    }
}