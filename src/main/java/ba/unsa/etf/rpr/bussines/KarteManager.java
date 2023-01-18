package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.util.List;


public class KarteManager {

    public void validateKarteVrsta(String ime) throws KarteException {
        if(ime == null || ime.length() > 80 || ime.length() < 2)
            throw new KarteException("Naziv vrste karte mora biti izmedju 2 i 80 karaktera");
    }

    public List<Karte> getAll() throws KarteException {
        return DaoFactory.karteDAO().getAll();
    }

    public void delete(int id) throws KarteException {
        //DaoFactory.karteDAO().delete(id);
        try {
            DaoFactory.karteDAO().delete(id);
        } catch (KarteException e) {
            if(e.getMessage().contains("FOREIGN KEY"))
                throw new KarteException("Cannot delete Karte which is related to Kupac. First delete related Kupac before deleting Karte.");
            throw e;
        }
    }

    public Karte getById(int id) throws KarteException {
        return DaoFactory.karteDAO().getById(id);
    }

    public void update(Karte k) throws KarteException {
        validateKarteVrsta(k.getVrsta());
        DaoFactory.karteDAO().update(k);
    }
    public Karte add (Karte k) throws KarteException {
        if (k.getId() != 0) throw new KarteException("Ne moze se dodati karta sa ID-em. ID je automatski dodijeljen");
        validateKarteVrsta(k.getVrsta());
        try {
            return DaoFactory.karteDAO().add(k);
        } catch(KarteException e) {
            throw e;
        }
    }

    public int dajIdProdavcaKarte(String vrsta) throws KarteException {
        return DaoFactory.karteDAO().dajIdProdavcaKarte(vrsta);
    }

    public Double dajCijenu(int id) throws KarteException {
        return DaoFactory.karteDAO().dajCijenu(id);
    }
    public int dajIdKarte(String vrsta) throws KarteException {
        return DaoFactory.karteDAO().dajIdKarte(vrsta);
    }
    public List<String> getAllKarte() throws KarteException {
        return DaoFactory.karteDAO().getAllKarte();
    }
}
