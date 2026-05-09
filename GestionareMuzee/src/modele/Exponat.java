package modele;

public class Exponat {
    private int idExponat;
    private String denumire;
    private String descriere;
    private int anOrigine;

    public Exponat(int idExponat, String denumire, String descriere, int anOrigine) {
        this.idExponat = idExponat;
        this.denumire = denumire;
        this.descriere = descriere;
        this.anOrigine = anOrigine;
    }

    public void afisareDetalii() {
        System.out.println("Exponat: " + denumire + " (An: " + anOrigine + ") - " + descriere);
    }

    public void actualizareStare() {
        System.out.println("Starea exponatului " + denumire + " a fost actualizată in baza de date.");
    }
}