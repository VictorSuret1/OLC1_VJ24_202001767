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
public class AsignacionLista extends Instruccion {
    private String id;
    private Instruccion indice;
    private Instruccion valor;

    public AsignacionLista(String id, Instruccion indice, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indice = indice;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);

        if (simbolo == null) {
            arbol.getErrores().add(new Errores("Semántico", "Variable no declarada: " + id, linea, col));
            return null;
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            arbol.getErrores().add(new Errores("Semántico", "La variable no es una lista: " + id, linea, col));
            return null;
        }

        LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();
        Object valorIndice = indice.interpretar(arbol, tabla);

        if (!(valorIndice instanceof Integer)) {
            arbol.getErrores().add(new Errores("Semántico", "El índice debe ser entero: " + id, linea, col));
            return null;
        }

        int i = (int) valorIndice;

        if (i < 0 || i >= lista.size()) {
            arbol.getErrores().add(new Errores("Semántico", "Índice fuera de rango: " + id, linea, col));
            return null;
        }

        Object valorInterpretado = valor.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        lista.set(i, valorInterpretado);
        simbolo.setValor(lista);

        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instruccion getIndice() {
        return indice;
    }

    public void setIndice(Instruccion indice) {
        this.indice = indice;
    }

    public Instruccion getValor() {
        return valor;
    }

    public void setValor(Instruccion valor) {
        this.valor = valor;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
}