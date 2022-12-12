package ba.unsa.etf.rpr;

public class Placanje {
    private Integer id;
    private Double iznos;
    private String datum;

    public Placanje(Integer id, Double iznos, String datum) {
        this.id = id;   this.iznos = iznos; this.datum = datum;
    }

    @Override
    public String toString() {
        return "Placanje{" +
                "id = " + id +
                ", iznos = " + iznos +
                ", datum = " + datum +
                '}';
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
