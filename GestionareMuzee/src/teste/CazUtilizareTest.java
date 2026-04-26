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
    public void testCazUtilizare(TipBilet tip, double pret, boolean adauga, StareBilet stare, boolean expPlata, boolean expValidare) {
        Bilet bilet = null;

        if (adauga) {
            bilet = vizitator.cumparaBilet(tranzactie, tip, pret);
            if (stare != null) {
                bilet.setStare(stare);
            }
        }

        assertEquals(expPlata, tranzactie.procesarePlata(), "Rezultat plata incorect");

        if (bilet != null) {
            assertEquals(expValidare, casier.valideazaBilet(bilet), "Rezultat validare incorect");

            if (expValidare) {
                assertEquals(StareBilet.FOLOSIT, bilet.getStare(), "Starea nu a devenit FOLOSIT");
            }
        }
    }

    private static Stream<Arguments> dateTest() {
        return Stream.of(
                Arguments.of(TipBilet.STANDARD, 50.0, true, StareBilet.VALID, true, true),
                Arguments.of(TipBilet.STUDENT, 25.0, true, StareBilet.FOLOSIT, true, false),
                Arguments.of(TipBilet.ELEV, 15.0, true, StareBilet.ANULAT, true, false),
                Arguments.of(TipBilet.STANDARD, 50.0, false, null, false, false),
                Arguments.of(TipBilet.PENSIONAR, -10.0, true, StareBilet.VALID, false, true)
        );
    }
}