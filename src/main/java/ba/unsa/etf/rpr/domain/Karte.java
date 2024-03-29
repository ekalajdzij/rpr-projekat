package ba.unsa.etf.rpr.domain;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Karte implements Idable{
    private int id;
    private String vrsta;
    private String datum;
    private String adresa;
    private Prodavac prodavac;
    private Double cijena;
    private Integer kolicina;

    public Karte(int id, String vrsta, String datum, String adresa, Prodavac prodavac_id, Double cijena,Integer kolicina) {
        this.id = id;   this.vrsta = vrsta; this.datum = datum; this.adresa = adresa; this.kolicina = kolicina;
        this.prodavac = prodavac_id;
        this.cijena = cijena;
    }
    public Karte() {
    }
    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getKolicina() {
        return this.kolicina;
    }

    @Override
    public String toString() {
        return vrsta + " Kolicina: "+kolicina;
    }

}
