package modele;
import enums.StareBilet;

public class Casier extends Angajat {
    public Casier(int id, String nume, String email, int idAngajat, double salariu) {
        super(id, nume, email, idAngajat, salariu);
    }

    public boolean valideazaBilet(Bilet bilet) {
        if (bilet.getStare() == StareBilet.VALID) {
            bilet.setStare(StareBilet.FOLOSIT);
            System.out.println("Casierul " + this.nume + " a validat biletul: " + bilet.getIdBilet());
            return true;
        }
        System.out.println("Biletul este deja folosit sau anulat!");
        return false;
    }
}