package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Prodavac;

import java.sql.SQLException;

public interface ProdavacDAO extends DAO<Prodavac>{
    int getId(String ime) throws SQLException;

}
