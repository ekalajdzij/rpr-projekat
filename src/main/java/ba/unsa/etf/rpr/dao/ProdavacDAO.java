package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

/**
 * The interface of Prodavac dao
 */

public interface ProdavacDAO extends DAO<Prodavac> {
    /**
     * Gets id of a seller
     *
     * @param ime - the name of the seller
     * @return id of the seller
     * @throws KarteException if an error occurs
     */
    int getId(String ime) throws KarteException;

}
