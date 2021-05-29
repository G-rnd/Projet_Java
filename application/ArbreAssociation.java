package application;

import java.util.LinkedList;

/**
 * Arbre de l'association.
 */
public class ArbreAssociation {
    protected LinkedList<CompteRendu> listeCR = null;
    private final Arbre arbre;
    private int[] dateDerniereVisite = null;
    private int nbVotes = 0;

    /**
     * Crée un arbre de l'association à partir d'un arbre.
     *
     * @param a L'arbre associé.
     */
    public ArbreAssociation(Arbre a) {
        this.arbre = a;
    }

    /**
     * Renvoie la date de classification en arbre remarquable.
     *
     * @return Une copie de l'attribut dateRemarquable de l'arbre.
     */
    protected int[] getDateRemarquable() {
        return this.arbre.getDateRemarquable();
    }

    /**
     * Modifie la date de la dernière visite de l'arbre.
     *
     * @param d Tableau d'entier comportant une date.
     */
    protected void setDateDerniereVisite(int[] d) {
        try {
            if (date.estValide(d)) {
                this.dateDerniereVisite = d;
            } else {
                throw new ExceptionInInitializerError("Date invalide");
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Renvoie ne nombre de votes de l'arbre.
     *
     * @return L'attribut nbVotes de l'arbre de l'association.
     */
    protected int getNbVotes() {
        return this.nbVotes;
    }

    /**
     * Ajoute un vote à l'arbre de l'association.
     */
    protected void addNbVotes() {
        this.nbVotes++;
    }

    /**
     * Retire un vote à l'arbre de l'association.
     */
    protected void removeNbVotes() {
        this.nbVotes--;
    }

    /**
     * Renvoie l'arbre associé à l'arbre de l'association considéré.
     *
     * @return L'arbre associé.
     */
    protected Arbre getArbre() {
        return this.arbre;
    }

    /**
     * Efface toute mention écrite du membre des comptes-rendus de l'arbre de l'association.
     *
     * @param m Le membre considéré.
     */
    protected void masquer(Membre m) {
        for (CompteRendu cr : listeCR)
            cr.masquer(m);
    }
}
