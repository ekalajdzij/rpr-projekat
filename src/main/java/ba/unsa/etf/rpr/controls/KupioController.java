package ba.unsa.etf.rpr.controls;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KupioController {
    public Label fieldCijena = new Label();
    public Button zatvoriButtonClick;
    private String vrsta_odabrane_karte;
    private Integer kolicina;
    private String cijena;
    private int id;

    public KupioController(String vok, Integer c, String cijena, int id) {
        this.vrsta_odabrane_karte = vok;    this.kolicina = c;  this.cijena = cijena; this.id = id;
    }
    @FXML
    void initialize() throws KarteException {
        KarteManager km = new KarteManager();
        Karte k = km.getById(id);
        String vrsta = k.getVrsta();
        String adresa = k.getAdresa();
        String datum = k.getDatum();
        Double c1 = k.getCijena();
        Integer azuriranaKolcina = k.getKolicina()-kolicina;
        Prodavac p = k.getProdavac();
        Karte azurirana = new Karte(id,vrsta,datum,adresa,p,c1,azuriranaKolcina);
        km.update(azurirana);

        Double c = Double.parseDouble(cijena);
        Double ukupno = c*kolicina;
        String pomocni_string = ukupno.toString();
        fieldCijena.setText(pomocni_string);

    }
    public void zatvoriButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) fieldCijena.getScene().getWindow();
        stage.close();

    }
}
