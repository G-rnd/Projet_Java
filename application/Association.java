package application;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Association.
 */
public class Association {
    // Attributs.
    public final double montantCotisation = 10.5;
    private int nbMembres = 0;

    private ArrayList<Membre> listeMembres = new ArrayList<>();
    private ArrayList<ArbreAssociation> listeArbres = new ArrayList<>();

    // Méthodes.

    /**
     * Renvoie le montant de la cotisation annuelle de l'association.
     * @return L'attribut montantCotisation de l'association.
     */
    public double getMontantCotisation() { return this.montantCotisation; }

    /**
     * Renvoie le nombre de membres de l'association.
     * @return L'attribut nbMembres de l'association.
     */
    public int getNbMembres() { return this.nbMembres; }

    /**
     * Ajoute le nombre passé en paramètre au nombre de membres.
     * @param n L'entier à ajouter au nombre de membres.
     */
    protected void addNbMembre(int n) {
        if(this.nbMembres + n > 0) { this.nbMembres += n; }
    }

    /**
     * Renvoie le i-ème membre de la liste des membres de l'association.
     * @param i L'indice du membre considéré.
     * @return Le membre s'il existe, null sinon.
     */
    protected Membre getMembre(int i) {
        try {
            return this.listeMembres.get(i);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Renvoie un classement d'arbres candidats à la classification en tant qu'arbres remarquables.
     * @return Une liste d'arbres de taille au plus 5.
     */
    public LinkedList<Arbre> proposerListeArbre() {
        // Liste dans laquelle on range les arbres votés.
        LinkedList<Arbre> listeProposee = new LinkedList<>();

        ArbreAssociation aMax = null;
        int nbArbre = 5;
        boolean init = true;
        boolean ok = false;

        // tant que l'on a pas choisi 5 arbres et que l'on a pas parcouru toute la liste sans en choisir un nouveau.
        while (nbArbre != 0 && !ok) {
            ok = true;

            for (ArbreAssociation aa : listeArbres) {
                    // cherche un arbre non déjà choisi.
                if (!(listeProposee.contains(aa.getArbre()) ||
                    // s'il n'a pas été voté, on ne le compte pas.
                    aa.getNbVotes() == 0 ||
                    // s'il est déjà remaquable, on ne le comptabilise pas.
                    aa.getArbre().getEstRemarquable()
                )) {
                    if (!init) {
                        // choix entre deux arbres : le max temporaire et le i-eme arbre de la liste.
                        // test du nombre de votes
                        if (aa.getNbVotes() > aMax.getNbVotes()) {
                            aMax = aa;
                            ok = false;
                        } else {
                            // si nombre de votes est égal, on passe à la circonférence
                            if (aa.getNbVotes() == aMax.getNbVotes()) {
                                if (aa.getArbre().getCirconference() > aMax.getArbre().getCirconference()) {
                                    aMax = aa;
                                    ok = false;
                                }
                                // si nbVotes et circonférence sont égaux, on passe à la hauteur
                                else {
                                    if (aa.getArbre().getCirconference() == aMax.getArbre().getCirconference()) {
                                        if (aa.getArbre().getHauteur() > aMax.getArbre().getHauteur()) {
                                            aMax = aa;
                                            ok = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        // 1er arbre de la boucle détecté.

                        aMax = aa;
                        init = false;
                        ok = false;
                    }
                }
            }
            if (!ok) {
                // à la fin, on a l'arbre à ajouter dans aMax si on a trouvé un Arbre.
                listeProposee.add(aMax.getArbre());
                aMax = null;
                init = true;
                nbArbre--;
            }
        }
        return listeProposee;
    }

    /**
     * Permet de savoir si une personne est inscrite à l'association.
     * @param p La personne considérée.
     * @return Un booléen à VRAI si la personne est inscrite, à FAUX sinon.
     */
    public boolean estInscrit(Personne p) {
        for (Membre listeMembre : this.listeMembres) {
            if (listeMembre.getPersonne().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet d'inscrire une personne à une date choisi.
     * @param p La personne considérée.
     * @param date la date considérée.
     * @return Le membre s'il est inscrit, null sinon.
     */
    public Membre inscrire(Personne p, int[] date) {
        Membre m = null;
        try {
            if (this.estInscrit(p)) {
                throw new Exception("La personne est déjà inscrite.");
            }
            else {
                if (application.date.estValide(date)) {
                    m = new Membre(p, date);
                    if (this.getNbMembres() == 0) { m.setPresident(); }
                    this.addNbMembre(1);
                    this.listeMembres.add(m);
                }
            }
        }
        catch (Exception e) { System.out.println("[Association] : L'inscription a échouée : "+ e.toString()); }
        return m;
    }

    /**
     * Permet de désinscrire un membre.
     * @param m Le membre considéré.
     */
    public void desinscriptionMembre(Membre m) {
        try {
            if (m == null) { throw new Exception("Membre inexistant."); }
            else {
                if (this.listeMembres.contains(m)) {
                    this.listeMembres.remove(m);
                    this.addNbMembre(-1);

                    // TODO avec les CR.
                    /*
                    for (int i = 0; i < listeArbres.size(); i++) {
                        listeArbres.get(i).masquer(m);
                    }
                     */

                    // retirer les votes
                    if (m.getNbVotesRestants() < m.nbVotesMax) {
                        for (int i = 0; i < m.listeArbresVotes.size(); i++) {
                            m.listeArbresVotes.get(i).removeNbVotes();
                        }
                    }

                    // si président
                    if (m.isPresident()) {
                        try {
                            this.listeMembres.get(0).setPresident();
                        } catch (Exception e) {
                            // il n'y a plus de personnes dans l'association...
                        }
                    }

                    // si besoin de retirer autre choses.

                } else {
                    // normalement impossible
                    throw new Exception("Membre inexistant.");
                }
            }
        }
        catch (Exception e) { System.out.println("[Association] : La désinscription a échoué : " + e.toString()); }
    }

    /**
     * Permet de nommer un nouveau président d'association.
     * @param m Le futur président de l'association.
     */
    public void nommerPresident(Membre m) {
        if (m != null && !m.equals(this.getActualPresident())) {
            this.getActualPresident().resetPresident();
            m.setPresident();
        }
    }


    /*
        TODO pour les visites, l'ancienneté peut être obtenue/changée par getDateDerniereVisite/setDateDerniereVisite
     */

    /*
        TODO à la fin de chaque visite, la liste des CR est listeCR dans ArbreAssociation + dans le membre qui a fait la visite
     */

    /*
        TODO le nombre de visites max : (static) nbVisitesMax,
         les méthodes : setNbVisites, getNbVisites, removeNbVisites
     */

    /*
        TODO un objet CR
         + ce serait sympa s'il y avait une méthode toString qui te rédige le cr
     */

    // todo : savoir si l'arbre est vivant ou pas -> le retirer de la liste des AA. (à appeler à chaque notification).

    /**
     * Renvoie le membre associé aux attributs d'une personne.
     * @param nom Le nom de la personne.
     * @param prenom Le prénom de la personne.
     * @param dateNaissance La date de naissance de la personne au format DD/MM/AAAA.
     * @param adresse L'adresse de la personne.
     * @return Le membre si existant, null sinon.
     */
    protected Membre getMembreByPersonne(String nom, String prenom, String dateNaissance, String adresse) {
        Personne p = new Personne(nom, prenom, date.stringToDate(dateNaissance), adresse);

        for (Membre m : this.listeMembres) {
            if (m.getPersonne().equals(p)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Renvoie l'arbre de l'association dont l'arbre associé possède l'id passé en paramètre.
     * @param id L'id de l'arbre considéré.
     * @return L'arbre de l'association s'il existe, null sinon.
     */
    protected ArbreAssociation getArbreById(int id) {
        for (ArbreAssociation aa : this.listeArbres) {
            if (aa.getArbre().getId() == id) {
                return aa;
            }
        }
        return null;
    }

    /**
     * Affiche la liste des ids des arbres de l'association.
     */
    public void afficherArbreAssociation() {
        if (this.listeArbres.size() > 0) {
            System.out.println("[Association] : Affichage de la liste d'id des Arbres.");
            for (ArbreAssociation aa : this.listeArbres) {
                System.out.println("  " + aa.getArbre().getId());
            }
        }
        else { System.out.println("[Association] : La liste des arbres est vide."); }
    }

    /**
     * Renvoie le président de l'association.
     * @return Le membre dont l'attribut président est à VRAI, null si inexistant.
     */
    protected Membre getActualPresident() {
        for(int i = 0; i < this.nbMembres; i++) {
            if (this.listeMembres.get(i).isPresident()) {
                return this.listeMembres.get(i);
            }
        }
        return null;
    }

    /**
     * Affiche la liste des informations des membres de l'association.
     */
    public void afficherMembres() {
        System.out.println("[Association] : Liste des " + this.getNbMembres() + " membre(s) de l'association.");
        for(int i = 0; i < this.getNbMembres(); i++) {
            System.out.println(this.getMembre(i).toString());
        }
    }

    // Constructeur

    /**
     * Initialise une association avec la liste des arbres de la ville.
     * @param lA Une liste d'arbres.
     */
    public Association(ArrayList<Arbre> lA) {
        for (Arbre arbre : lA) {
            // todo mettre ici les critères.
            this.listeArbres.add(new ArbreAssociation(arbre));
        }
    }
}
