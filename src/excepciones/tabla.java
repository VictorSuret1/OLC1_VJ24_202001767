/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author VictorS
 */
public class tabla {
    public String Nombre;
    public String Tipo;   // Tipo de la variable
    public String Tipo2;  // Tipo de la expresión o null si no hay asignación
    public String Entorno;
    public Object Valor;  // Valor de la variable si se asigna
    public int Fila;
    public int Columna;

    public tabla(String Nombre, String Tipo, String Tipo2, String Entorno, Object Valor, int Linea, int Columna) {
        this.Nombre = Nombre;
        this.Tipo = Tipo;   // Tipo de la variable
        this.Tipo2 = Tipo2; // Tipo de la expresión o null si no hay asignación
        this.Entorno = Entorno;
        this.Valor = Valor; // Valor de la variable si se asigna
        this.Fila = Linea;
        this.Columna = Columna;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipo2() {
        return Tipo2;
    }

    public void setTipo2(String Tipo2) {
        this.Tipo2 = Tipo2;
    }

    public String getEntorno() {
        return Entorno;
    }

    public void setEntorno(String Entorno) {
        this.Entorno = Entorno;
    }

    public Object getValor() {
        return Valor;
    }

    public void setValor(Object Valor) {
        this.Valor = Valor;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int Fila) {
        this.Fila = Fila;
    }

    public int getColumna() {
        return Columna;
    }

    public void setColumna(int Columna) {
        this.Columna = Columna;
    }
}