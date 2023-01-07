package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;


public class KarteManager {

    public void validateKarteVsrta(String ime) throws KarteException {
        if(ime == null || ime.length() > 80 || ime.length() < 10)
            throw new KarteException("Naziv vrste karte mora biti izmedju 10 i 80 karaktera");
    }

    public List<Karte> getAll() throws KarteException {
        return DaoFactory.karteDAO().getAll();
    }

    public void delete(int id) throws KarteException {
        try {
            DaoFactory.karteDAO().delete(id);
        } catch(KarteException e) {
            if(e.getMessage().contains("FOREIGN KEY"))
                throw new KarteException("Cannot delete Karte which is related to Prodavac. First delete related Prodavac before deleting Karte");
        }
    }

    public Karte getById(int id) throws KarteException {
        return DaoFactory.karteDAO().getById(id);
    }

    public void update(Karte k) throws KarteException {
        validateKarteVsrta(k.getVrsta());
        DaoFactory.karteDAO().update(k);
    }

    public Karte add (Karte k) throws KarteException {
        validateKarteVsrta(k.getVrsta());
        return DaoFactory.karteDAO().add(k);
    }
}
