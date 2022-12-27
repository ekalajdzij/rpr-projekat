package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;

public class DaoFactory {
    private static final KarteDAO karteDao = new KarteDAOSQLImplementation();
    private static final KupacDAO kupacDao = new KupacDAOSQLImplementation();
    private static final ProdavacDAO prodavacDao = new ProdavacDAOSQLImplementation();

    private DaoFactory(){
    }

    public static KarteDAO karteDAO(){
        return karteDao;
    }

    public static KupacDAO kupacDAO(){
        return kupacDao;
    }

    public static ProdavacDAO prodavacDAO(){
        return prodavacDao;
    }
}
