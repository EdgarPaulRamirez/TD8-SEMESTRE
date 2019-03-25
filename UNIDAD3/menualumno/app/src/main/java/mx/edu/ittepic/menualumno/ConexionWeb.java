package mx.edu.ittepic.menualumno;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ConexionWeb extends AsyncTask<URL, String,String> {
    List<String[]> variables;
    AsyncResponse delegado;
    String RequestMethod;
    public ConexionWeb(AsyncResponse pp,String RequestMethod){
        delegado = pp;
        variables = new ArrayList<>();
        this.RequestMethod=RequestMethod;
    }

    public void onPreExecute(){}

    public void agregarVariable(String nombreVariable, String contenidoVariable){
        String[] temporal = {nombreVariable,contenidoVariable};
        variables.add(temporal);
    }

    private JSONObject generarCadenaPost(){
        JSONObject jsonn = new JSONObject();
        try {
            for (int i =0; i<variables.size(); i++){
                String[] temporal = variables.get(i);
                jsonn.put(""+temporal[0], temporal[1]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonn;
    }

    protected void onProgressUpdate(String...s){
    }

    @Override
    protected String doInBackground(URL... urls) {
        //crear el objeto json para enviar por POST
        JSONObject jsonn = generarCadenaPost();
        String respuesta="";
        HttpURLConnection urlConnection = null;
        try{
            urlConnection = (HttpURLConnection) urls[0].openConnection();

            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod(RequestMethod);// se puede cambiar por delete ,put ,etc
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setDoInput(true);
            if(RequestMethod!="GET"){
                urlConnection.setDoOutput(true);

                //OBTENER EL RESULTADO DEL REQUEST
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonn.toString());
                writer.flush();
                writer.close();
                os.close();
            }

            if(urlConnection.getResponseCode() == 200){
                InputStreamReader entrada = new InputStreamReader(urlConnection.getInputStream(),"UTF-8");
                BufferedReader flujoEntrada = new BufferedReader(entrada);
                String temp ="";
                do{
                    temp = flujoEntrada.readLine();
                    if(temp!=null){
                        respuesta+=temp;
                    }
                }while(temp!=null);
                flujoEntrada.close();
            }
            else{
                return "error_400";
            }
        }catch(UnknownHostException e){
            //Esta excepcion se disparara cuando escribiste mal la direccion web
            //Cuando el servidor web se caiga
            respuesta = "error_404";//se ejecuto una excepcion a nivel de host

        }catch (IOException e){
            //se dispara cuando no se puede enviar o recibir datos
            respuesta = "error_405";//se ejecuto una excepcion a nivel de Entrada/Salida

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //Se ejecuta cuando salieron bien las lineas del try
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return respuesta;
    }

    public void onPostExecute(String r){
        delegado.procesarRespuesta(r);
    }
}
