package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

public interface ProdavacDAO extends DAO<Prodavac> {
    int getId(String ime) throws KarteException;

}
