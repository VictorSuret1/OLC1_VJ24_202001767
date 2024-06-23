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
        switch (this.tipo.getTipo()) {
            case ENTERO:
                valorInterpretado = 0;
                break;
            case DECIMAL:
                valorInterpretado = 0.0;
                break;
            case BOOLEANO:
                valorInterpretado = false;
                break;
            case CARACTER:
                valorInterpretado = '\u0000'; // valor por defecto de char
                break;
            case CADENA:
                valorInterpretado = "";
                break;
            default:
                return new Errores("SEMANTICO", "Tipo desconocido", this.linea, this.col);
        }
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
}
