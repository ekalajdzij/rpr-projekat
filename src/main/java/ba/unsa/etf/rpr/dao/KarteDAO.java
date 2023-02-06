package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

/**
 * The interface of Karte dao
 */

public interface KarteDAO extends DAO<Karte> {
    /**
     * Gets all tickets.
     *
     * @return all tickets names
     * @throws KarteException if an error occurs
     */
    List<String> getAllKarte() throws KarteException;

    /**
     * Gets an id of a ticket
     *
     * @param vrsta - name of the ticket
     * @return id of a appropriate ticket
     * @throws KarteException if an error occurs
     */
    int dajIdKarte (String vrsta) throws KarteException;

    /**
     * Gets an id of a seller of the appropriate ticket
     *
     * @param vrsta - name of the ticket
     * @return id of a appropriate seller
     * @throws KarteException if an error occurs
     */
    int dajIdProdavcaKarte(String vrsta) throws KarteException;

    /**
     * Gets a price of a ticket
     *
     * @param id - id of the ticket
     * @return price of the appropriate ticket
     * @throws KarteException if an error occurs
     */
    Double dajCijenu(int id) throws KarteException;

}
