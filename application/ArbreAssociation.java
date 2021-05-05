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
        try {
            StringBuilder error = new StringBuilder();
            boolean res = true;
            if (date.length != 3) {
                error.append("Taille incorrecte");
                res = false;
            }
            if (date[0] <= 0 || date[0] > 31) {
                if (!res) { error.append(", "); }
                error.append("Jour incorrect");
                res = false;
            }
            if (date[1] <= 0 || date[1] > 12) {
                if (!res) { error.append(", "); }
                error.append("Mois incorrect");
                res = false;
            }
            if (date[2] <= 2020) {
                if (!res) { error.append(", "); }
                error.append("Année incorrecte");
                res = false;
            }
            if (res) {
                this.dateRemarquable = date;
                this.estRemarquable = true;
            }
            else { throw new ExceptionInInitializerError(error.toString()); }
        }
        catch (ExceptionInInitializerError e) {
            StringBuilder sb = new StringBuilder("Erreur : Date saisie incorrecte : ");
            sb.append(e.toString());
            sb.append(".");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        Arbre a1 = new Arbre("bla","bla2","blabla",1,1.1,"grand",
                "rue", new double[] {1.0,1.0});
        ArbreAssociation aRem = new ArbreAssociation(a1);
        aRem.rendreRemarquable(new int[] {5,5, 2021});
    }
}
