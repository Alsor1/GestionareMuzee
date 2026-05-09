package teste;

import enums.*;
import modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CazUtilizareTest {

    private Vizitator vizitator;
    private Casier casier;
    private Tranzactie tranzactie;

    @BeforeEach
    public void setUp() {
        vizitator = new Vizitator(1, "Ion", "ion@email.com", true, "Carnet");
        casier = new Casier(2, "Maria", "maria@email.com", 101, 3500.0);
        tranzactie = new Tranzactie(1001, new Date(), MetodaPlata.CARD);
    }

    @ParameterizedTest
    @MethodSource("dateTest")
    public void testCazUtilizare(int numarBilete, TipBilet tip, double pret, boolean biletPreValidat, boolean expPlata, boolean expValidare) {
        Bilet ultimBilet = null;
        for (int i = 0; i < numarBilete; i++) {
            ultimBilet = vizitator.cumparaBilet(tranzactie, tip, pret);
        }

        assertEquals(expPlata, tranzactie.procesarePlata(), "Rezultat plata incorect");

        tranzactie.generareChitanta();

        ultimBilet.generareCodQR();

        if (biletPreValidat) {
            casier.valideazaBilet(ultimBilet);
        }

        boolean valid = casier.valideazaBilet(ultimBilet);
        assertEquals(expValidare, valid, "Rezultat validare incorect");

        if (expValidare) {
            assertEquals(StareBilet.FOLOSIT, ultimBilet.getStare(), "Starea nu a devenit FOLOSIT");
        }
    }

    private static Stream<Arguments> dateTest() {
        return Stream.of(
                Arguments.of(1, TipBilet.STANDARD, 50.0, false, true, true),
                Arguments.of(2, TipBilet.STUDENT, 25.0, false, true, true),
                Arguments.of(1, TipBilet.ELEV, 15.0, true, true, false),
                Arguments.of(1, TipBilet.PENSIONAR, -10.0, false, false, true)
        );
    }
}