/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;
import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.AccesoVector;
import expresiones.Nativo;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;
/**
 *
 * @author VictorS
 */
public class Declaracion extends Instruccion {
    public String identificador;
    public Instruccion valor;
    public String mutabilidad;
    

    // Constructor para la declaración sin valor inicial
    public Declaracion(String mutabilidad, String identificador, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = null;
    }

    // Constructor para la declaración con valor inicial
    public Declaracion(String mutabilidad, String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
        this.mutabilidad = mutabilidad;
    }
    
    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }
    
    

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = null;

        // Interpretar la expresión si hay un valor inicial
        if (this.valor != null) {
            valorInterpretado = this.valor.interpretar(arbol, tabla);

            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }

        } else {
            // Asignar el valor por defecto basado en el tipo de dato
            valorInterpretado = valoresDefault();
        }

        // Si el valor interpretado es de tipo Nativo, extraer el valor real
        if (valorInterpretado instanceof Nativo) {
            valorInterpretado = ((Nativo) valorInterpretado).getValor();
        }

        // Crear el símbolo con el valor interpretado o el valor por defecto
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        s.setMutabilidad(this.mutabilidad);

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        return null;
    }

    public Object valoresDefault() {
        return switch (this.tipo.getTipo()) {
            case tipoDato.BOOLEANO -> false;
            case tipoDato.CADENA -> "";
            case tipoDato.CARACTER -> '\u0000';
            case tipoDato.ENTERO -> 0;
            case tipoDato.DECIMAL -> 0.0;
            default -> null;
        };
        
        
    }
    private int identificarConstructor() {
    if (this.mutabilidad != null && this.valor == null) {
        return 1; // Primer constructor
    } else if (this.mutabilidad != null && this.valor != null) {
        return 2; // Segundo constructor
    } else if (this.mutabilidad == null && this.valor != null) {
        return 3; // Tercer constructor
    }
    return -1; // Caso no identificado
}
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        
        String nodoPP = "n" + arbol.getContador();
    String nodot = "n" + arbol.getContador();
    String nodoid = "n" + arbol.getContador();
    String nododos = "n" + arbol.getContador();
    String nodoTI = "n" + arbol.getContador();
    String nodoPC = "n" + arbol.getContador();
    String nodoMM = "n" + arbol.getContador();
    String nodoID = "n" + arbol.getContador();
    String nodoTIPO = "n" + arbol.getContador();

    String resultado = nodoPP + "[label=\"DECLARACION VARIABLE\"];\n";
    resultado += anterior + " -> " + nodoPP + ";\n";

    resultado += nodoPP + " -> " + nodoMM + ";\n";
    resultado += nodoPP + " -> " + nodoID + ";\n";
    resultado += nodoPP + " -> " + nododos + ";\n";
    resultado += nodoPP + " -> " + nodoTIPO + ";\n";
    resultado += nodoPP + " -> " + nodoPC + ";\n";

    
    
    int constructor = identificarConstructor();

    if (constructor == 1) {
        resultado += nodoMM+ "[label=\"MUTABILIDAD\"];\n";
        resultado += nodot + "[label=\"" + this.mutabilidad + "\"];\n";
        resultado += nodoID + "[label=\"ID\"];\n";
        resultado += nodoid + "[label=\"" + this.identificador + "\"];\n";
        resultado += nododos + "[label=\":\"];\n";
        resultado += nodoTIPO + "[label=\"TIPO\"];\n";
        resultado += nodoTI + "[label=\"" + this.tipo.getTipo() + "\"];\n";
        resultado += nodoPC + "[label=\";\"];\n";
        
        resultado += nodoMM + " -> " + nodot + ";\n";
    resultado += nodoID + " -> " + nodoid + ";\n";
    resultado += nodoTIPO + " -> " + nodoTI + ";\n";
    
    
    } else if (constructor == 2) {
        String nodoP = "n" + arbol.getContador();
        String nodoExp = "n" + arbol.getContador();
        
        resultado += nodoMM+ "[label=\"MUTABILIDAD\"];\n";
        resultado += nodot + "[label=\"" + this.mutabilidad + "\"];\n";
        resultado += nodoID + "[label=\"ID\"];\n";
        resultado += nodoid + "[label=\"" + this.identificador + "\"];\n";
        resultado += nododos + "[label=\":\"];\n";
        resultado += nodoTIPO + "[label=\"TIPO\"];\n";
        resultado += nodoTI + "[label=\"" + this.tipo.getTipo() + "\"];\n";
        resultado += nodoP + "[label=\"=\"];\n";
        resultado += nodoExp + "[label=\"EXPRESION\"];\n";
        resultado += nodoPC + "[label=\";\"];\n";
        
        resultado += nodoMM + " -> " + nodot + ";\n";
        resultado += nodoID + " -> " + nodoid + ";\n";
        resultado += nodoTIPO + " -> " + nodoTI + ";\n";
    
        resultado += nodoPP + " -> " + nodoP + ";\n";
        resultado += nodoPP + " -> " + nodoExp + ";\n";
        resultado += this.valor.generarast(arbol, nodoExp);
    } else if (constructor == 3) {
        String nodoP = "n" + arbol.getContador();
        String nodoExp = "n" + arbol.getContador();

        resultado += nodoid + "[label=\"" + this.identificador + "\"];\n";
        resultado += nodoTI + "[label=\"" + this.tipo.getTipo() + "\"];\n";
        resultado += nodoP + "[label=\"=\"];\n";
        resultado += nodoExp + "[label=\"EXPRESION\"];\n";
        resultado += nodoPC + "[label=\";\"];\n";

        resultado += nodoPP + " -> " + nodoP + ";\n";
        resultado += nodoPP + " -> " + nodoExp + ";\n";
        resultado += this.valor.generarast(arbol, nodoExp);
    }
        return resultado;
    }
}