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
}