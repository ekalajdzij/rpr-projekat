package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.SQLException;

public interface KupacDAO extends DAO<Kupac>{
    int getId(String ime) throws KarteException;
}
