package teste;

import modele.Exponat;
import modele.Expozitie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ExpozitieTest {

    private Expozitie expozitie;
    private Exponat exponat;

    @BeforeEach
    public void setUp() {
        expozitie = new Expozitie(1, "Arta Moderna", new Date(), new Date());
        exponat = new Exponat(101, "Tablou Abstract", "Ulei pe panza", 2023);
    }

    @Test
    public void testAdaugaExponat() throws Exception {
        expozitie.adaugaExponat(exponat);

        Field field = Expozitie.class.getDeclaredField("exponate");
        field.setAccessible(true);
        List<Exponat> listaExponate = (List<Exponat>) field.get(expozitie);

        assertEquals(1, listaExponate.size(), "Lista de exponate ar trebui să conțină un element.");
        assertTrue(listaExponate.contains(exponat), "Exponatul adăugat ar trebui să se afle în listă.");
    }

    @Test
    public void testActualizareStareExponat() {

        assertDoesNotThrow(() -> exponat.actualizareStare(),
                "Metoda actualizareStare nu ar trebui să arunce excepții.");
    }

    @Test
    public void testScoateExponat() throws Exception {
        expozitie.adaugaExponat(exponat);
        expozitie.scoateExponat(exponat);

        Field field = Expozitie.class.getDeclaredField("exponate");
        field.setAccessible(true);
        List<Exponat> listaExponate = (List<Exponat>) field.get(expozitie);

        assertEquals(0, listaExponate.size(), "Lista ar trebui să fie goală după eliminare.");
    }
}