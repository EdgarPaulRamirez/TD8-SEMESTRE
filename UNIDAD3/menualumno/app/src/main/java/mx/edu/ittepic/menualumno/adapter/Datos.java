package mx.edu.ittepic.menualumno.adapter;


public class Datos {
    String name="nombre: ";
    String Domicilio="Domicilio: ";
    int ID;

    public Datos(String name, String Domicilio,int ID) {
        this.name += name;
        this.Domicilio += Domicilio;
        this.ID = ID;
    }
}