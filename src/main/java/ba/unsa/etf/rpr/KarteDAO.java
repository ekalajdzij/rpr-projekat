package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Karte;

import java.sql.SQLException;
import java.util.List;

public interface KarteDAO extends DAO<Karte>{
    List<String> getAllKarte() throws SQLException;

    int dajIdKarte (String vrsta) throws SQLException;

    int dajIdProdavcaKarte(String vrsta) throws SQLException;

    Double dajCijenu(int id) throws SQLException;

}
