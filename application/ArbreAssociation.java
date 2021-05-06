package application;

import java.util.LinkedList;

public class ArbreAssociation {
    private Arbre arbre;

    private int[] dateDerniereVisite = null;
    private int nbVotes = 0;

    // TODO à adapter en LinkedList<CR>
    protected LinkedList<String> listeCR = null;

    protected int[] getDateRemarquable() {
        return this.arbre.getDateRemarquable();
    }
    protected void setDateDerniereVisite(int[] d) {
        try {
            if (date.estValide(d)) {
                this.dateDerniereVisite = d;
            }
            else {
                throw new ExceptionInInitializerError("Date invalide");
            }
        }
        catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }

    protected int getNbVotes() { return this.nbVotes; }
    protected void addNbVotes() { this.nbVotes++; }
    protected void removeNbVotes() { this.nbVotes--; }
    protected Arbre getArbre() { return this.arbre; }

    //déclarer un arbre de l'association
    public ArbreAssociation(Arbre a) {
        this.arbre = a;
    }

    // TODO masquer = remplacer celui qui a écrit + celui qui a organisé par << personne ayant quitté l'association >>.
    protected void masquer(Membre m) {

    }


    public static void main(String[] args) {
        Arbre a1 = new Arbre(1,"bla","bla2","blabla",1,1.1,"grand",
                "rue", new double[] {1.0,1.0}, true);
        ArbreAssociation aRem = new ArbreAssociation(a1);
        aRem.arbre.rendreRemarquable(new int[] {5,9, 2021});
    }
}
