package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;

public interface KupacDAO extends DAO<Kupac> {
    int getId(String ime) throws KarteException;
}
