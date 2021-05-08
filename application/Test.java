package application;

import java.util.LinkedList;

/**
 * Classe de test.
 */
public class Test {
    /**
     * Ensemble des tests réalisés sur une association.
     * @param a L'association associée.
     */
    public static void main(Association a) {
        //a.afficherMembres();

        a.inscrire(new Personne("a","a",new int[]{1,1,2000},"a"),
                new int[] {1,1,2000});
        a.inscrire(new Personne("b","b",new int[]{1,1,2000},"b"),
                new int[] {1,1,2000});
        Membre m1 = a.getMembreByPersonne("a","a","1/1/2000","a");
        Membre m2 = a.getMembreByPersonne("b","b","1/1/2000","b");


        a.nommerPresident(m1);
        a.nommerPresident(m2);

        // a.afficherMembres();

        //a.desinscriptionMembre(m2);
        // a.afficherMembres();

        m2.voterArbre(a.getArbreById(100));
        m1.voterArbre(a.getArbreById(1));
        m1.voterArbre(a.getArbreById(2));
        m1.voterArbre(a.getArbreById(3));
        m1.voterArbre(a.getArbreById(3));

        m1.voterArbre(a.getArbreById(4));
        m1.voterArbre(a.getArbreById(5));
        m1.voterArbre(a.getArbreById(6));
        m1.voterArbre(a.getArbreById(-1));
        m1.remplacerVoteArbre(a, 3, 100);
  /*
        System.out.println("### Normalement pareils");
        a.getMembreByPersonne(m1.getPersonne().getNom(), m1.getPersonne().getPrenom(),
                    m1.getPersonne().getStringDateNaissance(), m1.getPersonne().getAdresse()).afficherVote();
        m1.afficherVote();
        System.out.println("###");
*/
        m1.afficherVote();
        m1.retirerVoteArbre(2);
        System.out.println("-----");
        try {
            LinkedList<Arbre> aL = a.proposerListeArbre();
            for(Arbre arbre : aL) {
                System.out.println(arbre.getId());
            }
        }
        catch (Exception e) {System.out.println(e.toString());}

        /*
        System.out.println("-----");
        for(int i = 1; i < 10; i++) {
            try {
                System.out.println(a.getArbreById(i).getNbVotes());
            } catch (Exception e) { System.out.println(e.toString());}
        }
        */
        System.out.println("---");

        Personne p = new Personne("R", "G", date.stringToDate("5/9/2000"), "80 ch rb");
        a.inscrire(p,date.stringToDate("9/5/2021"));
        a.desinscriptionMembre(a.getMembreByPersonne("R","G","5/9/2000","80 ch rb"));

        System.out.println(p.toString());

        System.out.println("Fin.");
    }
}
