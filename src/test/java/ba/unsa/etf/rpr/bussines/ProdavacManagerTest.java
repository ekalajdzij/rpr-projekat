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
    void validateImeProdavcaTest() throws KarteException {
        String ispravno = "Johnny Depp";
        try {
            Mockito.doCallRealMethod().when(prodavacManager).validateProdavacIme(ispravno);
        } catch(KarteException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String neispravno = "X";
        Mockito.doCallRealMethod().when(prodavacManager).validateProdavacIme(neispravno);
        KarteException keks = Assertions.assertThrows(KarteException.class, ()-> {
            prodavacManager.validateProdavacIme(neispravno);},
                "Ime prodavca mora biti izmedju 3 i 50 karaktera!");
        Assertions.assertEquals("Ime prodavca mora biti izmedju 3 i 50 karaktera!",keks.getMessage());

        String predugoIme = RandomStringUtils.randomAlphabetic(51);
        Mockito.doCallRealMethod().when(prodavacManager).validateProdavacIme(predugoIme);
        KarteException keks2 = Assertions.assertThrows(KarteException.class, ()-> {
            prodavacManager.validateProdavacIme(predugoIme);},
                "Ime prodavca mora biti izmedju 3 i 50 karaktera!");
        Assertions.assertEquals("Ime prodavca mora biti izmedju 3 i 50 karaktera!", keks2.getMessage());
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


}
