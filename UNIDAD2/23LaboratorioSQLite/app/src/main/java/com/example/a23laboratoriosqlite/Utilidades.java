package com.example.a23laboratoriosqlite;

public class Utilidades {

    public static String TABLA = "PACIENTE";
    public static String IDPACIENTE = "IDPACIENTE";
    public static String NOMBRE = "NOMBRE";
    public static String RFC = "RFC";
    public static String CEL="CEL";
    public static String EMAIL="EMAIL";
    public static String FECHA="FECHA";
    public static String ID="_id";

    public static final String DBNAME="BASEDEDATOS";


    public static String CREAR_TABLA="CREATE TABLE "+TABLA+"("+
            ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            IDPACIENTE+" TEXT NOT NULL, "+
            NOMBRE+" TEXT, "+
            RFC+" TEXT, " +
            CEL+" TEXT, " +
            EMAIL+" TEXT, " +
            FECHA+" TEXT)";

}
