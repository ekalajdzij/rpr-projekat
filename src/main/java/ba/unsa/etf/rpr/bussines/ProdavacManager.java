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
        try {
            DaoFactory.prodavacDAO().delete(id);
        } catch(KarteException e) {
            if(e.getMessage().contains("foreign key")) {
                throw new KarteException("Cannot delete Prodavac which is related to Karte. First delete related Karte before deleting Prodavac");
            }
            throw e;
        }
    }

    public Prodavac getById (int id) throws KarteException {
        return DaoFactory.prodavacDAO().getById(id);
    }

    public void update (Prodavac p) throws KarteException {
        validateProdavacIme(p.getIme());
        DaoFactory.prodavacDAO().update(p);
    }

    public Prodavac add (Prodavac p) throws KarteException {
        if (p.getId() != 0) throw new KarteException("Ne moze se dodati prodavac sa ID-em. ID je automatski dodijeljen");
        validateProdavacIme(p.getIme());
        try {
            return DaoFactory.prodavacDAO().add(p);
        } catch(KarteException e) {
            throw e;
        }
    }
    public int getId(String ime) throws KarteException {
        return DaoFactory.prodavacDAO().getId(ime);
    }

}
