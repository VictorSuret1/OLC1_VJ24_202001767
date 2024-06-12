/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroninterprete;
import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;
/**
 *
 * @author VictorS
 */
public class Patroninterprete {
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            String texto = "PRINTLN(5/7);PRINTLN(11 <= 10);PRINTLN(10 < 11);PRINTLN(10 <= 10);";
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for (var a : ast.getInstrucciones()) {
                var res = a.interpretar(ast, tabla);
            }
            System.out.println(ast.getConsola());
        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }
}
