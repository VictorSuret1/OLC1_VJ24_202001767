/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class AccesoVector2D extends Instruccion {
    private String id;
    private Instruccion indice1;
    private Instruccion indice2;

    public AccesoVector2D(String id, Instruccion indice1, Instruccion indice2, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indice1 = indice1;
        this.indice2 = indice2;
    }

    public String getId() {
        return id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            arbol.getErrores().add(new Errores("Semántico", "Variable no declarada: " + id, linea, col));
            return null;
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            arbol.getErrores().add(new Errores("Semántico", "La variable no es un vector: " + id, linea, col));
            return null;
        }

        LinkedList<LinkedList<Object>> vector2D = (LinkedList<LinkedList<Object>>) simbolo.getValor();
        Object valorIndice1 = indice1.interpretar(arbol, tabla);
        Object valorIndice2 = indice2.interpretar(arbol, tabla);

        if (!(valorIndice1 instanceof Integer) || !(valorIndice2 instanceof Integer)) {
            arbol.getErrores().add(new Errores("Semántico", "Los índices deben ser enteros: " + id, linea, col));
            return null;
        }

        int i = (int) valorIndice1;
        int j = (int) valorIndice2;

        if (i < 0 || i >= vector2D.size() || j < 0 || j >= vector2D.get(i).size()) {
            arbol.getErrores().add(new Errores("Semántico", "Índice fuera de rango: " + id, linea, col));
            return null;
        }

        return vector2D.get(i).get(j);
    }
}
