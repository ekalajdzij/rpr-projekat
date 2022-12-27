package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

public interface KarteDAO extends DAO<Karte> {
    List<String> getAllKarte() throws KarteException;

    int dajIdKarte (String vrsta) throws KarteException;

    int dajIdProdavcaKarte(String vrsta) throws KarteException;

    Double dajCijenu(int id) throws KarteException;

}
