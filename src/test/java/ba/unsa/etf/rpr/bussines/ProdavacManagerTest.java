package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ProdavacDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class ProdavacManagerTest {
    private ProdavacManager prodavacManager;
    private Prodavac prodavac;
    private ProdavacDAOSQLImplementation prodavacDAOSQLImplementation;
    private List<Prodavac> prodavci;

    @BeforeEach
    public void InitializeObjects () {
        prodavacManager = Mockito.mock(ProdavacManager.class);
        prodavac = new Prodavac();
        prodavacDAOSQLImplementation = Mockito.mock(ProdavacDAOSQLImplementation.class);
    }

    @Test
    void validateIspravnoImeProdavcaTest() throws KarteException {
        String ispravno = "Dorian Smith";
        ProdavacManager pm = new ProdavacManager();
        pm.validateProdavacIme(ispravno);
        Assertions.assertTrue(true);
    }
    @Test
    void updateTest() {
        try {
            prodavac = new Prodavac(10,"Elvis John Presley", "+33 123 444 55", "kraljiliking@mail");
            Mockito.doCallRealMethod().when(prodavacManager).update(prodavac);
        } catch (KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    void deleteTest() throws KarteException {
        Mockito.doCallRealMethod().when(prodavacManager).delete(12);
        KarteException exception = Assertions.assertThrows(KarteException.class, ()-> {
            prodavacManager.delete(12);},"Cannot delete Prodavac which is related to Karte. First delete related Karte before deleting Prodavac");
        Assertions.assertEquals("Cannot delete Prodavac which is related to Karte. First delete related Karte before deleting Prodavac",exception.getMessage());
    }
    @Test
    void getIdTest() throws KarteException {
        ProdavacManager pm = new ProdavacManager();
        Assertions.assertEquals(1,pm.getId("John Smith"));
    }
    @Test
    void add() throws KarteException {
        /*
        Definisemo kada cemo da mock-ujemo daoFactory i sta treba da nam vrati
         */
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::prodavacDAO).thenReturn(prodavacDAOSQLImplementation);
        when(DaoFactory.prodavacDAO().getAll()).thenReturn(prodavci);
        /*
        Bacit ce se izuzetak jer instanca Prodavac.java class ima value za id
         */
        prodavac = new Prodavac(30,"","","");
        Mockito.doCallRealMethod().when(prodavacManager).add(prodavac);
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
                    prodavacManager.add(prodavac);},
                "Ne moze se dodati prodavac sa ID-em. ID je automatski dodijeljen");
        Assertions.assertEquals("Ne moze se dodati prodavac sa ID-em. ID je automatski dodijeljen",exception.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::prodavacDAO);
        Mockito.verify(prodavacManager).add(prodavac);
        daoFactoryMockedStatic.close();
    }

}
