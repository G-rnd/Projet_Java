package application;

import java.util.LinkedList;
import java.util.Scanner;

public class Association {
    private LinkedList<ArbreAssociation> listeArbres;
    private LinkedList<Membre> listeMembres;

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

    public void exclure(Membre m) {
        // TODO avec les CR.
        for(int i = 0; i < listeArbres.size(); i++) {
            listeArbres.get(i).masquer(m);
        }
        // retirer les votes
        if(m.getNbVotes() < 5) {
            for(int i = 0; i < m.listeArbresVotes.size(); i++) {
                m.listeArbresVotes.get(i).removeNbVotes();
            }
        }

        // si besoin de retirer autre choses.
        m = null;
    }


    public static void main(String[] args) {
        boolean ok = true;
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
