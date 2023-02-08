package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KupacDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
    public void mockitoTest() throws KarteException {
        ProdavacManager pm = new ProdavacManager();
        Prodavac p = pm.getById(1);
        KarteManager km = new KarteManager();
        Karte k = km.getById(1);
        Mockito.when(kupacManager.getById(1)).thenReturn(new Kupac());
        Kupac expected = new Kupac();
        Kupac actual = kupacManager.getById(1);
        Assertions.assertEquals(expected.getIme(),actual.getIme());
        Assertions.assertEquals(expected.getTelefon(),actual.getTelefon());

    }

    @Test
    public void validateImeKupca() throws KarteException {
        KupacManager km = new KupacManager();
        String ispravno_ime = "John Johnson";
        try {
           km.validateKupacIme(ispravno_ime);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String neispravno_ime = "dx";
        KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
            km.validateKupacIme(neispravno_ime);},"Ime kupca mora biti izmedju 3 i 50 karaktera!");
        Assertions.assertEquals("Ime kupca mora biti izmedju 3 i 50 karaktera!",exception.getMessage());
    }
    @Test
    public void getAllTest() throws KarteException {
        kupacManager = new KupacManager();
        kupac = kupacManager.getById(1);
        kupci = kupacManager.getAll();
        Assertions.assertEquals(1,kupci.get(0).getId());
        Assertions.assertEquals("Karim Smith", kupci.get(0).getIme());
    }
    @Test
    public void getImeKupcaTest() throws KarteException {
        KupacManager km = new KupacManager();
        Kupac k = km.getById(6);
        String expected = "John English";
        Assertions.assertEquals(expected,k.getIme());

    }
    @Test
    public void updateTest() throws KarteException {
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
    public void addTest() throws KarteException {
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
