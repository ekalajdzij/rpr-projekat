package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.List;

public class ProdavacDAOSQLImplementation implements ProdavacDAO{
    @Override
    public Prodavac getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Prodavac> getAll() throws SQLException {
        return null;
    }

    @Override
    public int add(Prodavac prodavac) throws SQLException {
        return 0;
    }

    @Override
    public int update(Prodavac prodavac) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Prodavac prodavac) {
        return 0;
    }
}
