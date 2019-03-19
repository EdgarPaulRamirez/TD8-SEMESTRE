package com.example.a26proyectobdu2_30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public SQLiteDatabase myDB;

    public ConexionSQLiteHelper(Context context) {
        super(context, Utilidades.DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB(){
        myDB=getWritableDatabase();
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    public Cursor getAll(){

        String query = "SELECT * FROM CLIENTE ORDER BY LICENCIA";

        /*String query="SELECT * FROM "+ Utilidades.TABLA;*/

        return myDB.rawQuery(query,null);

    }

    public String insertar(String licencia, String monto, String puntos, String cel, String mail){
        String mensaje = "";

        SQLiteDatabase database = this.getWritableDatabase();

        String query = "SELECT * FROM CLIENTE WHERE LICENCIA ="+licencia;

        Cursor retorno = database.rawQuery(query, null);

        if (retorno.moveToFirst() != false){

            int montoAnt = Integer.parseInt(retorno.getString(2));
            int puntosAnt = Integer.parseInt(retorno.getString(3));

            int sumaMonto = montoAnt + Integer.parseInt(monto);
            int sumaPuntos = puntosAnt + Integer.parseInt(puntos);

            ContentValues con = new ContentValues();

            con.put(Utilidades.MONTO, sumaMonto);
            con.put(Utilidades.PUNTOS, sumaPuntos);

            int cantidad = database.update(Utilidades.TABLA, con, "LICENCIA="+licencia, null);

            if (cantidad!=0){
                mensaje = "El cliente ya existe y se sumaron correctamente tanto el monto como los puntos";
            }else{
                mensaje = "No se pudo Actualizar";
            }

        }else {
            ContentValues contenedor = new ContentValues();

            contenedor.put(Utilidades.LICENCIA, licencia);
            contenedor.put(Utilidades.MONTO, monto);
            contenedor.put(Utilidades.PUNTOS, puntos);
            contenedor.put(Utilidades.CEL, cel);
            contenedor.put(Utilidades.MAIL, mail);

            try {
                database.insertOrThrow(Utilidades.TABLA, null, contenedor);
                mensaje = "Se registro correctamente el cliente con la licencia: " + licencia;

            } catch (SQLException e) {
                mensaje = "No se pudo registrar el cliente";
            }
        }
        database.close();
        return mensaje;
    }

    public String[] buscarCliente(String licencia){
        String[] datos = new String[7];
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + Utilidades.TABLA + " WHERE LICENCIA ='"+licencia+"'";
        Cursor registros = database.rawQuery(query, null);

        if (registros.moveToFirst()){
            for (int i=0; i<6; i++){
                datos[i] = registros.getString(i);
            }
            datos[6] = "Encontrado correctamente el cliente con la licencia: " + licencia;
        }else{
            datos[6] = "No se encontro el cliente con la licencia: "+ licencia;
        }

        database.close();
        return datos;
    }


    public String actualizar(String licencia, String cel, String mail){
        String mensaje = "";

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put(Utilidades.LICENCIA, licencia);
        contenedor.put(Utilidades.CEL, cel);
        contenedor.put(Utilidades.MAIL, mail);

        int cantidad = database.update(Utilidades.TABLA, contenedor, "LICENCIA='"+licencia+"'", null);

        if (cantidad!=0){
            mensaje = "Se Actualizado Correctamente el Cliente con la licencia: " + licencia;
        }else{
            mensaje = "No se pudo Actualizar";
        }

        database.close();
        return mensaje;
    }

    public String eliminar(String licencia){
        String mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();

        int cantidad = database.delete(Utilidades.TABLA, "LICENCIA='"+licencia+"'", null);

        if (cantidad !=0){
            mensaje = "Cliente eliminado correctamente con la licencia: " + licencia;
        }else {
            mensaje = "No existe el cliente con la licencia: "+licencia;
        }

        database.close();
        return mensaje;
    }
}
