package application;

import java.util.ArrayList;

public class Municipalite implements Donateur {
    private final String nom;
    private final float maxMontantAssociation = 1000;

    private final ArrayList<Arbre> listeArbresVivants;
    private final ArrayList<Arbre> listeArbresMorts;

    private final ArrayList<NotificationArbre> listeEntitesEvenementsArbres;

    public Municipalite(String nom, ArrayList<Arbre> listeArbres) {
        this.nom = nom;
        listeArbresVivants = listeArbres;
        listeArbresMorts = new ArrayList<>();
        listeEntitesEvenementsArbres = new ArrayList<>();
    }

    /**
     * Renvoie la liste des arbres vivants de la municipalité.
     *
     * @return La liste des arbres vivants de la municipalité
     */
    public ArrayList<Arbre> getListeArbres() {
        return listeArbresVivants;
    }

    @Override
    public void don(Donataire donataire, float montant) {
        donataire.recevoirDon(this, montant);
    }

    @Override
    public void recevoirDemandeDon(Donataire donataire, float montant, String message, String rapportActivite) {
        if (donataire instanceof ArbreAssociation)
            don(donataire, Math.min(montant, maxMontantAssociation));
        else
            don(donataire, 0);
    }

    /**
     * Permet de planter un arbre.
     *
     * @param arbre L'arbre à planter.
     */
    public void planterArbre(Arbre arbre) {
        if (listeArbresVivants.contains(arbre))
            System.out.println("[Municipalité] : Arbre déjà planté.");
        else {
            listeArbresVivants.add(arbre);
            System.out.println("[Municipalité] : Arbre planté.");
            for (NotificationArbre entite : listeEntitesEvenementsArbres)
                entite.notifierPlantation(arbre);
        }
    }

    /**
     * Permet d'abattre un arbre.
     *
     * @param arbre L'arbre à abattre.
     */
    public void abattreArbre(Arbre arbre) {
        if (listeArbresVivants.contains(arbre)) {
            listeArbresVivants.remove(arbre);
            listeArbresMorts.add(arbre);
            System.out.println("[Municipalité] : Arbre abattu.");
            for (NotificationArbre entite : listeEntitesEvenementsArbres)
                entite.notifierAbattage(arbre);
        } else {
            System.out.println("[Municipalité] : Arbre inexistant");
        }
    }

    /**
     * Permet de classifier un arbre à une date spécifique.
     *
     * @param arbre              L'arbre à classifier.
     * @param dateClassification La date de classification.
     */
    public void classifierArbre(Arbre arbre, int[] dateClassification) {
        if (listeArbresVivants.contains(arbre)) {
            if (arbre.getEstRemarquable())
                System.out.println("[Municipalité] : Arbre déjà remarquable.");
            else {
                if (date.estValide(dateClassification)) {
                    arbre.rendreRemarquable(dateClassification);
                    System.out.println("[Municipalité] : Arbre rendu remarquable");
                    for (NotificationArbre entite : listeEntitesEvenementsArbres)
                        entite.notifierClassification(arbre, dateClassification);
                }
            }
        } else
            System.out.println("[Municipalité] : L'arbre est introuvable.");
    }

    /**
     * Permet de classifier un arbre à une date spécifique.
     *
     * @param arbre L'arbre à classifier.
     */
    public void classifierArbre(Arbre arbre) {
        if (listeArbresVivants.contains(arbre)) {
            if (arbre.getEstRemarquable())
                System.out.println("[Municipalité] : Arbre déjà remarquable.");
            else {
                arbre.rendreRemarquable();
                System.out.println("[Municipalité] : Arbre rendu remarquable");
                for (NotificationArbre entite : listeEntitesEvenementsArbres)
                    entite.notifierClassification(arbre);
            }
        } else
            System.out.println("[Municipalité] : L'arbre est introuvable.");
    }

    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Permet d'ajouter une entité voulant être notifiée à chaque événement.
     *
     * @param entite L'entité à ajouter.
     */
    public void ajouterEntiteNotifiable(NotificationArbre entite) {
        listeEntitesEvenementsArbres.add(entite);
    }

    /**
     * Permet de retirer une entité de la liste des entités à notifier.
     *
     * @param entite L'entité à retirer.
     */
    public void retirerEntiteNotifiable(NotificationArbre entite) {
        listeEntitesEvenementsArbres.remove(entite);
    }
}
