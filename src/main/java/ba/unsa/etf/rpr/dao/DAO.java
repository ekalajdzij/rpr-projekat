package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

public interface DAO<T> {
    /**
     * get entity from the database on ID
     *
     * @param id - primary key of the entity
     * @return the entity from the database
     * @throws KarteException in case of an error with the database
     */
    T getById(int id) throws KarteException;


    /**
     * lists all entities from database. Very slow operation because it reads all records.
     *
     *
     * @return List of entities from database
     * @throws KarteException in case of an error with the database
     */
    List<T> getAll() throws KarteException;


    /**
     * Saves an entity into the database
     *
     * @param item - bean for saving into the database
     * @return saved item with id field populated
     * @throws KarteException in case of an error with the database
     */
    T add(T item) throws KarteException;


    /**
     * Fully updates an entity in the database based on id (primary) match.
     *
     * @param t - bean to be updated. id field must be populated
     * @return an updated version of bean
     * @throws KarteException in case of an error with the database
     */
    T update(T t) throws KarteException;


    /**
     * Hard delete of an item from database with given id
     *
     * @param id - primary key of an entity which is to be deleted
     * @throws KarteException in case of an error with the database
     */
    void delete(int id) throws KarteException;

}
