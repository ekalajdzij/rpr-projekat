package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;

public class KupacManager {

    public void validateKupacIme(String ime) throws KarteException {
        if(ime == null || ime.length() > 50 || ime.length() < 3)
            throw new KarteException("Ime kupca mora biti izmedju 3 i 50 karaktera!");
    }

    public List<Kupac> getAll() throws KarteException {
        return DaoFactory.kupacDAO().getAll();
    }

    public void delete (int id) throws KarteException {
        try {
            DaoFactory.kupacDAO().delete(id);
        } catch (KarteException e) {
            if(e.getMessage().contains("FOREIGN KEY"))
                throw new KarteException("Cannot delete Kupac which is related to Prodavac & Karte. First delete related Prodavac & Karte before deleting Kupac. ");
            throw e;
        }

    }

    public Kupac getById(int id) throws KarteException {
        return DaoFactory.kupacDAO().getById(id);
    }

    public void update (Kupac k) throws KarteException {
        validateKupacIme(k.getIme());
        DaoFactory.kupacDAO().update(k);
    }

    public Kupac add(Kupac k) throws KarteException {
        validateKupacIme(k.getIme());
        return DaoFactory.kupacDAO().add(k);
    }

    public int getId(String ime) throws KarteException {
        return DaoFactory.kupacDAO().getId(ime);
    }


}
