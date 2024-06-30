/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;
import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.AccesoVector;
import expresiones.Nativo;
import simbolo.*;

/**
 *
 * @author VictorS
 */
public class AsignacionVar extends Instruccion {

    private String id;
    private Instruccion exp;

    public AsignacionVar(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Verificar si la variable existe en la tabla de símbolos
        Simbolo variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no existente", this.linea, this.col);
        }

        // Verificar si se intenta modificar una constante
        if (variable.getMutabilidad().equals("CONST")) {
            return new Errores("SEMANTICO", "No se puede modificar una variable constante", this.linea, this.col);
        }

        // Interpretar el nuevo valor a asignar
        Object newValor = this.exp.interpretar(arbol, tabla);
        if (newValor instanceof Errores) {
            return newValor;
        }

        // Validar tipos
        if (variable.getTipo().getTipo() != this.exp.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipos incorrectos en asignación", this.linea, this.col);
        }

        // Asignar el nuevo valor a la variable
        variable.setValor(newValor);
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instruccion getValor() {
        return exp;
    }

    public void setValor(Instruccion valor) {
        this.exp = valor;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }
    
}