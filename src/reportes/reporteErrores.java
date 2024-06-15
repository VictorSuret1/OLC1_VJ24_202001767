/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import Interfaz.interfaz;
import analisis.parser;
import excepciones.Errores;
import excepciones.tabla;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import Interfaz.interfaz;
import analisis.parser.*;
import static analisis.parser.listaTabla;
/**
 *
 * @author VictorS
 */
public class reporteErrores {
    
     public reporteErrores() {
    }

    public static void crearReporte(List<Errores> errores) {

        
        try {

            FileWriter fileWriter = new FileWriter("Errores.html");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("<html>");
            printWriter.println("<body>");

            printWriter.println("<table border='2'>");
            printWriter.println(
                    "<tr><th> # </th><th>Tipo</th><th>Descripcion</th><th>Fila</th><th>Columna</th>");
            for (int i = 0; i < errores.size(); i++) {
                
                errores.get(i);
                printWriter.println(
                        "<tr><td>" + i + "</td><td>" + errores.get(i).tipo + "</td><td>"
                                + errores.get(i).desc + "</td><td>"
                                + errores.get(i).linea + "</td><td>"
                                + errores.get(i).columna + "</td>");
            }
               errores.clear();

            

            printWriter.println("</table>");

            printWriter.println("</body>");
            printWriter.println("</html>");

            printWriter.close();
            fileWriter.close();

            System.out.println("Tabla de errosres creada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    public static void crearTabla() {
        try {
            FileWriter fileWriter = new FileWriter("Simbolos.html");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            
            printWriter.println("<html>");
            printWriter.println("<body>");


            printWriter.println("<table border='1'>");
            printWriter.println(
                    "<tr><th> # </th><th>Nombre</th><th>Tipo</th><th>Tipo</th><th>Entorno</th><th> Valor </th><th>Fila</th><th>Columna</th>");
            for (int i = 0; i < listaTabla.size(); i++) {
                listaTabla.get(i);
                printWriter.println(
                        "<tr><td>" + i + "</td><td>" 
                                + listaTabla.get(i).Nombre + "</td><td>"
                                + listaTabla.get(i).Tipo + "</td><td>"
                                + listaTabla.get(i).Tipo2 + "</td><td>"
                                + listaTabla.get(i).Entorno + "</td><td>"
                                + listaTabla.get(i).Valor + "</td><td>"
                                + listaTabla.get(i).Fila + "</td><td>"
                                + listaTabla.get(i).Columna + "</td>");
            }
            listaTabla.clear();

            printWriter.println("</table>");

            printWriter.println("</body>");
            printWriter.println("</html>");

            printWriter.close();
            fileWriter.close();

            System.out.println("Tabla de simbolos creada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}