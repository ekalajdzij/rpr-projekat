package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.KupacDAOSQLImplementation;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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



}
