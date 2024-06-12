/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;
import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author fabian
 */
/*
- E
E + E
E - E
 */
public class Aritmeticas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    //negacion 
    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    //cualquier operacion menos negacion
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);
            case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }

    public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CADENA -> {
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }

    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case tipoDato.ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case tipoDato.DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.col);
            }
        }
    }
    
    public Object resta(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CADENA -> {
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }

    
    public Object multiplicacion(Object op1, Object op2){
        var type1 = this.operando1.tipo.getTipo();
        var type2 = this.operando2.tipo.getTipo();
        switch (type1){
            case tipoDato.ENTERO -> { 
                switch (type2){
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 * (int) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return (int) op1 * (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "LA MULTIPLICACION ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (type2){
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (int) op2; 
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return (double) op1 * (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "LA MULTIPLICACION ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "LA SUMA ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
            }
        }
    }

    
public Object division(Object op1, Object op2){
    var type1 = this.operando1.tipo.getTipo();
    var type2 = this.operando2.tipo.getTipo();
    switch (type1){
        case tipoDato.ENTERO -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    if ((int) op2 == 0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) ((int) op1) / (int) op2;
                }
                case tipoDato.DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) ((int) op1) / (double) op2;
                }
                case tipoDato.CARACTER -> {
                    if ((int) op2 == 0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) ((int) op1) / (int) op2;
                }
                default -> {
                    return new Errores("SEMANTICO", "LA DIVISION ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                }
            }
        }
        case tipoDato.DECIMAL -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    if ((int) op2 == 0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / (int) op2; 
                }
                case tipoDato.DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / (double) op2;
                }
                case tipoDato.CARACTER -> {
                    if ((int) op2 == 0) {
                        System.out.println("error division 0");
                        return new Errores("SEMANTICO", "DIVISION POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / (int) op2;
                }
                default -> {
                    return new Errores("SEMANTICO", "LA DIVISION ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                }
            }
        }
        default -> {
            return new Errores("SEMANTICO", "LA DIVISION ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
        }
    }
}


public Object modulo(Object op1, Object op2){
    var type1 = this.operando1.tipo.getTipo();
    var type2 = this.operando2.tipo.getTipo();
    switch (type1){
        case tipoDato.ENTERO -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    if ((int) op2 == 0) {
                        System.out.println("error modulo 0");
                        return new Errores("SEMANTICO", "MODULO POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 % (int) op2;
                }
                case tipoDato.DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        System.out.println("error modulo 0");
                        return new Errores("SEMANTICO", "MODULO POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (int) op1 % (double) op2;
                }
                default -> {
                    return new Errores("SEMANTICO", "EL MODULO ES ERRONEO O ESTA MAL ESCRITO", this.linea, this.col);
                }
            }
        }
        case tipoDato.DECIMAL -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    if ((int) op2 == 0) {
                        System.out.println("error modulo 0");
                        return new Errores("SEMANTICO", "MODULO POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 % (int) op2; 
                }
                case tipoDato.DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        System.out.println("error modulo 0");
                        return new Errores("SEMANTICO", "MODULO POR CERO", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 % (double) op2;
                }
                default -> {
                    return new Errores("SEMANTICO", "EL MODULO ES ERRONEO O ESTA MAL ESCRITO", this.linea, this.col);
                }
            }
        }
        default -> {
            return new Errores("SEMANTICO", "EL MODULO ES ERRONEO O ESTA MAL ESCRITO", this.linea, this.col);
        }
    }
}


 public Object potencia(Object op1, Object op2){
    var type1 = this.operando1.tipo.getTipo();
    var type2 = this.operando2.tipo.getTipo();
    switch (type1){
        case tipoDato.ENTERO -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return Math.pow((int) op1, (int) op2);
                }
                case tipoDato.DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((int) op1, (double) op2);
                }
                default -> {
                    return new Errores("SEMANTICO", "LA POTENCIA ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                }
            }
        }
        case tipoDato.DECIMAL -> {
            switch (type2){
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((double) op1, (int) op2); 
                }
                case tipoDato.DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((double) op1, (double) op2);
                }
                default -> {
                    return new Errores("SEMANTICO", "LA POTENCIA ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
                }
            }
        }
        default -> {
            return new Errores("SEMANTICO", "LA POTENCIA ES ERRONEA O ESTA MAL ESCRITA", this.linea, this.col);
        }
    }
}
 
}






