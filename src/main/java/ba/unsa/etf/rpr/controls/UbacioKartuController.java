package ba.unsa.etf.rpr.controls;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UbacioKartuController {
    public ImageView fieldImage;

    @FXML
    void initialize() throws FileNotFoundException {
        Image image = new Image(getClass().getResourceAsStream("/checkpoint.jpg"));
        fieldImage.setImage(image);
    }

}
