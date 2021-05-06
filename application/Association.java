package application;

import java.util.LinkedList;
import java.util.Scanner;

public class Association {
    private LinkedList<ArbreAssociation> listeArbres = new LinkedList<ArbreAssociation>();
    private LinkedList<Membre> listeMembres = new LinkedList<Membre>();

    public Association() {
        // ---
    }

    public LinkedList<Arbre> proposerListeArbre() {
        LinkedList<Arbre> listeProposee = new LinkedList<Arbre>();
        if (this.listeArbres.size() > 5) {
            int nbArbre = 5;

            ArbreAssociation aMax = new ArbreAssociation(null);
            boolean init = false;
            while (nbArbre != 0) {
                for(int i = 0; i < listeArbres.size(); i++) {
                    // cherche un arbre non déjà choisi.
                    if (listeProposee.contains(listeArbres.get(i).getArbre())) { continue; }
                    // s'il est déjà remaquable, on ne le comptabilise pas.
                    if (listeArbres.get(i).isEstRemarquable()) { continue; }
                    else {
                        if (init) {
                            // choix entre deux arbres : le max temporaire et le 1-eme arbre de la liste.
                            ArbreAssociation aTemp = listeArbres.get(i);
                            // test du nombre de votes
                            if (aTemp.getNbVotes() > aMax.getNbVotes()) { aMax = aTemp; }
                            else {
                                // si nombre de votes est égal, on passe à la circonférence
                                if (aTemp.getNbVotes() == aMax.getNbVotes()) {
                                    if (aTemp.getArbre().getCirconference() > aMax.getArbre().getCirconference()) {
                                        aMax = aTemp;
                                    }
                                    // si nbVotes et circonférence sont égaux, on passe à la hauteur
                                    else {
                                        if(aTemp.getArbre().getCirconference() == aMax.getArbre().getCirconference()) {
                                            if(aTemp.getArbre().getHauteur() > aMax.getArbre().getHauteur()) {
                                                aMax = aTemp;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        else {
                            aMax = listeArbres.get(i);
                            init = true;
                        }
                    }
                }
                // à la fin, on a l'arbre à ajouter dans aMax
                listeProposee.add(aMax.getArbre());
            }
        }
        else {
            for(int i = 0; i < listeArbres.size(); i++) {
                if (listeArbres.get(i).isEstRemarquable()) { continue; }
                else {
                    listeProposee.add(listeArbres.get(i).getArbre());
                }
            }
        }
        return listeProposee;
    }

    public boolean estInscrit(Personne p) {
        for(int i = 0; i < this.listeMembres.size(); i++) {
            if(this.listeMembres.get(i).getPersonne().equals(p)) { return true;}
        }
        return false;
    }

    public void inscrire(Personne p, int[] date, double cotisationEntree) {
        StringBuilder error = new StringBuilder("Erreur inscription membre : ");
        boolean res = true;
        try {
            if (this.estInscrit(p)) { throw new ExceptionInInitializerError("Personne déjà inscrite."); }
            if (dateValide.estValide(date)) {
                if (cotisationEntree < 0) {
                    error.append(", Cotisation d'inscription invalide.");
                    res = false;
                }
            }
            else {
                res = false;
                error.append("Date invalide");
                if (cotisationEntree <= 0) {
                    error.append(", Cotisation d'inscription invalide");
                }
                error.append(".");
            }
            if (res) { this.listeMembres.add(new Membre(p, date, cotisationEntree)); }
            else { throw new ExceptionInInitializerError(error.toString()); }
        }
        catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }

    // todo : savoir si l'arbre est vivant ou pas
    public void voteMembre(Membre m, ArbreAssociation a) {
        if(m.getNbVotes() > 0) {
            if(!a.isEstRemarquable()) {
                m.listeArbresVotes.add(a);
                m.voterArbre(a);
            }
            else { System.out.println("Vote : arbre déjà remarquable."); }
        }
        else {
            m.listeArbresVotes.remove(0);
            m.listeArbresVotes.add(a);
            System.out.println("Vote : Le plus ancien choix a été retiré.");
        }
    }

    public void rendrePresident(Membre m) {
        for(int i = 0; i < listeMembres.size(); i++) {
            if (listeMembres.get(i).equals(m)) {
                m.setPresident();
            }
            else {
                if (listeMembres.get(i).isPresident()) {
                    listeMembres.get(i).setPresident(false);
                }
            }
        }
    }

    // cotisation annuelle d'un membre, vérifie s'il n'a pas déjà payé.
    public void payer(Membre m, double c) {
        if (m.isCotisation()) {
            System.out.print("Payer : le membre a déjà versé sa cotisation annuelle :");
            System.out.println(m.listeCotisations.getLast());
        }
        else {
            if (c > 0) {
                m.listeCotisations.add(c);
                m.setCotisation(true);
            }
        }
    }

    public void exclure(Membre m) {
        // TODO avec les CR.

        this.listeMembres.remove(m);

        for(int i = 0; i < listeArbres.size(); i++) {
            listeArbres.get(i).masquer(m);
        }
        // retirer les votes
        if(m.getNbVotes() < 5) {
            for(int i = 0; i < m.listeArbresVotes.size(); i++) {
                m.listeArbresVotes.get(i).removeNbVotes();
            }
        }
        // si président
        if(m.isPresident()) {
            this.listeMembres.get(0).setPresident();
        }

        // si besoin de retirer autre choses.
        m = null;
    }

    /*
        TODO pour les visites, l'ancienneté peut être obtenue/changée par getDateDerniereVisite/setDateDerniereVisite
     */

    /*
        TODO à la fin de chaque visite, la liste des CR est listeCR dans ArbreAssociation
     */

    /*
        TODO le nombre de visites max : (static) nbVisitesMax,
         les méthodes : setNbVisites, getNbVisites, removeNbVisites
     */

    /*
        TODO un objet CR
         ce serait sympa s'il y avait une méthode toString qui te rédige le cr
     */
    public static void main(String[] args) {
        boolean ok = true;
        Personne g = new Personne("NOM", "Prénom", new int[] {5,9,2000},"Ville");

        Association a = new Association();
        a.inscrire(g, new int[] {6,5,2021}, 25.0);
        System.out.println(a.listeMembres.size());
        a.exclure(a.listeMembres.get(0));
        System.out.println(a.listeMembres.size());
        try ( Scanner scanner = new Scanner( System.in ) ) {
            while(ok) {
                // affichage interface choix.
                System.out.println( "Veuillez saisir une action.");
                int res = scanner.nextInt();

                System.out.print("lu : ");
                System.out.println(res);
                if(res == -1) { ok = false; }

            }
        }
    }
}
