package modele;

import enums.StareBilet;
import enums.TipBilet;

import java.util.UUID;

public class Bilet {
    private int idBilet;
    private TipBilet tip;
    private StareBilet stare;
    private double pret;

    // Modificatorul 'package-private' (fără public) permite instanțierea doar din același pachet (ex. din Tranzactie)
    Bilet(int idBilet, TipBilet tip, StareBilet stare, double pret) {
        this.idBilet = idBilet;
        this.tip = tip;
        this.stare = stare;
        this.pret = pret;
    }

    public String generareCodQR() {
        String qrCode = "QR_" + UUID.randomUUID().toString().substring(0, 8) + "_" + idBilet;
        System.out.println("S-a generat codul QR: " + qrCode);
        return qrCode;
    }

    public boolean validare() {
        return this.stare == StareBilet.VALID;
    }

    public int getIdBilet() { return idBilet; }
    public StareBilet getStare() { return stare; }
    public void setStare(StareBilet stare) { this.stare = stare; }
    public double getPret() { return pret; }
}
