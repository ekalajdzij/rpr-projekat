package controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PocetnaController {
    public Button kupovinaButton;
    public Button prodajaButton;

    public void kupovinaButtonClick(ActionEvent actionEvent) throws IOException {
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

    public void prodajaButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/prodavac.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setMinHeight(250);
        stage.setMaxHeight(350);
        stage.setMaxWidth(450);
        stage.setMinWidth(350);
        stage.setTitle("Podaci");
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
