package controls;

import ba.unsa.etf.rpr.KarteDAO;
import ba.unsa.etf.rpr.KarteDAOSQLImplementation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KarteController {
    public TextField fieldKolicina;
    public Button okButton;
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();

    private KarteDAO k = new KarteDAOSQLImplementation();
    private ObservableList<String> karte = FXCollections.observableArrayList(k.getAllKarte());

    public KarteController() throws SQLException {
    }


    @FXML
    private void initialize(){
        choiceBox.setItems(karte);
    }

    public void okButtonClick(ActionEvent actionEvent) throws IOException {
        if (choiceBox.getValue() == null || fieldKolicina.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška");
            alert.setHeaderText("Greška pri unosu podataka! ");
            alert.setContentText("Molimo Vas unesite podatke o kupovini ponovo!");
            alert.showAndWait();
        } else {
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/kupac.fxml"));
            KupacController kupacController = new KupacController(choiceBox.getSelectionModel().getSelectedItem(),Integer.valueOf(fieldKolicina.getText()));
            fxmlloader.setController(kupacController);
            Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Podaci");
            stage.setScene(scene);
            stage.show();

        }
    }
}
