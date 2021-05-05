package application;

import java.util.LinkedList;

public class Membre {
    private Personne p;

    private int[] dateInscription;
    // à adapter en LinkedList<Cotisations> si besoin
    private LinkedList<Double> listeCotisations = new LinkedList<Double>();

    protected LinkedList<Arbre> listeArbresVotes;
    private int nombreVotes;

    protected Membre(Personne p, int[] dateInscription, double cotisationEntree) {
        StringBuilder error = new StringBuilder("Erreur inscription membre : ");
        boolean res = true;
        try {
            if (dateValide.estValide(dateInscription)) {
                this.p = p;
                this.dateInscription = dateInscription;
                if (cotisationEntree > 0) {
                    this.listeCotisations.add(cotisationEntree);
                }
                else {
                    error.append(", Cotisation d'inscription invalide.");
                    res = false;
                }
            }
            else {
                res = false;
                error.append("Date invalide");
                if (cotisationEntree <= 0) {
                    error.append(", Cotisation d'inscription invalide");
                }
                error.append(".");
            }
            if (!res) { throw new ExceptionInInitializerError(error.toString()); }
        }
        catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }
}
