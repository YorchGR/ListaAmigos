package com.jorgelopezendrina.listaamigos.util;

/**
 * Clase que valída datos.
 *
 * @author Jorge López Endrina.
 * */
public class ValidaDatos {

    /**
     * Método estático que valída el formato de la fecha.
     * */
    public static boolean validaFecha(String cad) {
        return (cad.length() == 10 && cad.charAt(2) =='/' && cad.charAt(5) =='/');
    }
}
