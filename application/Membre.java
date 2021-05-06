package application;

import java.util.LinkedList;

public class Membre {
    private Personne p;
    private boolean president;
    private boolean cotisation;
    private int nbVisites;
    protected final static int nbVisitesMax = 20;

    private int[] dateInscription;
    // à adapter en LinkedList<Cotisations> si besoin
    protected LinkedList<Double> listeCotisations = new LinkedList<Double>();
    protected LinkedList<ArbreAssociation> listeArbresVotes = new LinkedList<ArbreAssociation>();

    private int nombreVotes;

    public int getNbVisites() { return this.nbVisites; }
    protected void removeNbVisites() {
        if (this.getNbVisites() > 0) { this.nbVisites--; }
    }
    protected void setNbVisites(int n) {
        if(n < nbVisitesMax && n >= 0) { this.nbVisites = n; }
        else { System.out.println("setNbVisites : Le nombre de visites saisi est incorrecte."); }
    }

    public boolean isPresident() { return  this.president; }

    public void setPresident() { this.president = true; }
    public void setPresident(boolean b) { this.president = b; }

    public boolean isCotisation() { return this.cotisation; }
    public void setCotisation(boolean b) { this.cotisation = b; }
    public void payer(double montant) {
        if (!this.isCotisation() && montant > 0) {
            this.setCotisation(true);
            this.listeCotisations.add(montant);
        }
    }

    protected void voterArbre(ArbreAssociation a) {
        this.nombreVotes--;
        this.listeArbresVotes.add(a);
        a.addNbVotes();
    }
    protected int getNbVotes() { return this.nombreVotes; }
    protected Personne getPersonne() { return this.p; }

    public String getStringDateInscription() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.dateInscription[0]);
        for(int i = 1; i < 3; i ++) {
            sb.append("/ ");
            sb.append(this.dateInscription[i]);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Informatiions du membre ---\n");
        sb.append("Nom                   : ");
        sb.append(this.p.getNom());
        sb.append("\nPrénom                : ");
        sb.append(this.p.getPrenom());
        sb.append("\nDate de naissance     : ");
        sb.append(this.p.getStringDateNaissance());
        sb.append("\nAdresse               :\n    ");
        sb.append(this.p.getAdresse());
        sb.append("\nDate d'inscription    : ");
        sb.append(this.getStringDateInscription());
        sb.append("\nRôle : ");
        sb.append((this.president) ? "Président" : "Membre");
        sb.append("\nListe des cotisations :\n");
        for(int i = 0; i < this.listeCotisations.size(); i++) {
            sb.append("    ");
            sb.append(this.listeCotisations.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    protected Membre(Personne p, int[] dateInscription, double cotisationEntree) {
        this.p = p;
        this.dateInscription = dateInscription;
        this.listeCotisations.add(cotisationEntree);
        this.cotisation = true;
        this.president = false;
        this.nombreVotes = 5;
        this.nbVisites = 0;
    }
}
