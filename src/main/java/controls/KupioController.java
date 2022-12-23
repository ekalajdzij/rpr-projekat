package controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KupioController {
    public Label fieldCijena = new Label();
    public Button zatvoriButtonClick;
    private String vrsta_odabrane_karte;
    private Integer kolicina;
    private String cijena;

    public KupioController(String vok, Integer c, String cijena) {
        this.vrsta_odabrane_karte = vok;    this.kolicina = c;  this.cijena = cijena;
    }
    @FXML
    void initialize() {
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
