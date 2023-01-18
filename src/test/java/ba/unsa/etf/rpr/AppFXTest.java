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

/**
 * Unit test for simple App.
 */
public class AppFXTest {

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
    @Test
    public void test2() throws KarteException {
        KupacManager kupacManager = new KupacManager();
        Kupac kupac = kupacManager.getById(1);
        Assertions.assertEquals("+45 421 689", kupac.getTelefon());
        Assertions.assertEquals("El Halils, Casablanca", kupac.getAdresa());
        Assertions.assertEquals("karim@gmail.net.com", kupac.getMail());
    }
    @Test
    public void test3() throws KarteException {
        KarteManager karteManager = new KarteManager();
        Karte karta = karteManager.getById(6);
        Assertions.assertEquals("31.12.2022", karta.getDatum());
        Assertions.assertEquals("San Siro stadion, Milano, Italija", karta.getAdresa());
        Assertions.assertEquals(100, karta.getCijena());
    }




}
