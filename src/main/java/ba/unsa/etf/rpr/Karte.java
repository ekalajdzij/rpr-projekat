package ba.unsa.etf.rpr;

public class Karte {
    private Integer id;
    private String vrsta;
    private String datum;
    private String adresa;
    private Prodavac prodavac;
    private Double cijena;

    public Karte(Integer id, String vrsta, String datum, String adresa, Prodavac prodavac_id, Double cijena ) {
        this.id = id;   this.vrsta = vrsta; this.datum = datum; this.adresa = adresa;
        this.prodavac = prodavac_id;
        this.cijena = cijena;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac_id(Prodavac prodavac_id) {
        this.prodavac = prodavac_id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Karte{" +
                "id = " + id +
                ", vrsta = " + vrsta +
                ", datum = " + datum +
                ", adresa = " + adresa  +
                ", prodavac = " + prodavac.getIme() +
                ", cijena = " + cijena +
                '}';
    }
}
