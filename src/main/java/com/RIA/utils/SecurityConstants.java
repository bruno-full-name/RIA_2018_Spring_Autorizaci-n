package com.RIA.utils;

public class SecurityConstants {

    public static final String JWT_SECRET = "secret";
    //Tiempo en el que el token emitido deja de ser valido
    public static final long JWT_EXPIRATION_TIME = 30_000;
    //Indica como debe comenzar el header que contiene el token
    public static final String TOKEN_PREFIX = "RIA ";
    public static final String HEADER_STRING = "Authorization";

}
