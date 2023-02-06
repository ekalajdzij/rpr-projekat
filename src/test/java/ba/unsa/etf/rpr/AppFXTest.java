package ba.unsa.etf.rpr;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.KupacManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AppFXTest {

    /**
     * Test for getId for all dao implementations
     * @throws KarteException if an error occurs with the database
     */
    @Test
    public void test1() throws KarteException {
        KupacManager kupacManager = new KupacManager();
        Kupac kupac = kupacManager.getById(1);
        Assertions.assertEquals(1,kupac.getId());

        ProdavacManager prodavacManager = new ProdavacManager();
        Prodavac prodavac = prodavacManager.getById(1);
        Assertions.assertEquals(1,prodavac.getId());

        KarteManager karteManager = new KarteManager();
        Karte karta = karteManager.getById(1);
        Assertions.assertEquals(1,karta.getId());
    }

    /**
     * Test for methods of Kupac class
     * @throws KarteException if an error occurs with the database
     */
    @Test
    public void test2() throws KarteException {
        KupacManager kupacManager = new KupacManager();
        Kupac kupac = kupacManager.getById(1);
        Assertions.assertEquals("+45 421 689", kupac.getTelefon());
        Assertions.assertEquals("El Halils, Casablanca", kupac.getAdresa());
        Assertions.assertEquals("karim@gmail.net.com", kupac.getMail());
    }


    /**
     * Test for methods of Karte class
     * @throws KarteException if an error occurs with the database
     */
    @Test
    public void test3() throws KarteException {
        KarteManager karteManager = new KarteManager();
        Karte karta = karteManager.getById(6);
        Assertions.assertEquals("31.12.2022", karta.getDatum());
        Assertions.assertEquals("San Siro stadion, Milano, Italija", karta.getAdresa());
        Assertions.assertEquals(100, karta.getCijena());
    }

    /**
     * Test for KupacUpdate
     */
    @Test
    public void test4() throws KarteException {
        KupacManager kupacManager = new KupacManager();
        ProdavacManager pm = new ProdavacManager();
        Prodavac p = pm.getById(1);
        KarteManager km = new KarteManager();
        Karte k = km.getById(1);
        Kupac kupac = new Kupac(2,"John Johnny Lennon", "johnny123@gmail.net", "Los Angeles, California", "+91 111 112", p, k);
        kupacManager.update(kupac);
        Assertions.assertEquals("+91 111 112", kupac.getTelefon());
        Assertions.assertEquals("John Johnny Lennon", kupac.getIme());

    }




}
