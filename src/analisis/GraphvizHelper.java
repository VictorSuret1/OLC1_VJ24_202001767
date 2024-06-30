/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;
import java.io.*;
/**
 *
 * @author VictorS
 */
public class GraphvizHelper {
    public static void generatePngFromDot(String dotPath, String outputPath) throws IOException, InterruptedException {
        String[] command = {"dot", "-Tpng", dotPath, "-o", outputPath};
        Process process = new ProcessBuilder(command).start();
        process.waitFor();
    }
}