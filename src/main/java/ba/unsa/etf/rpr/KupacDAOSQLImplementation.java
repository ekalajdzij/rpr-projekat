package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.List;

public class KupacDAOSQLImplementation implements DAO<Kupac>{
    @Override
    public Kupac getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Kupac> getAll() throws SQLException {
        return null;
    }


    @Override
    public int add(Kupac kupac) throws SQLException {
        return 0;
    }

    @Override
    public int update(Kupac kupac) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Kupac kupac) {
        return 0;
    }
}
