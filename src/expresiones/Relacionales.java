/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author VictorS
 */
public class Relacionales extends Instruccion{
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }

        return switch (relacional) {
            case EQUALS ->
                this.equals(condIzq, condDer);
            case NOTEQUALS ->
                this.notEquals(condIzq,condDer);
            case MAYOR ->
                this.mayor(condIzq, condDer);
            case MAYORQUE->
                this.mayorIgual(condIzq, condDer);
            case MENOR->
                this.menor(condIzq, condDer);
            case MENORQUE->
                this.menorIgual(condIzq, condDer);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object equals(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();
    
    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    
    return switch (comparando1) {
        case tipoDato.ENTERO -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 == (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 == (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 == (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 == (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 == (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 == (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 == (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 == (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 == (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA -> 
            switch (comparando2) {
                case tipoDato.CADENA -> comp1.toString().equalsIgnoreCase(comp2.toString());
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        case tipoDato.BOOLEANO -> 
            switch (comparando2) {
                case tipoDato.BOOLEANO -> comp1 == comp2;
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar booleano con otro tipo", this.linea, this.col);
            };
        default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
    
    
public Object notEquals(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();
    
    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    
    return switch (comparando1) {
        case tipoDato.ENTERO -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 != (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 != (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 != (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 != (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 != (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 != (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER -> 
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 != (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 != (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 != (int) comp2;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA -> 
            switch (comparando2) {
                case tipoDato.CADENA -> !comp1.toString().equalsIgnoreCase(comp2.toString());
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        case tipoDato.BOOLEANO -> 
            switch (comparando2) {
                case tipoDato.BOOLEANO -> comp1 != comp2;
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar booleano con otro tipo", this.linea, this.col);
            };
        default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
    
    
public Object mayor(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();
    
    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    
    return switch (comparando1) {
        case tipoDato.ENTERO ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 > (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 > (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 > (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> {
                    int valorComp1 = (boolean) comp1 ? 1 : 0;
                    int valorComp2 = (boolean) comp2 ? 1 : 0;
                    yield valorComp1 > valorComp2;
                }
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 > (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 > (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 > (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 > (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 > (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 > (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA ->
            switch (comparando2) {
                case tipoDato.CADENA -> comp1.toString().compareToIgnoreCase(comp2.toString()) > 0;
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        case tipoDato.BOOLEANO ->
            switch (comparando2) {
                case tipoDato.BOOLEANO -> {
                    int valorComp1 = (boolean) comp1 ? 1 : 0;
                    int valorComp2 = (boolean) comp2 ? 1 : 0;
                    yield valorComp1 > valorComp2;
                }
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar booleano con otro tipo", this.linea, this.col);
            };
        default ->
            new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
    
        public Object mayorIgual(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();

    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    return switch (comparando1) {
        case tipoDato.ENTERO ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 >= (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 >= (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 >= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 >= (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 >= (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 >= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 >= (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 >= (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 >= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA ->
            switch (comparando2) {
                case tipoDato.CADENA -> comp1.toString().compareToIgnoreCase(comp2.toString()) >= 0;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
       case tipoDato.BOOLEANO ->
            switch (comparando2) {
                case tipoDato.BOOLEANO -> {
                    int valorComp1 = (boolean) comp1 ? 1 : 0;
                    int valorComp2 = (boolean) comp2 ? 1 : 0;
                    yield valorComp1 >= valorComp2;
                }
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar booleano con otro tipo", this.linea, this.col);
            };
        default ->
            new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
    
    public Object menor(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();

    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    return switch (comparando1) {
        case tipoDato.ENTERO ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 < (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 < (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 < (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 < (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 < (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 < (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 < (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 < (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 < (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA ->
            switch (comparando2) {
                case tipoDato.CADENA -> comp1.toString().compareToIgnoreCase(comp2.toString()) < 0;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        case tipoDato.BOOLEANO ->
            switch (comparando2) {
                case tipoDato.BOOLEANO -> {
                    int valorComp1 = (boolean) comp1 ? 1 : 0;
                    int valorComp2 = (boolean) comp2 ? 1 : 0;
                    yield valorComp1 < valorComp2;
                }
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        default ->
            new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
    
    public Object menorIgual(Object comp1, Object comp2) {
    var comparando1 = this.cond1.tipo.getTipo();
    var comparando2 = this.cond2.tipo.getTipo();

    // Convertir comp1 si es un Character a su valor ASCII (int)
    if (comp1 instanceof Character) {
        comp1 = (int) ((Character) comp1).charValue();
    }
    
    // Convertir comp2 si es un Character a su valor ASCII (int)
    if (comp2 instanceof Character) {
        comp2 = (int) ((Character) comp2).charValue();
    }
    return switch (comparando1) {
        case tipoDato.ENTERO ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 <= (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 <= (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 <= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar entero con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.DECIMAL ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (double) comp1 <= (int) comp2;
                case tipoDato.DECIMAL -> (double) comp1 <= (double) comp2;
                case tipoDato.CARACTER -> (double) comp1 <= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar decimal con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CARACTER ->
            switch (comparando2) {
                case tipoDato.ENTERO -> (int) comp1 <= (int) comp2;
                case tipoDato.DECIMAL -> (int) comp1 <= (double) comp2;
                case tipoDato.CARACTER -> (int) comp1 <= (int) comp2;
                case tipoDato.CADENA -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con cadena", this.linea, this.col);
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar caracter con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
        case tipoDato.CADENA ->
            switch (comparando2) {
                case tipoDato.CADENA -> comp1.toString().compareToIgnoreCase(comp2.toString()) <= 0;
                case tipoDato.BOOLEANO -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con booleano", this.linea, this.col);
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        case tipoDato.BOOLEANO ->
            switch (comparando2) {
                case tipoDato.BOOLEANO -> {
                    int valorComp1 = (boolean) comp1 ? 1 : 0;
                    int valorComp2 = (boolean) comp2 ? 1 : 0;
                    yield valorComp1 <= valorComp2;
                }
                default -> new Errores("SEMANTICO", "Relacional Invalido: No se puede comparar cadena con otro tipo", this.linea, this.col);
            };
        default ->
            new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
    };
}
}