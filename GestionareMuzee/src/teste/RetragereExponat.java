package teste;

import modele.Exponat;
import modele.Expozitie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class RetragereExponat {

    private Expozitie expozitie;
    private Exponat exponat1;
    private Exponat exponat2;
    private Exponat exponat3;

    @BeforeEach
    public void setUp() {
        expozitie = new Expozitie(1, "Arta Moderna", new Date(), new Date());
        exponat1 = new Exponat(101, "Tablou Abstract", "Ulei pe panza", 2020);
        exponat2 = new Exponat(102, "Sculptura Bronz", "Bronz turnat", 1990);
        exponat3 = new Exponat(103, "Fotografie Alb-Negru", "Print pe hartie", 1965);
    }

    private List<Exponat> getListaExponate() throws Exception {
        Field field = Expozitie.class.getDeclaredField("exponate");
        field.setAccessible(true);
        return (List<Exponat>) field.get(expozitie);
    }

    // Ramura DA din diagrama: exponatul exista -> se elimina cu succes
    @Test
    public void testScoateExponatExistent() throws Exception {
        expozitie.adaugaExponat(exponat1);
        expozitie.scoateExponat(exponat1);

        List<Exponat> lista = getListaExponate();
        assertEquals(0, lista.size(), "Lista ar trebui sa fie goala dupa retragere.");
        assertFalse(lista.contains(exponat1), "Exponatul retras nu ar trebui sa mai fie in lista.");
    }

    // Ramura NU din diagrama: exponatul nu exista -> lista ramane neschimbata
    @Test
    public void testScoateExponatInexistent() throws Exception {
        expozitie.adaugaExponat(exponat1);
        expozitie.scoateExponat(exponat2); // exponat2 nu a fost adaugat

        List<Exponat> lista = getListaExponate();
        assertEquals(1, lista.size(), "Lista nu ar trebui sa se modifice daca exponatul nu exista.");
        assertTrue(lista.contains(exponat1), "Exponatul existent ar trebui sa ramana in lista.");
    }

    // Ramura DA: retragere selectiva - doar exponatul corect e eliminat
    @Test
    public void testScoateExponatSelectiv() throws Exception {
        expozitie.adaugaExponat(exponat1);
        expozitie.adaugaExponat(exponat2);
        expozitie.adaugaExponat(exponat3);
        expozitie.scoateExponat(exponat2);

        List<Exponat> lista = getListaExponate();
        assertEquals(2, lista.size(), "Lista ar trebui sa contina 2 exponate dupa retragere.");
        assertTrue(lista.contains(exponat1), "Exponatul1 ar trebui sa ramana in lista.");
        assertFalse(lista.contains(exponat2), "Exponatul2 ar trebui sa fie retras din lista.");
        assertTrue(lista.contains(exponat3), "Exponatul3 ar trebui sa ramana in lista.");
    }

    // Ramura DA + NU: retragere repetata - a doua oara exponatul nu mai exista
    @Test
    public void testScoateExponatRepetata() throws Exception {
        expozitie.adaugaExponat(exponat1);
        expozitie.scoateExponat(exponat1); // prima retragere - ramura DA
        expozitie.scoateExponat(exponat1); // a doua retragere - ramura NU

        List<Exponat> lista = getListaExponate();
        assertEquals(0, lista.size(), "Lista ar trebui sa ramana goala dupa retragere repetata.");
    }

    // Ramura NU: retragere din lista vida
    @Test
    public void testScoateExponatDinListaVida() throws Exception {
        expozitie.scoateExponat(exponat1); // lista e goala, ramura NU

        List<Exponat> lista = getListaExponate();
        assertEquals(0, lista.size(), "Lista ar trebui sa ramana goala.");
    }

    // Verifica ca actualizareStare nu arunca exceptii (pas din diagrama - ramura DA)
    @Test
    public void testActualizareStareDupaRetragere() {
        expozitie.adaugaExponat(exponat1);
        expozitie.scoateExponat(exponat1);

        assertDoesNotThrow(() -> exponat1.actualizareStare(),
                "actualizareStare nu ar trebui sa arunce exceptii dupa retragere.");
    }
}
