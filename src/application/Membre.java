package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Membre.
 */
public class Membre {
    // Attributs
    private final Personne p;
    private boolean president;
    private boolean cotisation;

    private int nbVotes;
    public final static int nbVotesMax = 5;

    private int nbVisites;
    protected final static int nbVisitesMax = 20;

    private final int[] dateInscription;
    protected LinkedList<ArbreAssociation> listeArbresVotes = new LinkedList<>();
    private ArrayList<CompteRendu> listeCrMembre;



    // Méthodes

    /**
     * Renvoie le nombre de visites disponibles pour le membre.
     * @return L'attribut nbVisites.
     */
    public int getNbVisitesRestantes() { return this.nbVisites; }

    /**
     * Retire une visite au nombre de visites disponibles.
     */
    protected void removeNbVisites() {
        if (this.getNbVisitesRestantes() > 0) { this.nbVisites--; }
    }

    /**
     * Met le nombre de visites restantes d'un membre à jour.
     * @param n Le nombre de visites restantes.
     */
    protected void setNbVisites(int n) {
        if(n < nbVisitesMax && n >= 0) { this.nbVisites = n; }
        else { System.out.println("[Membre] : Le nombre de visites saisi est incorrecte."); }
    }

    /**
     * Renvoie un booléen à VRAI si le membre est président, à FAUX sinon.
     * @return L'attribut president du membre.
     */
    public boolean isPresident() { return  this.president; }

    /**
     * Met l'attribut president à VRAI.
     */
    public void setPresident() { this.president = true; }

    /**
     * Met l'attribut president à FAUX.
     */
    public void resetPresident() { this.president = false; }

    /**
     * Permet de savoir si le membre a payé sa cotisation annuelle.
     * @return L'attribut cotisation du membre.
     */
    public boolean isCotisation() { return this.cotisation; }

    /**
     * Met à jour l'attribut cotisation du membre.
     * @param b La valeur nouvelle.
     */
    public void setCotisation(boolean b) { this.cotisation = b; }

    /**
     * Réalise le paiement de la cotisation annuelle d'un membre.
     * @return Un booléen à VRAI si le paiement a réussi, à FAUX sinon.
     */
    public boolean payer() {
        if (this.isCotisation()) {
            System.out.println("[Membre] : " + this.getPersonne().getPrenom() + " " + this.getPersonne().getNom() + " a déjà payé.");
            return false;
        }
        else {
            this.setCotisation(true);
            return true;
        }
    }

    /**
     * Ajoute un vote à un arbre.
     * @param a L'arbre de l'association considéré.
     */
    protected void voterArbre(ArbreAssociation a) {
        try {
            if (this != null && a != null) {
                if (a.getArbre().getEstRemarquable()) {
                    throw new Exception("Arbre déjà remarquable.");
                }
                else {
                    if(this.listeArbresVotes.contains(a)) {
                        throw new Exception("Arbre déjà choisi.");
                    }
                    else {
                        if(this.getNbVotesRestants() > 0) {
                            this.nbVotes--;
                            System.out.println("[Membre] : Le vote a été pris en compte.");
                        }
                        else {
                            this.listeArbresVotes.getFirst().removeNbVotes();
                            this.listeArbresVotes.removeFirst();
                            System.out.println("[Membre] : Le plus ancien choix a été retiré.");
                        }
                        this.listeArbresVotes.add(a);
                        a.addNbVotes();
                    }
                }
            }
            else {
                if (this == null) { throw new Exception("Membre introuvable."); }
                else { throw new Exception("Arbre introuvable;"); }
            }
        }
        catch (Exception e) { System.out.println("[Membre] : Le vote a échoué : " + e.toString()); }
    }

    /**
     * Affiche les votes d'un membre.
     */
    protected void afficherVote() {
        try {
            if (this != null) {
                System.out.println("[Membre] : Liste des Arbres votés par " + this.getPersonne().getNom()
                        + " " + this.getPersonne().getPrenom() + " :");
                for(int i = 0; i < this.getNbVotes(); i++) {
                    System.out.println("    - " + this.listeArbresVotes.get(i).getArbre().getId());
                }
                for(int i = 0; i < this.getNbVotesRestants(); i++) {
                    System.out.println("    - <en attente>");
                }
            }
            else { throw new Exception(); }
        }
        catch (Exception e) {
            System.out.println("[Membre] : Membre introuvable.");
        }
    }

    /**
     * Retire un vote d'un membre.
     * @param id L'id de l'arbre dont on souhaite retirer le vote.
     */
    protected void retirerVoteArbre(int id) {
        try {
            if (this != null && id >=0) {
                boolean res = true;
                for(int i = 0; i < this.getNbVotes() && res; i++) {
                    if(this.listeArbresVotes.get(i).getArbre().getId() == id) {
                        // retire le vote de l'arbre.
                        this.listeArbresVotes.get(i).removeNbVotes();
                        // retire le vote de la liste de vote du membre.
                        this.listeArbresVotes.remove(i);

                        // incrémente le nombre de votes.
                        this.nbVotes ++;
                        res = false;
                    }
                }
                System.out.println("Le vote " + ((res) ? "n'a pas " : "a ") + "été retiré.");
            }
            else {
                if (this == null) {
                    throw new Exception("Membre inexistant");
                }
                else { throw new Exception("id incorrect."); }
            }
        }
        catch (Exception e) {
            System.out.println("[Membre] : Le retrait du vote a échoué : " + e.toString());
        }
    }

    /**
     * Permet à un membre de changer de vote.
     * @param a L'association considérée.
     * @param idAncien L'id de l'arbre que l'on souhaite changer.
     * @param idNouveau L'id de l'arbre avec lequel on souhaite échanger un vote.
     */
    protected void remplacerVoteArbre(Association a, int idAncien, int idNouveau) {
        try {
            if (a.getArbreById(idNouveau).getArbre().getEstRemarquable() ||
                    (idAncien == idNouveau) ||
                    this.listeArbresVotes.contains(a.getArbreById(idNouveau)) ||
                    !this.listeArbresVotes.contains(a.getArbreById(idAncien)) ||
                    (this == null)
            ) { System.out.println("[Membre] : Le remplacement a échoué."); }
            else {
                boolean res = true;
                for(int i = 0; i < this.listeArbresVotes.size() && res; i++) {
                    if(this.listeArbresVotes.get(i).getArbre().getId() == idAncien) {
                        a.getArbreById(idAncien).removeNbVotes();
                        a.getArbreById(idNouveau).addNbVotes();
                        Collections.replaceAll(this.listeArbresVotes,a.getArbreById(idAncien), a.getArbreById(idNouveau));
                        res = false;
                    }
                }
                System.out.println("[Membre] : Le vote " + ((res) ? "n'a pas " : "a ") + "été remplacé.");
            }
        }
        catch (Exception e) { System.out.println("[Membre] : Le remplacement a échoué."); }
    }

    /**
     * Permet de retirer tous les votes d'un membre.
     */
    protected void resetVote() {
        while(this.listeArbresVotes.size() > 0) {
            this.retirerVoteArbre(this.listeArbresVotes.getFirst().getArbre().getId());
        }
    }

    /**
     * Renvoie le nombre de votes restants d'un membre avant de devoir effacer ses anciens votes s'il vote à nouveau.
     * @return L'attribut nbVotes du membre.
     */
    protected int getNbVotesRestants() { return this.nbVotes; }

    /**
     * Renvoie le nombre de votes effectués par un membre qui n'entraîneront pas un effacement d'anciens votes.
     * @return
     */
    protected int getNbVotes() { return this.nbVotesMax - this.nbVotes; }

    /**
     * Renvoie la personne associée au membre considéré.
     * @return La référence associée.
     */
    protected Personne getPersonne() { return this.p; }

    /**
     * Renvoie la date d'inscription du membre sous forme d'une chaîne de caractères.
     * @return Une chaîne de caractère au format DD/MM/AAAA.
     */
    public String getStringDateInscription() {
        return date.dateToString(this.dateInscription);
    }

    /**
     * Renvoie la liste des comptes rendus du membre
     * @return une liste de comptes rendus
     */
    public ArrayList<CompteRendu> getListeCrMembre(){return this.listeCrMembre;}

    /**
     * Renvoie une fiche d'information du membre.
     * @return Une chaîne de caractères comportant les informations du membre.
     */
    @Override
    public String toString() {
        return "--- Informatiions du membre ---\n" +
                "Nom                   : " +
                this.p.getNom() +
                "\nPrénom                : " +
                this.p.getPrenom() +
                "\nDate de naissance     : " +
                this.p.getStringDateNaissance() +
                "\nAdresse               :\n    " +
                this.p.getAdresse() +
                "\n\nDate d'inscription    : " +
                this.getStringDateInscription() +
                "\nRôle : " +
                ((this.president) ? "Président" : "Membre") +
                "\nPaiement Annuel       : " +
                ((this.cotisation) ? "Effectué" : "En attente");
    }

    // Constructeur

    /**
     * Initialise un membre à partir d'une personne et d'une date d'inscription.
     * @param p La personne considérée.
     * @param dateInscription Un tableau d'entiers comportant une date valide.
     */
    protected Membre(Personne p, int[] dateInscription) {
        this.p = p;
        this.dateInscription = dateInscription;
        this.cotisation = true;
        this.president = false;
        this.nbVotes = 5;
        this.nbVisites = 0;
        this.listeCrMembre=new ArrayList<>();
    }
}
