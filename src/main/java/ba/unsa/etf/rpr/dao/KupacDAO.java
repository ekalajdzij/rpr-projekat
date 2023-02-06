package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;

/**
 * The interface of Kupac dao.
 */

public interface KupacDAO extends DAO<Kupac> {
    /**
     * Gets id of a buyer
     *
     * @param ime - the name of the buyer
     * @return id of the buyer
     * @throws KarteException if an error occurs
     */
    int getId(String ime) throws KarteException;
}
