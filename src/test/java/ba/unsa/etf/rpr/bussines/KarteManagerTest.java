package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KarteDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.AbstractSet;
import java.util.List;

import static org.mockito.Mockito.when;

public class KarteManagerTest {
    private KarteManager karteManager;
    private Karte karta;
    private KarteDAOSQLImplementation karteDAOSQLImplementationMock;
    private List<Karte> karte;

    @BeforeEach
    public void initializeObjects() {
        karteManager = Mockito.mock(KarteManager.class);
        karta = new Karte();
        karteDAOSQLImplementationMock = Mockito.mock(KarteDAOSQLImplementation.class);

    }
    @Test
    public void mockitoTest() throws KarteException {
        ProdavacManager pm = new ProdavacManager();
        Prodavac p = pm.getById(1);
        Mockito.when(karteManager.getById(1)).thenReturn(new Karte(100,"probaMockito","datum","adresa",p,22.5));
        Karte expected = new Karte(100,"probaMockito","datum","adresa",p,22.5);
        Karte actual = karteManager.getById(1);
        Assertions.assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void validateVrstuKarteTest() {
        String ispravno = "Film: U zemlji krvi i meda";
        try {
            karteManager.validateKarteVrsta(ispravno);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void updateTest() throws KarteException {
        KarteManager km = new KarteManager();
        ProdavacManager pm = new ProdavacManager();
        Prodavac p = pm.getById(1);
        Karte azurirati = new Karte(1,"Film: Babylon", "09.02.2023", "Cinestar, New York", p, 33.5);
        km.update(azurirati);

        Assertions.assertEquals("09.02.2023", km.getById(1).getDatum());
        Assertions.assertEquals("Film: Babylon",km.getById(1).getVrsta());


    }

    @Test
    public void deleteTest() throws KarteException {
        KarteManager km = new KarteManager();
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
            km.delete(2);}, "Cannot delete Karte which is related to Kupac. First delete related Kupac before deleting Karte.");
        Assertions.assertEquals("Cannot delete Karte which is related to Kupac. First delete related Kupac before deleting Karte.",exception.getMessage());
    }
    @Test
    public void getByIdTest() throws KarteException {
       KarteManager km = new KarteManager();
       Karte dobijena = km.getById(7);
       ProdavacManager pm = new ProdavacManager();
       Prodavac p = pm.getById(12);
       Karte stvarna = new Karte(7,"Fudbalska utakmica: Arsenal - Brentford", "11.02.2023", "Emirates Stadium, London",p, 200.0);
       Assertions.assertEquals(stvarna.getId(),dobijena.getId());
       Assertions.assertEquals(stvarna.getVrsta(),dobijena.getVrsta());
       Assertions.assertEquals(stvarna.getDatum(),dobijena.getDatum());
       Assertions.assertEquals(stvarna.getAdresa(),dobijena.getAdresa());
       Assertions.assertEquals(stvarna.getProdavac().getId(),dobijena.getProdavac().getId());
       Assertions.assertEquals(stvarna.getCijena(),dobijena.getCijena());


    }
    @Test
    public void add() throws KarteException {
       KarteManager km = new KarteManager();
       ProdavacManager pm = new ProdavacManager();
       Prodavac p = pm.getById(11);
       Karte nova = new Karte(21,"","","",p,0.0);
       KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
           km.add(nova);}, "Ne moze se dodati karta sa ID-em. ID je automatski dodijeljen");
       Assertions.assertEquals("Ne moze se dodati karta sa ID-em. ID je automatski dodijeljen", exception.getMessage());

    }
    @Test
    public void getAllTest() throws KarteException {
        KarteManager manager = new KarteManager();
        List<String> imena = manager.getAllKarte();
        Assertions.assertEquals("Fudbalska utakmica: Milan - Inter",imena.get(5));

    }




}
