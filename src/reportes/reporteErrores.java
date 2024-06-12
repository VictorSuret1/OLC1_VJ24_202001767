/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import Interfaz.interfaz;
import analisis.parser;
import excepciones.Errores;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import Interfaz.interfaz;


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

            System.out.println("Tabla creada exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}