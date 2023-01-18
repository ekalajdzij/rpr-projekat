package controls;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KarteController {
    public TextField fieldKolicina;
    public Button okButton;
    public ChoiceBox<Karte> choiceBox = new ChoiceBox<>();
    public TextArea adresaDogadjajaArea;
    public TextArea datumDogadjajaArea;

    public KarteModel model = new KarteModel();

    private KarteManager karteManager = new KarteManager();

    public KarteController() throws KarteException {
    }


    @FXML
    private void initialize() throws KarteException {
        choiceBox.setItems(FXCollections.observableArrayList(karteManager.getAll()));
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null) {
                datumDogadjajaArea.textProperty().unbindBidirectional(oldValue);
                adresaDogadjajaArea.textProperty().unbindBidirectional(oldValue);
            }
            if (newValue == null) {
                datumDogadjajaArea.setText("");
                adresaDogadjajaArea.setText("");
            } else {
                model = new KarteModel(newValue);
                datumDogadjajaArea.textProperty().bindBidirectional(model.datumProperty());
                adresaDogadjajaArea.textProperty().bindBidirectional(model.adresaProperty());


            }
        });
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/kupac.fxml"));
            KupacController kupacController = new KupacController(choiceBox.getSelectionModel().getSelectedItem(),Integer.valueOf(fieldKolicina.getText()));
            fxmlloader.setController(kupacController);
            Scene scene = new Scene(fxmlloader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Podaci");
            stage.setScene(scene);
            stage.show();

        }
    }
    public class KarteModel {
        public SimpleStringProperty datum = new SimpleStringProperty("");
        public SimpleStringProperty adresa = new SimpleStringProperty("");
        public SimpleObjectProperty<Karte> trenutnaKarta = new SimpleObjectProperty<>();

        public KarteModel() throws KarteException {
        }
        public KarteModel (Karte k) {
            datum.set(k.getDatum());
            adresa.set(k.getAdresa());
            this.setTrenutnaKarta(k);
        }

        public String getDatum() {
            return datum.get();
        }

        public SimpleStringProperty datumProperty() {
            return datum;
        }

        public void setDatum(String datum) {
            this.datum.set(datum);
        }

        public String getAdresa() {
            return adresa.get();
        }

        public SimpleStringProperty adresaProperty() {
            return adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa.set(adresa);
        }

        public Karte getTrenutnaKarta() {
            return trenutnaKarta.get();
        }

        public SimpleObjectProperty<Karte> trenutnaKartaProperty() {
            return trenutnaKarta;
        }

        public void setTrenutnaKarta(Karte trenutnaKarta) {
            this.trenutnaKarta.set(trenutnaKarta);
        }

        public void fromKarte(Karte k) {
            this.datum.set(k.getDatum());
            this.adresa.set(k.getAdresa());
        }
        public Karte toKarte() {
            Karte k = new Karte();
            k.setDatum(this.datum.getValue());
            k.setAdresa(this.adresa.getValue());
            return k;
        }
    }

}
