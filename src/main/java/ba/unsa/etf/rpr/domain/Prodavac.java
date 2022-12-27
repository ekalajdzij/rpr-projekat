package ba.unsa.etf.rpr.domain;

public class Prodavac implements Idable {
    private int id;
    private String ime;
    private String telefon;
    private String mail;

    public Prodavac(int id, String ime, String telefon, String mail) {
        this.id = id;   this.ime = ime; this.telefon = telefon; this.mail = mail;
    }
    public Prodavac() {}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prodavac {" +
                "id = " + id +
                ", ime = " + ime +
                ", telefon = " + telefon +
                ", mail = " + mail +
                '}';
    }

}

