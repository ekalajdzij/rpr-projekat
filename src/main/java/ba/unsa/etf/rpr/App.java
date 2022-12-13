package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException {
        /*Prodavac p = new Prodavac(1, "Emir", "062336150", "ekalajdzij1@etf.unsa.ba");
        System.out.println(p);
        Karte k = new Karte(1, "utakmica", "12.02.2008", "London, Wembley Stadium", p, 200.50);
        System.out.println(k);
        Connection con = Database.getConnection();
        if (con != null) {
            System.out.println("Radi");
        }
        ProdavacDAO pDAO = new ProdavacDAOSQLImplementation();
        Prodavac novi = new Prodavac(3,"Damir Hos", "+387 62 333 111", "damirh@hotmail.at.de");
        int pr = pDAO.add(novi);

        System.out.println(pr);

        ProdavacDAO p111DAO = new ProdavacDAOSQLImplementation();
        List<Prodavac> pr111 = p111DAO.getAll();
        ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
        Prodavac prodavac = new Prodavac(2,"kkk","+381 666 222","lll");
        prodavacDAO.update(prodavac);
        System.out.println(prodavac);
         */
        ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
        Prodavac prodavac = prodavacDAO.getById(2);
        System.out.println(prodavac);
        int rez = prodavacDAO.delete(prodavac);








    }
}
