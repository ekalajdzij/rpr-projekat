package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KupacDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class KupacManagerTest {
    private KupacManager kupacManager;
    private Kupac kupac;
    private KupacDAOSQLImplementation kupacDAOSQLImplementationMock;
    private List<Kupac> kupci;

    @BeforeEach
    public void initializeObjects() {
        kupacManager = Mockito.mock(KupacManager.class);
        kupac = new Kupac();
        kupacDAOSQLImplementationMock = Mockito.mock(KupacDAOSQLImplementation.class);
    }

    @Test
    void validateImeKupca() throws KarteException {
        String ispravno_ime = "John Johnson";
        try {
            Mockito.doCallRealMethod().when(kupacManager).validateKupacIme(ispravno_ime);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String neispravno_ime = "dx";
        Mockito.doCallRealMethod().when(kupacManager).validateKupacIme(neispravno_ime);
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
            kupacManager.validateKupacIme(neispravno_ime);},"Ime kupca mora biti izmedju 3 i 50 karaktera!");
        Assertions.assertEquals("Ime kupca mora biti izmedju 3 i 50 karaktera!",exception.getMessage());
    }
    @Test
    void getAllTest() throws KarteException {
        kupacManager = new KupacManager();
        kupac = kupacManager.getById(1);
        kupci = kupacManager.getAll();
        Assertions.assertEquals(1,kupci.get(0).getId());
        Assertions.assertEquals("Karim Smith", kupci.get(0).getIme());
    }
    @Test
    void deleteTest() {
        try {
            kupac = kupacManager.getById(7);
            Mockito.doCallRealMethod().when(kupacManager).delete(7);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    void updateTest() throws KarteException {
        ProdavacManager prodavacManager = Mockito.mock(ProdavacManager.class);
        KarteManager karteManager = Mockito.mock(KarteManager.class);
        Prodavac p = prodavacManager.getById(1);
        Karte k = karteManager.getById(1);
        Kupac kup = new Kupac(2,"John Lennon", "johnnyL@mail.com", "adresaJohhnija", "+381 333 222", p, k);
        try {
            Mockito.doCallRealMethod().when(kupacManager).update(kup);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

    }
    @Test
    void addTest() throws KarteException {
        /*
        Definisemo kada cemo da mock-ujemo daoFactory i sta treba da nam vrati
         */
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::kupacDAO).thenReturn(kupacDAOSQLImplementationMock);
        when(DaoFactory.kupacDAO().getAll()).thenReturn(kupci);
        /*
        Bacit ce se izuzetak jer instanca Kupac.java class ima value za id
         */
        ProdavacManager prodavacManager = Mockito.mock(ProdavacManager.class);
        KarteManager karteManager = Mockito.mock(KarteManager.class);
        Prodavac p = prodavacManager.getById(1);
        Karte k = karteManager.getById(1);
        kupac = new Kupac(40,"John Lennon", "johnnyL@mail.com", "adresaJohhnija", "+381 333 222", p, k);
        Mockito.doCallRealMethod().when(kupacManager).add(kupac);
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
                    kupacManager.add(kupac);},
                "Ne moze se dodati kupac sa ID-em. ID je automatski dodijeljen");
        Assertions.assertEquals("Ne moze se dodati kupac sa ID-em. ID je automatski dodijeljen",exception.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::kupacDAO);
        Mockito.verify(kupacManager).add(kupac);
        daoFactoryMockedStatic.close();
    }





}
