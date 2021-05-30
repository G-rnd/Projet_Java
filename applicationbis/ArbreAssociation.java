package application;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.Comparator;

/**
 * Arbre de l'association.
 */
public class ArbreAssociation{
    // Attributs
    private Arbre arbre;

    private int[] dateDerniereVisite = null;
    private int nbVotes = 0;

    private boolean visiteProgrammee=true;
    private boolean estRemarquable=true;

    // TODO à adapter en LinkedList<CR>
    protected LinkedList<String> listeCR = null;

    // Méthodes

    /**
     * Renvoie la date de classification en arbre remarquable.
     * @return Une copie de l'attribut dateRemarquable de l'arbre.
     */
    protected int[] getDateRemarquable() {
        return this.arbre.getDateRemarquable();
    }

    /**
     * Modifie la date de la dernière visite de l'arbre.
     * @param d Tableau d'entier comportant une date.
     */
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
    /**
     * Renvoie la date de la dernière visite d'un arbre
     * @return la date de la dernière visite d'un arbre
     */
protected int[] getDateDerniereVisite(){return this.dateDerniereVisite;}


    /**
     * Renvoie le nombre de votes de l'arbre.
     * @return L'attribut nbVotes de l'arbre de l'association.
     */
    protected int getNbVotes() { return this.nbVotes; }

    /**
     * Ajoute un vote à l'arbre de l'association.
     */
    protected void addNbVotes() { this.nbVotes++; }

    /**
     * Retire un vote à l'arbre de l'association.
     */
    protected void removeNbVotes() { this.nbVotes--; }

    /**
     * Renvoie l'arbre associé à l'arbre de l'association considéré.
     * @return L'arbre associé.
     */
    protected Arbre getArbre() { return this.arbre; }

    // TODO masquer = remplacer celui qui a écrit + celui qui a organisé par << personne ayant quitté l'association >>.

    /**
     * Efface toute mention écrite du membre des comptes-rendus de l'arbre de l'association.
     * @param m Le membre considéré.
     */
    protected void masquer(Membre m) {

    }

    /** Renvoie le booléen estRemarquable
     *
     */
    public boolean getEstRemarquable(){return this.estRemarquable;}

    /** Renvoie le booléen estRemarquable
     *
     */
    public boolean getVisiteProgrammee(){return this.visiteProgrammee;}

    /** Renvoie le booléen estRemarquable
     *
     */
    public void setVisiteProgrammee() {
        visiteProgrammee = true;
    }



    // Constructeur

    /**
     * Crée un arbre de l'association à partir d'un arbre.
     * @param a L'arbre associé.
     */
    public ArbreAssociation(Arbre a) {
        this.arbre = a;
    }

    /**
     * Compare les jours de dernière visite des arbres a1 et a2
     */
    public static Comparator<ArbreAssociation> ComparatorJour = new Comparator<ArbreAssociation>() {

        @Override
        public int compare(ArbreAssociation a1, ArbreAssociation a2) {
            int e1=a1.getDateDerniereVisite()[0];
            int e2=a2.getDateDerniereVisite()[0];
            return (int) (e1-e2);}
    };

        /**
         * Compare les mois de dernière visite des arbres a1 et a2
        */
        public static Comparator<ArbreAssociation> ComparatorMois = new Comparator<ArbreAssociation>() {

            @Override
            public int compare(ArbreAssociation a1, ArbreAssociation a2) {
                int e1 = a1.getDateDerniereVisite()[1];
                int e2 = a2.getDateDerniereVisite()[1];
                return (int) (e1 - e2);
            }
            };

    /**
     * Compare les années de dernière visite des arbres a1 et a2
     */
    public static Comparator<ArbreAssociation> ComparatorAnnee = new Comparator<ArbreAssociation>() {

                @Override
                public int compare(ArbreAssociation a1, ArbreAssociation a2) {
                    int e1 = a1.getDateDerniereVisite()[2];
                    int e2 = a2.getDateDerniereVisite()[2];
                    return (int) (e1 - e2);
                }
                };


}
