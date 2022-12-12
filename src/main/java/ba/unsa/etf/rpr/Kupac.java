package ba.unsa.etf.rpr;

public class Kupac {
    private Integer id;
    private String ime;
    private String mail;
    private String adresa;
    private String telefon;
    private Prodavac prodavac;
    private Placanje placanje;

    public Kupac(Integer id, String ime, String mail, String adresa, String telefon, Prodavac prodavac, Placanje placanje) {
        this.id = id;   this.ime = ime; this.adresa= adresa;    this. mail = mail;  this.telefon = telefon; this.prodavac = prodavac;   this.placanje = placanje;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "id = " + id +
                ", ime = " + ime +
                ", mail = " + mail +
                ", adresa = "+ adresa +
                ", telefon = " + telefon +
                ", prodavac = " + prodavac.getIme() +
                ", placeno = " + placanje.getIznos() +
                '}';
    }

    public Placanje getPlacanje() {
        return placanje;
    }

    public void setPlacanje(Placanje placanje) {
        this.placanje = placanje;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
