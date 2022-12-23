package ba.unsa.etf.rpr;

import controls.JavaFXKlasa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class App extends Application{
    @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFXKlasa.HelloApplication.class.getResource("/fxml/pocetna.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("");
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) throws SQLException {
            launch();
            //KupacDAO kDAO = new KupacDAOSQLImplementation();
            //Kupac k = kDAO.getById(0);
            //System.out.println(k);
            //KarteDAO kartaD = new KarteDAOSQLImplementation();
            //List<Karte> karte = new ArrayList<>();
            //karte = kartaD.getAll();
            //for (Karte x : karte) System.out.println(x);
            /*ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
            int id = pDAO.getId("Damir Hos");
            Prodavac p = pDAO.getById(id);
            System.out.println(p);
            KupacDAO kDAO = new KupacDAOSQLImplementation();
            int idd = kDAO.getId("Karim Smith");
            Kupac k = kDAO.getById(idd);
            System.out.println(k);
            KarteDAO k = new KarteDAOSQLImplementation();
            List<String> l = k.getAllKarte();
            for (String x : l) System.out.println(x);
            KarteDAO k = new KarteDAOSQLImplementation();
            int in = k.dajIdKarte("Film");
            Double cijena = k.dajCijenu(in);
            int pid = k.dajIdProdavcaKarte("Film");
            System.out.println(cijena);*/
        }
}
