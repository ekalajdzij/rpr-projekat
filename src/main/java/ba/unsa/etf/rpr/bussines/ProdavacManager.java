package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

public class ProdavacManager {

    public void validateProdavacIme(String ime) throws KarteException{
        if(ime == null || ime.length() > 50 || ime.length() < 3)
            throw new KarteException("Ime prodavca mora biti izmedju 3 i 50 karaktera!");
    }

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
        validateProdavacIme(p.getIme());
        DaoFactory.prodavacDAO().update(p);
    }

    public Prodavac add (Prodavac p) throws KarteException {
        validateProdavacIme(p.getIme());
        return DaoFactory.prodavacDAO().add(p);
    }
}
