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
    public void getTelefonTest() throws KarteException {
        KupacManager km = new KupacManager();
        Kupac k = km.getById(5);
        String expected = "+11 22  222  2";
        Assertions.assertEquals(expected,k.getTelefon());

    }
    @Test
    public void addTest() throws KarteException {
       KupacManager km = new KupacManager();
       ProdavacManager pm = new ProdavacManager();
       KarteManager kam = new KarteManager();
       Prodavac p = pm.getById(1);
       Karte k = kam.getById(1);
       Kupac novi = new Kupac(32,"Boris Johnson","boris@mail.net","","",p,k);
       KarteException exception = Assertions.assertThrows(KarteException.class, () -> {
           km.add(novi);
       },"Ne moze se dodati kupac sa ID-em. ID je automatski dodijeljen");
       Assertions.assertEquals("Ne moze se dodati kupac sa ID-em. ID je automatski dodijeljen",exception.getMessage());
    }

    @Test
    public void getAdresaTest() throws KarteException {
        KupacManager km = new KupacManager();
        Kupac k = km.getById(4);
        String expected = "Berlin Strasse, Berlin";
        Assertions.assertEquals(expected,k.getAdresa());

    }




}
