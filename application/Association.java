package application;

import java.util.LinkedList;

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
                listeProposee.add(listeArbres.get(i).getArbre());
            }
        }
        return listeProposee;
    }
}
