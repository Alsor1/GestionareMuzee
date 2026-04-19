package modele;

import enums.TipBilet;

public class Vizitator extends Utilizator {
    private boolean areReducere;
    private String documentDoveditor;

    public Vizitator(int id, String nume, String email, boolean areReducere, String documentDoveditor) {
        super(id, nume, email);
        this.areReducere = areReducere;
        this.documentDoveditor = documentDoveditor;
    }

    public void rezervaTur() {
        System.out.println("Vizitatorul " + this.nume + " a rezervat un tur ghidat.");
    }

    public Bilet cumparaBilet(Tranzactie tranzactie, TipBilet tip, double pret) {
        System.out.println("Vizitatorul " + this.nume + " a inițiat cumpărarea unui bilet.");
        return tranzactie.adaugaBiletInTranzactie(tip, pret);
    }
}