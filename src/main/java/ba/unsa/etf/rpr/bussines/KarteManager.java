package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;


public class KarteManager {

    public List<Karte> getAll() throws KarteException {
        return DaoFactory.karteDAO().getAll();
    }

    public void delete(int id) throws KarteException {
        DaoFactory.karteDAO().delete(id);
    }

    public Karte getById(int id) throws KarteException {
        return DaoFactory.karteDAO().getById(id);
    }

    public void update(Karte k) throws KarteException {
        DaoFactory.karteDAO().update(k);
    }

    public Karte add (Karte k) throws KarteException {
        return DaoFactory.karteDAO().add(k);
    }
}
