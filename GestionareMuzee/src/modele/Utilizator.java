package modele;

public abstract class Utilizator {
    protected int idUtilizator;
    protected String nume;
    protected String email;

    public Utilizator(int idUtilizator, String nume, String email) {
        this.idUtilizator = idUtilizator;
        this.nume = nume;
        this.email = email;
    }

    public boolean login() {
        System.out.println(nume + " s-a autentificat în sistem.");
        return true;
    }

    public void logout() {
        System.out.println(nume + " a ieșit din cont.");
    }
}
