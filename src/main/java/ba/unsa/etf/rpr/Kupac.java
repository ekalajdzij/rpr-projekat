package ba.unsa.etf.rpr;

public class Kupac {
    private Integer id;
    private String ime;
    private String mail;
    private String adresa;
    private String telefon;
    private Prodavac prodavac;
    private Karte karta;

    public Kupac(Integer id, String ime, String mail, String adresa, String telefon, Prodavac prodavac, Karte karta) {
        this.id = id;   this.ime = ime; this.adresa= adresa;    this. mail = mail;  this.telefon = telefon; this.prodavac = prodavac;   this.karta = karta;
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
                ", karta = " + karta.getVrsta()+ ", datum: " + karta.getDatum() + "u: " + karta.getAdresa() +
                '}';
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

    public Karte getKarta() {
        return karta;
    }

    public void setKarta(Karte karta) {
        this.karta = karta;
    }
}
