package teste;

import enums.MetodaPlata;
import enums.StareBilet;
import enums.TipBilet;
import modele.Bilet;
import modele.Tranzactie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class TranzactieTest {

    private Tranzactie tranzactie;

    @BeforeEach
    public void setUp() {
        tranzactie = new Tranzactie(2001, new Date(), MetodaPlata.CARD);
    }

    
    private List<Bilet> getListaBilete(Tranzactie t) throws Exception {
        Field field = Tranzactie.class.getDeclaredField("bilete");
        field.setAccessible(true);
        return (List<Bilet>) field.get(t);
    }

    
    private double getSumaTotala(Tranzactie t) throws Exception {
        Field field = Tranzactie.class.getDeclaredField("sumaTotala");
        field.setAccessible(true);
        return (double) field.get(t);
    }

    
    private static Stream<Arguments> dateTest() {
        return Stream.of(
                // metodaPlata, tipuri[], preturi[], sumaAsteptata, plataSucees, nrBileteAsteptat
                Arguments.of(
                        MetodaPlata.CARD,
                        new TipBilet[]{TipBilet.STANDARD},
                        new double[]{50.0},
                        50.0, true, 1
                ),
                Arguments.of(
                        MetodaPlata.CARD,
                        new TipBilet[]{TipBilet.STANDARD, TipBilet.STUDENT, TipBilet.ELEV},
                        new double[]{50.0, 25.0, 15.0},
                        90.0, true, 3
                ),
                Arguments.of(
                        MetodaPlata.CARD,
                        new TipBilet[]{},
                        new double[]{},
                        0.0, false, 0
                ),
                Arguments.of(
                        MetodaPlata.CASH,
                        new TipBilet[]{TipBilet.PENSIONAR},
                        new double[]{-10.0},
                        -10.0, false, 1
                ),
                Arguments.of(
                        MetodaPlata.CASH,
                        new TipBilet[]{TipBilet.STUDENT},
                        new double[]{25.0},
                        25.0, true, 1
                ),
                Arguments.of(
                        MetodaPlata.TRANSFER,
                        new TipBilet[]{TipBilet.ELEV},
                        new double[]{0.0},
                        0.0, false, 1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dateTest")
    public void testTranzactie(
            MetodaPlata metodaPlata,
            TipBilet[] tipuri,
            double[] preturi,
            double sumaAsteptata,
            boolean plataSucees,
            int nrBileteAsteptat
    ) throws Exception {

        // Cream o tranzactie noua cu metoda de plata specificata
        Tranzactie t = new Tranzactie(3001, new Date(), metodaPlata);

        // ACT: adaugam biletele in tranzactie
        Bilet ultimBilet = null;
        for (int i = 0; i < tipuri.length; i++) {
            ultimBilet = t.adaugaBiletInTranzactie(tipuri[i], preturi[i]);
        }

        // ASSERT 1: numarul de bilete din lista interna e corect
        List<Bilet> bilete = getListaBilete(t);
        assertEquals(nrBileteAsteptat, bilete.size(),
                "Numarul de bilete din tranzactie este incorect");

        // ASSERT 2: suma totala calculata e corecta
        double sumaReala = getSumaTotala(t);
        assertEquals(sumaAsteptata, sumaReala, 0.001,
                "Suma totala calculata de tranzactie este incorecta");

        // ASSERT 3: procesarePlata returneaza valoarea asteptata
        boolean rezultatPlata = t.procesarePlata();
        assertEquals(plataSucees, rezultatPlata,
                "Rezultatul procesarii platii este incorect");

        // ASSERT 4: daca exista bilete, verificam ca sunt in stare VALID
        if (ultimBilet != null) {
            assertEquals(StareBilet.VALID, ultimBilet.getStare(),
                    "Biletul trebuie sa fie VALID dupa creare");
        }

        // ASSERT 5: generareChitanta nu arunca exceptii
        assertDoesNotThrow(() -> t.generareChitanta(),
                "generareChitanta nu trebuie sa arunce exceptii");
    }
}
