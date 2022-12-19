package controls;

import ba.unsa.etf.rpr.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class ProdajaController {


    public TextField fieldCijena;
    public TextField fieldVrsta;
    public TextField fieldDatum;
    public TextField fieldAdresa;
    private String ime;

    public ProdajaController(String ime) {
        this.ime = ime;
    }

    public void okButtonClick(ActionEvent actionEvent) throws SQLException {
        if (fieldCijena.getText().isEmpty() || fieldVrsta.getText().isEmpty() || fieldDatum.getText().isEmpty() || fieldAdresa.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška pri unosu podataka! ");
            alert.setContentText("Molimo Vas unesite podatke vaše karte ponovo!");
            alert.showAndWait();
        } else {
            String pomocni = fieldCijena.getText();
            Double cijena = Double.parseDouble(pomocni);
            String vrsta = fieldVrsta.getText();
            String datum = fieldDatum.getText();
            String adresa = fieldAdresa.getText();

            Connection connection = Database.getConnection();
            KarteDAO kDAO = new KarteDAOSQLImplementation();

            ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
            int p_id = pDAO.getId(ime);
            Prodavac prodavac = pDAO.getById(p_id);

            Karte k = new Karte(0,vrsta,datum,adresa,prodavac,cijena);
            kDAO.add(k);
        }

    }
}
