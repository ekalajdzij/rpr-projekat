package ba.unsa.etf.rpr;

import java.sql.SQLException;
import java.util.List;

public class KarteDAOSQLImplementation implements KarteDAO{
    @Override
    public Karte getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Karte> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Karte karte) throws SQLException {
        return 0;
    }

    @Override
    public int add(Karte karte) throws SQLException {
        return 0;
    }

    @Override
    public int update(Karte karte) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Karte karte) {
        return 0;
    }
}
