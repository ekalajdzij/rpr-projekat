package controls;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UbacioKartuController {
    public ImageView fieldImage;

    @FXML
    void initialize() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("C:/Users/Emir/Pictures/Saved Pictures/checkpoint.jpg"));
        fieldImage.setImage(image);
    }

}
