package controls;

import ba.unsa.etf.rpr.dao.Database;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.dao.ProdavacDAO;
import ba.unsa.etf.rpr.dao.ProdavacDAOSQLImplementation;
import ba.unsa.etf.rpr.exceptions.KarteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ProdavacController {

    public TextField fieldIme;
    public TextField fieldTelefon;
    public TextField fieldMail;

    public void okButtonClick(ActionEvent actionEvent) throws SQLException, IOException, KarteException {

        if (fieldIme.getText().isEmpty() || fieldTelefon.getText().isEmpty() || fieldMail.getText().isEmpty()) {
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
        } else {
            String ime = fieldIme.getText();
            String telefon = fieldTelefon.getText();
            String mail = fieldMail.getText();

            Connection connection = Database.getConnection();
            ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
            Prodavac prodavac = new Prodavac(0, ime, telefon, mail);
            pDAO.add(prodavac);

            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/prodaja.fxml"));
            ProdajaController prodajaController = new ProdajaController(ime);
            fxmlloader.setController(prodajaController);

            Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Podaci");
            stage.setMaxHeight(400);
            stage.setMaxWidth(800);
            stage.setMinHeight(250);
            stage.setMinWidth(500);
            stage.setScene(scene);
            stage.show();
        }
    }
}
