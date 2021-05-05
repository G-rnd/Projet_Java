package application;

import java.util.LinkedList;

public class ArbreAssociation {
    Arbre arbre;

    private boolean estRemarquable;
    private int[] dateRemarquable = new int[] {0, 0, 0};
    // à adapter en LinkedList<CR>
    private LinkedList<String> listeCR;

    public ArbreAssociation(Arbre a) {
        this.arbre = a;
    }
    public ArbreAssociation(Arbre a, boolean estRemarquable, int[] dateRemarquable) {
        this(a);
        try {
            if (estRemarquable == true) {
                this.estRemarquable = estRemarquable;
                this.rendreRemarquable(dateRemarquable);
            }
            else {
                throw new ExceptionInInitializerError();
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

    public static void main(String[] args) {
        Arbre a1 = new Arbre("bla","bla2","blabla",1,1.1,"grand",
                "rue", new double[] {1.0,1.0});
        ArbreAssociation aRem = new ArbreAssociation(a1);
        aRem.rendreRemarquable(new int[] {5,5, 2021});
    }
}
