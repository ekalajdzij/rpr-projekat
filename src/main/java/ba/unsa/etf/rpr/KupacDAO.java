package ba.unsa.etf.rpr;

import java.sql.SQLException;

public interface KupacDAO extends DAO<Kupac>{
    int getId(String ime) throws SQLException;
}
