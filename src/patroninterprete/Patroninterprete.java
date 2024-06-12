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
        // TODO code application logic here
        
                
        try {
            String texto = ""
                    + "imprimir(2==\"true\");  imprimir(%2+2==3.0+1.0);$ imprimir(\"hola\"==\"HoLa\");"
                    + "";
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            LinkedList<Errores> lista = new LinkedList<>();
            lista.addAll (s.listaErrores);
            lista.addAll(p.listaErrores);
            for (var a : ast.getInstrucciones()) {
                if (a == null) {
                    continue;
                }

                var res = a.interpretar(ast, tabla);
                if (res instanceof Errores) {
                    lista.add((Errores) res);
                }
            }
            System.out.println(ast.getConsola());

            for (var i : lista) {
                System.out.println(i);
            }
            
                
        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
        
        
        
        
        
    }
}
