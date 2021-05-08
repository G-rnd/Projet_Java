package application;

import java.util.Collections;
import java.util.LinkedList;

public class Membre {
    private Personne p;
    private boolean president;
    private boolean cotisation;
    private int nbVisites;

    public final int nbVotesMax = 5;
    protected final static int nbVisitesMax = 20;

    private int[] dateInscription;
    protected LinkedList<ArbreAssociation> listeArbresVotes = new LinkedList<>();

    private int nbVotes;

    public int getNbVisitesRestantes() { return this.nbVisites; }
    protected void removeNbVisites() {
        if (this.getNbVisitesRestantes() > 0) { this.nbVisites--; }
    }
    protected void setNbVisites(int n) {
        if(n < nbVisitesMax && n >= 0) { this.nbVisites = n; }
        else { System.out.println("setNbVisites : Le nombre de visites saisi est incorrecte."); }
    }

    public boolean isPresident() { return  this.president; }

    public void setPresident() { this.president = true; }
    public void resetPresident() { this.president = false; }

    public boolean isCotisation() { return this.cotisation; }
    public void setCotisation(boolean b) { this.cotisation = b; }
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

    protected void voterArbre(ArbreAssociation a) {
        try {
            if (this != null && a != null) {
                if (a.getArbre().isEstRemarquable()) {
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
    protected void remplacerVoteArbre(Association a, int idAncien, int idNouveau) {
        try {
            if (a.getArbreById(idNouveau).getArbre().isEstRemarquable() ||
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
    protected void resetVote() {
        while(this.listeArbresVotes.size() > 0) {
            this.retirerVoteArbre(this.listeArbresVotes.getFirst().getArbre().getId());
        }
    }

    protected int getNbVotesRestants() { return this.nbVotes; }
    protected int getNbVotes() { return this.nbVotesMax - this.nbVotes; }
    protected Personne getPersonne() { return this.p; }

    public String getStringDateInscription() {
        return date.dateToString(this.dateInscription);
    }

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

    protected Membre(Personne p, int[] dateInscription) {
        this.p = p;
        this.dateInscription = dateInscription;
        this.cotisation = true;
        this.president = false;
        this.nbVotes = 5;
        this.nbVisites = 0;
    }
}
