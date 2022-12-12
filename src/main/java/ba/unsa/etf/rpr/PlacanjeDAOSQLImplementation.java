package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.List;

public class PlacanjeDAOSQLImplementation implements DAO<Placanje>{
    @Override
    public Placanje getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Placanje> getAll() throws SQLException {
        return null;
    }

    @Override
    public int add(Placanje placanje) throws SQLException {
        return 0;
    }

    @Override
    public int update(Placanje placanje) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Placanje placanje) {
        return 0;
    }
}
