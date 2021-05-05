package application;

import java.util.LinkedList;

public class Membre {
    private Personne p;

    private int[] dateInscription;
    // à adapter en LinkedList<Cotisations> si besoin
    private LinkedList<Double> listeCotisations = new LinkedList<Double>();
    protected LinkedList<ArbreAssociation> listeArbresVotes = new LinkedList<ArbreAssociation>();

    private int nombreVotes;

    protected void voterArbre(ArbreAssociation a) {
        this.nombreVotes--;
        this.listeArbresVotes.add(a);
        a.addNbVotes();
    }
    protected int getNbVotes() { return this.nombreVotes; }
    protected Personne getPersonne() { return this.p; }

    protected Membre(Personne p, int[] dateInscription, double cotisationEntree) {
        this.p = p;
        this.dateInscription = dateInscription;
        this.listeCotisations.add(cotisationEntree);
        this.nombreVotes = 5;
    }
}
