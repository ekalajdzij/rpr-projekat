package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KarteDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
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
    void validateVrstuKarteTest() {
        String ispravno = "Film: U zemlji krvi i meda";
        try {
            Mockito.doCallRealMethod().when(karteManager).validateKarteVrsta(ispravno);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    void updateTest() {
        try {
            Prodavac p = Mockito.mock(Prodavac.class);
            ProdavacManager pmanager = Mockito.mock(ProdavacManager.class);
            p = pmanager.getById(1);
            karta = new Karte(2, "Fudbalska utakmica: Argentina - Hrvatska", "13.12.2022", "Lusail Stadium, Doha", p, 1200.50);
            Mockito.doCallRealMethod().when(karteManager).update(karta);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    void deleteTest() throws KarteException {
        Mockito.doCallRealMethod().when(karteManager).delete(2);
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
            karteManager.delete(2);}, "Cannot delete Karte which is related to Kupac. First delete related Kupac before deleting Karte.");
        Assertions.assertEquals("Cannot delete Karte which is related to Kupac. First delete related Kupac before deleting Karte.",exception.getMessage());
    }




}
