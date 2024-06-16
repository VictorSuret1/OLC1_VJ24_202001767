/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

/**
 *
 * @author VictorS
 */
public enum OperadoresAritmeticos {
    SUMA,
    RESTA,
    MULTIPLICACION,
    DIVISION,
    MODULO,
    NEGACION,
    POTENCIA;

    public static OperadoresAritmeticos getSUMA() {
        return SUMA;
    }

    public static OperadoresAritmeticos getRESTA() {
        return RESTA;
    }

    public static OperadoresAritmeticos getMULTIPLICACION() {
        return MULTIPLICACION;
    }

    public static OperadoresAritmeticos getDIVISION() {
        return DIVISION;
    }

    public static OperadoresAritmeticos getMODULO() {
        return MODULO;
    }

    public static OperadoresAritmeticos getNEGACION() {
        return NEGACION;
    }

    public static OperadoresAritmeticos getPOTENCIA() {
        return POTENCIA;
    }
}
