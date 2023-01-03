package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

public class ProdavacManager {

    public List<Prodavac> getAll() throws KarteException {
        return DaoFactory.prodavacDAO().getAll();
    }

    public void delete (int id) throws KarteException {
        DaoFactory.prodavacDAO().delete(id);
    }

    public Prodavac getById (int id) throws KarteException {
        return DaoFactory.prodavacDAO().getById(id);
    }

    public void update (Prodavac p) throws KarteException {
        DaoFactory.prodavacDAO().update(p);
    }

    public Prodavac add (Prodavac p) throws KarteException {
        return DaoFactory.prodavacDAO().add(p);
    }
}
