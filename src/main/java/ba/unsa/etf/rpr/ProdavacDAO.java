package ba.unsa.etf.rpr;

import java.sql.SQLException;

public interface ProdavacDAO extends DAO<Prodavac>{
    int getId(String ime) throws SQLException;

}
