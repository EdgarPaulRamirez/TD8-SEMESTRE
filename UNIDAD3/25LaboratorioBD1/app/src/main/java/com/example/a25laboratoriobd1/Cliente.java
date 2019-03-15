package com.example.a25laboratoriobd1;

public class Cliente {
    private int licencia;
    private String monto;
    private String puntos;
    private String cel;
    private String mail;

    public Cliente(int licencia, String monto, String puntos, String cel, String mail) {
        this.licencia = licencia;
        this.monto = monto;
        this.puntos = puntos;
        this.cel = cel;
        this.mail = mail;
    }

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(int licencia) {
        this.licencia = licencia;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
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
}
