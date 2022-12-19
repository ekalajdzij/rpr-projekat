package controls;

import ba.unsa.etf.rpr.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KupacController {

    public TextField fieldName;
    public PasswordField fieldPassword;
    public TextField fieldMail;
    public TextField fieldAdresa;
    public TextField fieldTelefon;
    private String vrsta_odabrane_karte;
    private Integer kolicina;
    public KupacController(String opcija, Integer kolicina) {
        this.vrsta_odabrane_karte = opcija; this.kolicina = kolicina;
    }

    public void loginButtonClick(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = new Stage();
        if (fieldName.getText().isEmpty() || fieldPassword.getText().isEmpty() || fieldAdresa.getText().isEmpty() || fieldMail.getText().isEmpty() || fieldTelefon.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška pri unosu podataka! ");
            alert.setContentText("Molimo Vas unesite vaše podatke ponovo!");
            alert.showAndWait();
        }
        else {
            FXMLLoader fxmlloader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/kupio.fxml"));
            Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Hvala");
            stage.setScene(scene);
            stage.show();

            Connection connection = Database.getConnection();
            KupacDAO kDAO = new KupacDAOSQLImplementation();
            String ime = fieldName.getText();
            String mail = fieldMail.getText();
            String telefon = fieldTelefon.getText();
            String adresa = fieldAdresa.getText();


            if (kDAO.getId(ime) == -1) {

                KarteDAO karteDAO = new KarteDAOSQLImplementation();
                int id_karte = karteDAO.dajIdKarte(vrsta_odabrane_karte);
                Karte karta = karteDAO.getById(id_karte);

                ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
                int id_prodavca = karteDAO.dajIdProdavcaKarte(vrsta_odabrane_karte);
                Prodavac prodavac = pDAO.getById(id_prodavca);
                Kupac k = new Kupac();

                k = new Kupac(0,ime, mail, adresa, telefon, prodavac, karta);
                kDAO.add(k);
            }
        }

    }
}
