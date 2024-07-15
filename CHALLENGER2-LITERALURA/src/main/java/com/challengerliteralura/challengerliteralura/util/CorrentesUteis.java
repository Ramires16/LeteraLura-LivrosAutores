package com.challengerliteralura.challengerliteralura.util;


public class CorrentesUteis {

    public static String limitarLongitud(String corrente, int longitudeMaxima) {
        if (corrente.length() <= longitudeMaxima) {
            return corrente;
        } else {
            return corrente.substring(0, longitudeMaxima);
        }
    }
}