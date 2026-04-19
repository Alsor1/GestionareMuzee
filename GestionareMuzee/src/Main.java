import java.util.Date;
import modele.*;
import enums.*;

public class Main {
    public static void main(String[] args) {
        // 1. Instanțiem utilizatorii
        Vizitator vizitator = new Vizitator(1, "Ion Popescu", "ion@email.com", false, "");
        Casier casier = new Casier(2, "Maria Casier", "maria@email.com", 101, 3500.0);

        // 2. Simulăm o Tranzacție (Compoziție: Tranzacția deține Biletele)
        Tranzactie tranzactie = new Tranzactie(1001, new Date(), MetodaPlata.CARD);

        // 3. Vizitatorul cumpără bilete (Asociere)
        Bilet bilet1 = vizitator.cumparaBilet(tranzactie, TipBilet.STANDARD, 50.0);
        Bilet bilet2 = vizitator.cumparaBilet(tranzactie, TipBilet.STUDENT, 25.0);

        // Procesăm plata
        tranzactie.procesarePlata();
        tranzactie.generareChitanta();

        // 4. Casierul validează biletul (Asociere)
        bilet1.generareCodQR();
        casier.valideazaBilet(bilet1);

        // Încercăm să validăm același bilet din nou
        casier.valideazaBilet(bilet1);
    }
}