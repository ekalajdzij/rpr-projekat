package ba.unsa.etf.rpr;

public class Prodavac {
    Integer id;
    String ime;
    String telefon;
    String mail;

    public Prodavac(Integer id, String ime, String telefon, String mail) {
        this.id = id;   this.ime = ime; this.telefon = telefon; this.mail = mail;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

