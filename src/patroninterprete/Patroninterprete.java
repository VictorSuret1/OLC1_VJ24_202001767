/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroninterprete;
import Interfaz.interfaz;
import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;
import reportes.reporteErrores;
/**
 *
 * @author VictorS
 */
public class Patroninterprete {
    public static void main(String[] args) {
            interfaz interfaz = new interfaz();
            interfaz.setVisible(true);
    }
}