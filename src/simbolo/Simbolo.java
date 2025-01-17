/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

/**
 *
 * @author VictorS
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    private String mutabilidad;
    private String entorno;
    private int linea;
    private int columna;
    
    
    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }
    
    public Simbolo(Tipo tipo, String id, Object valor, String entorno, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.entorno = entorno;
        this.linea = linea;
        this.columna = columna;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setMutabilidad(String mutabilidad) {
        this.mutabilidad = mutabilidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Object getValor() {
        return valor;
    }

    public String getMutabilidad() {
        return mutabilidad;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }
}