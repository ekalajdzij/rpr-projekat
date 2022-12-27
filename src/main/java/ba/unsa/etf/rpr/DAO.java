package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T getById(int id) throws KarteException;
    List<T> getAll() throws KarteException;
    T add(T item) throws KarteException;
    T update(T t) throws KarteException;
    void delete(int id) throws KarteException;

    //int getId(String ime) throws SQLException;

   // List<String> getAllKarte() throws SQLException;

}
