package modele;

public class Angajat extends Utilizator {
    protected int idAngajat;
    protected double salariu;

    public Angajat(int id, String nume, String email, int idAngajat, double salariu) {
        super(id, nume, email);
        this.idAngajat = idAngajat;
        this.salariu = salariu;
    }

    public void vizualizareProgram() {
        System.out.println("Programul de lucru pentru angajatul " + this.nume + " este 08:00 - 16:00.");
    }
}
