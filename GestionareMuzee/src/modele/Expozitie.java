package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Expozitie {
    private int idExpozitie;
    private String nume;
    private Date dataInceput;
    private Date dataSfarsit;

    private List<Exponat> exponate;

    public Expozitie(int idExpozitie, String nume, Date dataInceput, Date dataSfarsit) {
        this.idExpozitie = idExpozitie;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.exponate = new ArrayList<>();
    }

    public void adaugaExponat(Exponat exponat) {
        this.exponate.add(exponat);
        System.out.println("Exponatul a fost adăugat în expoziția: " + this.nume);
    }

    public void scoateExponat(Exponat exponat) {
        this.exponate.remove(exponat);
        System.out.println("Exponatul a fost retras din expoziție.");
    }
}