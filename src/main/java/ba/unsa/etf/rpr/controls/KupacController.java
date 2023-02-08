package ba.unsa.etf.rpr.controls;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.KupacManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KupacController {


    public TextField fieldName;
    public TextField fieldMail;
    public TextField fieldAdresa;
    public TextField fieldTelefon;
    private String vrsta_odabrane_karte;
    private Integer kolicina;
    public KupacController(Karte opcija, Integer kolicina) {
        this.vrsta_odabrane_karte = opcija.getVrsta(); this.kolicina = kolicina;
    }

    public void okButtonClick(ActionEvent actionEvent) throws IOException, KarteException {
        Stage stage = new Stage();
        if (fieldName.getText().isEmpty() || fieldAdresa.getText().isEmpty() || fieldMail.getText().isEmpty() || fieldTelefon.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška pri unosu podataka! ");
            alert.setContentText("Molimo Vas unesite vaše podatke ponovo!");
            alert.showAndWait();
        }
        else if(!fieldMail.getText().contains("@")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unijeli ste neispravnu mail adresu! ");
            alert.setContentText("Molimo Vas unesite vašu mail adresu ponovo!");
            alert.showAndWait();
        }
        else {

            KupacManager kupacManager = new KupacManager();
            KupacDAOSQLImplementation novi = new KupacDAOSQLImplementation();
            Connection connection = novi.getConnection();
            String ime = fieldName.getText();
            String mail = fieldMail.getText();
            String telefon = fieldTelefon.getText();
            String adresa = fieldAdresa.getText();

            KarteManager karteManager = new KarteManager();
            int id_karte = karteManager.dajIdKarte(vrsta_odabrane_karte);
            Double cijena = karteManager.dajCijenu(id_karte);
            Karte karta = karteManager.getById(id_karte);

            ProdavacManager prodavacManager = new ProdavacManager();
            int id_prodavca = karteManager.dajIdProdavcaKarte(vrsta_odabrane_karte);
            Prodavac prodavac = prodavacManager.getById(id_prodavca);

           //Kupac k = new Kupac();
            Kupac k = new Kupac(0,ime, mail, adresa, telefon, prodavac, karta);
            kupacManager.add(k);

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/kupio.fxml"));
            KupioController kupioController = new KupioController(vrsta_odabrane_karte, kolicina, cijena.toString());
            fxmlloader.setController(kupioController);

            Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("");
            stage.setScene(scene);
            stage.show();
        }

    }
}
