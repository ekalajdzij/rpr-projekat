package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Kupac;

import java.sql.SQLException;

public interface KupacDAO extends DAO<Kupac>{
    int getId(String ime) throws SQLException;
}
