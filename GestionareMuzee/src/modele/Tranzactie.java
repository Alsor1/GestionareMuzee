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
        System.out.println("Se procesează plata de " + sumaTotala + " lei prin " + metoda + "...");
        if (sumaTotala > 0) {
            System.out.println("Plata procesată cu succes!");
            return true;
        }
        System.out.println("Eroare: Tranzacție goală!");
        return false;
    }

    public void generareChitanta() {
        System.out.println("--- CHITANȚĂ Tranzacția #" + idTranzactie + " ---");
        System.out.println("Total plătit: " + sumaTotala + " (" + metoda + ")");
        System.out.println("Bilete achiziționate: " + bilete.size());
        System.out.println("--------------------------------");
    }
}
