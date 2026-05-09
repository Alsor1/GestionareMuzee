package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import enums.TipBilet;
import enums.MetodaPlata;
import enums.StareBilet;

public class Tranzactie {
    private int idTranzactie;
    private Date dataPlata;
    private double sumaTotala;
    private MetodaPlata metoda;

    // Relație de Compoziție cu Bilet
    private List<Bilet> bilete;
    private static int counterBilete = 1;

    public Tranzactie(int idTranzactie, Date dataPlata, MetodaPlata metoda) {
        this.idTranzactie = idTranzactie;
        this.dataPlata = dataPlata;
        this.metoda = metoda;
        this.sumaTotala = 0.0;
        this.bilete = new ArrayList<>();
    }

    // Tranzacția controlează crearea biletelor (Compoziție)
    public Bilet adaugaBiletInTranzactie(TipBilet tip, double pret) {
        Bilet biletNou = new Bilet(counterBilete++, tip, StareBilet.VALID, pret);
        this.bilete.add(biletNou);
        this.sumaTotala += pret;
        return biletNou;
    }

    public boolean procesarePlata() {
        System.out.println("Se proceseaza plata de " + sumaTotala + " lei prin " + metoda + "...");
        if (sumaTotala > 0) {
            System.out.println("Plata procesata cu succes!");
            return true;
        }
        System.out.println("Eroare: Tranzactie goala!");
        return false;
    }

    public void generareChitanta() {
        System.out.println("--- CHITANTA Tranzactia #" + idTranzactie + " ---");
        System.out.println("Total platit: " + sumaTotala + " (" + metoda + ")");
        System.out.println("Bilete achizitionate: " + bilete.size());
        System.out.println("--------------------------------");
    }
}
