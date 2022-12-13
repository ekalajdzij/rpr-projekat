package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    int add(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(T t) throws SQLException;

}
