package ba.unsa.etf.rpr.dao;

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
