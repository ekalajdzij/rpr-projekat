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



}
