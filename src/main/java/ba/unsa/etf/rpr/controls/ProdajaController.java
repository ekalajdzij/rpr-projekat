package ba.unsa.etf.rpr.controls;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class ProdajaController {


    public TextField fieldCijena;
    public TextField fieldVrsta;
    public TextField fieldDatum;
    public TextField fieldAdresa;
    public TextField fieldKolicina;
    private String ime;

    public ProdajaController(String ime) {
        this.ime = ime;
    }

    public void okButtonClick(ActionEvent actionEvent) throws KarteException, IOException {
        if (fieldCijena.getText().isEmpty() || fieldVrsta.getText().isEmpty() || fieldDatum.getText().isEmpty() || fieldAdresa.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška pri unosu podataka! ");
            alert.setContentText("Molimo Vas unesite podatke vaše karte ponovo!");
            alert.showAndWait();
        } else if(Integer.parseInt(fieldCijena.getText())== 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška! Cijena ne može biti 0! ");
            alert.setContentText("Molimo Vas unesite podatke vaše karte ponovo!");
            alert.showAndWait();
        } else {
            String pomocni = fieldCijena.getText();
            Double cijena = Double.parseDouble(pomocni);
            String vrsta = fieldVrsta.getText();
            String datum = fieldDatum.getText();
            String adresa = fieldAdresa.getText();
            Integer kolicina = Integer.parseInt(fieldKolicina.getText());

            ProdavacManager prodavacManager = new ProdavacManager();
            ProdavacDAOSQLImplementation novi = new ProdavacDAOSQLImplementation();
            Connection connection = novi.getConnection();
            KarteManager karteManager = new KarteManager();

            int p_id = prodavacManager.getId(ime);
            Prodavac prodavac = prodavacManager.getById(p_id);

            Karte k = new Karte(0,vrsta,datum,adresa,prodavac,cijena,kolicina);
            karteManager.add(k);

            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/ubaciokartu.fxml"));
            Scene scene = new Scene(fxmlloader.load(), 500, 250);
            stage.setTitle("");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

    }
}
