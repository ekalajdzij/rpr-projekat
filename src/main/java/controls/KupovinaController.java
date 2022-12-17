package controls;

import ba.unsa.etf.rpr.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KupovinaController {

    public TextField fieldName;
    public PasswordField fieldPassword;
    public TextField fieldCijena;
    public TextField fieldKolicina;

    public Button okButton;
    public Button zatvoriButtonClick;
    public Label hvalaLabel;
    public ChoiceBox choiceBox = new ChoiceBox();
    public TextField fieldMail;
    public TextField fieldAdresa;
    public TextField fieldTelefon;
    private KarteDAO k = new KarteDAOSQLImplementation();
    private ObservableList<String> karte = FXCollections.observableArrayList(k.getAllKarte());

    public KupovinaController() throws SQLException {
    }


    @FXML
    private void initialize(){
        choiceBox.setItems(karte);
    }

    public void buttonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/karte.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setMinHeight(250);
        stage.setMaxHeight(350);
        stage.setMaxWidth(450);
        stage.setMinWidth(350);
        stage.setTitle("Karte");
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    public void okButtonClick(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/kupac.fxml"));
        Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Podaci");
        stage.setScene(scene);
        stage.show();
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
            Kupac k = null;
            String ime = fieldName.getText();
            String mail = fieldMail.getText();
            String telefon = fieldTelefon.getText();
            String adresa = fieldAdresa.getText();
            if (kDAO.getId(ime) == -1) {
                ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
                Prodavac p = pDAO.getById(1);
                KarteDAO kaDAO = new KarteDAOSQLImplementation();
                Karte ka = kaDAO.getById(1);
                k = new Kupac(0,ime, mail, adresa, telefon, p, ka);
                kDAO.add(k);
            }
        }

    }

    public void zButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) hvalaLabel.getScene().getWindow();
        stage.close();
    }
}
