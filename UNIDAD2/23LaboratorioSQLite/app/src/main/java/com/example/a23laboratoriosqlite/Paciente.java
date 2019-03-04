package com.example.a23laboratoriosqlite;

public class Paciente {

    private int idPaciente;
    private String nombre;
    private String rfc;
    private String cel;
    private String mail;
    private String fecha;

    public Paciente(int idPaciente, String nombre, String rfc, String cel, String mail, String fecha) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.rfc = rfc;
        this.cel = cel;
        this.mail = mail;
        this.fecha = fecha;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

