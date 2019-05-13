package mx.edu.ittepic.listadesensores_51;

public class Datos {
    String Nombre;
    String Fabricante;
    int Version;
    float Rango;
    int Delay;
    float Poder;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public float getRango() {
        return Rango;
    }

    public void setRango(float rango) {
        Rango = rango;
    }

    public int getDelay() {
        return Delay;
    }

    public void setDelay(int delay) {
        Delay = delay;
    }

    public float getPoder() {
        return Poder;
    }

    public void setPoder(float poder) {
        Poder = poder;
    }

    public Datos(String nombre, String fabricante, int version, float rango, int delay, float poder) {

        Nombre = nombre;
        Fabricante = fabricante;
        Version = version;
        Rango = rango;
        Delay = delay;
        Poder = poder;
    }
}
