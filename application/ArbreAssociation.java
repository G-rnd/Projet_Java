package application;

import java.util.LinkedList;

public class ArbreAssociation {
    private Arbre arbre;

    private int nbVotes = 0;
    private boolean estRemarquable;
    private int[] dateRemarquable = new int[] {0, 0, 0};
    // TODO à adapter en LinkedList<CR>
    protected LinkedList<String> listeCR;

    private int[] dateDerniereVisite = new int[] {0, 0, 0};
    protected int[] getDateRemarquable() {
        return this.dateRemarquable.clone();
    }
    protected void setDateDerniereVisite(int[] d) {
        try {
            if (dateValide.estValide(d)) {
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


    protected boolean isEstRemarquable() { return this.estRemarquable; }
    protected int getNbVotes() { return this.nbVotes; }
    protected void addNbVotes() { this.nbVotes++; }
    protected void removeNbVotes() { this.nbVotes--; }
    protected Arbre getArbre() { return this.arbre; }

    //déclarer un arbre de l'association
    public ArbreAssociation(Arbre a) { this.arbre = a; }
    // déclarer un arbre de l'association en disant s'il est remarquable ou non
    public ArbreAssociation(Arbre a, boolean estRemarquable) {
        this(a);
        this.estRemarquable = estRemarquable;
    }
    // déclarer un arbre de l'association remarquable en lui donnant une date de classification
    public ArbreAssociation(Arbre a, int[] dateRemarquable) {
        this(a);
        try {
            if (dateValide.estValide(dateRemarquable)) {
                this.estRemarquable = true;
                this.rendreRemarquable(dateRemarquable);
            }
            else {
                throw new ExceptionInInitializerError("Erreur : Date incorrecte.");
            }
        }
        catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }

    public void rendreRemarquable(int[] date) {
        if (dateValide.estValide(date)){
            this.dateRemarquable = date;
            this.estRemarquable = true;
        }
        else {
            System.out.println("L'arbre n'a pas pu être rendu remarquable.");
        }
    }
    // TODO masquer = remplacer celui qui a écrit + celui qui a organisé par << personne ayant quitté l'association >>.
    protected void masquer(Membre m) {

    }


    public static void main(String[] args) {
        Arbre a1 = new Arbre("bla","bla2","blabla",1,1.1,"grand",
                "rue", new double[] {1.0,1.0});
        ArbreAssociation aRem = new ArbreAssociation(a1);
        aRem.rendreRemarquable(new int[] {5, 5, 2021});
    }
}
