package com.example.a25laboratoriobd1;

public class Utilidades {

    public static String TABLA = "CLIENTE";
    public static String LICENCIA = "LICENCIA";
    public static String MONTO = "MONTO";
    public static String PUNTOS = "PUNTOS";
    public static String CEL="CEL";
    public static String MAIL="MAIL";
    public static String ID="_id";

    public static final String DBNAME="BASEDEDATOS";


    public static String CREAR_TABLA="CREATE TABLE "+TABLA+"("+
            ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            LICENCIA+" INTEGER NOT NULL, "+
            MONTO+" TEXT, "+
            PUNTOS+" TEXT, " +
            CEL+" TEXT, " +
            MAIL+" TEXT)";
}
