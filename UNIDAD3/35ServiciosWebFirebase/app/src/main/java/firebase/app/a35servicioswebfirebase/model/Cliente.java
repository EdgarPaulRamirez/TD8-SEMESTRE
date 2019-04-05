package firebase.app.a35servicioswebfirebase.model;

public class Cliente {

    private String licencia;
    private String monto;
    private String puntos;
    private String cel;
    private String mail;

    public Cliente() {

    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
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

    @Override
    public String toString() {
        return "Cliente{" +
                "licencia='" + licencia + '\'' +
                ", cel='" + cel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
