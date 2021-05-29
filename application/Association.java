package application;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Association.
 */
public class Association implements Donataire, NotificationArbre {
    public final double montantCotisation = 10.5;
    private final int seuilCirconference;
    private final double seuilHauteur;
    private final Budget budget = new Budget();
    private final ArrayList<Facture> listeFactures = new ArrayList<>();
    private final ArrayList<Membre> listeMembres = new ArrayList<>();
    private final ArrayList<ArbreAssociation> listeArbres = new ArrayList<>();
    private final ArrayList<Donateur> listeDonateurs = new ArrayList<>();
    private int nbMembres = 0;
    private float soldeTotal = 0;

    /**
     * Initialise une association avec la liste des arbres de la ville.
     *
     * @param lA Une liste d'arbres.
     */
    public Association(ArrayList<Arbre> lA, double seuilHauteur, int seuilCirconference) {
        this.seuilCirconference = seuilCirconference;
        this.seuilHauteur = seuilHauteur;
        for (Arbre arbre : lA)
            if (seuilArbreAssociation(arbre))
                this.listeArbres.add(new ArbreAssociation(arbre));
    }

    /**
     * Renvoie le montant de la cotisation annuelle de l'association.
     *
     * @return L'attribut montantCotisation de l'association.
     */
    public double getMontantCotisation() {
        return this.montantCotisation;
    }

    /**
     * Renvoie le nombre de membres de l'association.
     *
     * @return L'attribut nbMembres de l'association.
     */
    public int getNbMembres() {
        return this.nbMembres;
    }

    /**
     * Ajoute le nombre passé en paramètre au nombre de membres.
     *
     * @param n L'entier à ajouter au nombre de membres.
     */
    protected void addNbMembre(int n) {
        if (this.nbMembres + n > 0) {
            this.nbMembres += n;
        }
    }

    /**
     * Renvoie le i-ème membre de la liste des membres de l'association.
     *
     * @param i L'indice du membre considéré.
     * @return Le membre s'il existe, null sinon.
     */
    protected Membre getMembre(int i) {
        try {
            return this.listeMembres.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Renvoie un classement d'arbres candidats à la classification en tant qu'arbres remarquables.
     *
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
                    } else {
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
     *
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
     *
     * @param p    La personne considérée.
     * @param date la date considérée.
     * @return Le membre s'il est inscrit, null sinon.
     */
    public Membre inscrire(Personne p, int[] date) {
        Membre m = null;
        try {
            if (this.estInscrit(p)) {
                throw new Exception("La personne est déjà inscrite.");
            } else {
                if (application.date.estValide(date)) {
                    m = new Membre(p, date);
                    if (this.getNbMembres() == 0) {
                        m.setPresident();
                    }
                    this.addNbMembre(1);
                    this.listeMembres.add(m);
                    enregistrerCotisation(m);
                }
            }
        } catch (Exception e) {
            System.out.println("[Association] : L'inscription a échouée : " + e.toString());
        }
        return m;
    }

    /**
     * Permet de désinscrire un membre.
     *
     * @param m Le membre considéré.
     */
    public void desinscriptionMembre(Membre m) {
        try {
            if (m == null) {
                throw new Exception("Membre inexistant.");
            } else {
                if (this.listeMembres.contains(m)) {
                    this.listeMembres.remove(m);
                    this.addNbMembre(-1);

                    // masquer le nom dans les CRs
                    for (ArbreAssociation aa : listeArbres)
                        aa.masquer(m);

                    // masquer le nom dans les défraiements
                    m.masquerDefraiements();

                    // retirer les votes
                    if (m.getNbVotesRestants() < Membre.nbVotesMax) {
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
        } catch (Exception e) {
            System.out.println("[Association] : La désinscription a échoué : " + e.toString());
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

    /**
     * Permet de nommer un nouveau président d'association.
     *
     * @param m Le futur président de l'association.
     */
    public void nommerPresident(Membre m) {
        if (m != null && !m.equals(this.getActualPresident())) {
            this.getActualPresident().resetPresident();
            m.setPresident();
        }
    }

    /**
     * Renvoie le membre associé aux attributs d'une personne.
     *
     * @param nom           Le nom de la personne.
     * @param prenom        Le prénom de la personne.
     * @param dateNaissance La date de naissance de la personne au format DD/MM/AAAA.
     * @param adresse       L'adresse de la personne.
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
     *
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
        } else {
            System.out.println("[Association] : La liste des arbres est vide.");
        }
    }

    /**
     * Renvoie le président de l'association.
     *
     * @return Le membre dont l'attribut président est à VRAI, null si inexistant.
     */
    protected Membre getActualPresident() {
        for (int i = 0; i < this.nbMembres; i++) {
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
        for (int i = 0; i < this.getNbMembres(); i++) {
            System.out.println(this.getMembre(i).toString());
        }
    }

    /**
     * Ajoute un donateur à la liste des donateurs potentiels de l'association.
     *
     * @param donateur Le donateur associé.
     */
    public void ajouterDonateur(Donateur donateur) {
        listeDonateurs.add(donateur);
    }

    /**
     * Retire un donateur de la liste des donateurs potentiels de l'association.
     *
     * @param donateur Le donateur associé.
     */
    public void retirerDonateur(Donateur donateur) {
        listeDonateurs.remove(donateur);
    }

    /**
     * Retourne la liste des donateurs potentiels de l'association.
     *
     * @return La liste des donateurs potentiels de l'association.
     */
    public ArrayList<Donateur> getListeDonateurs() {
        return listeDonateurs;
    }

    @Override
    public void demanderDon(Donateur donateur, float montant, String message, String rapportActivite) {
        donateur.recevoirDemandeDon(this, montant, message, rapportActivite);
    }

    @Override
    public void recevoirDon(Donateur donateur, float montant) {
        if (montant > 0) {
            this.enregistrerDon(donateur.getNom(), montant);
            if (!listeDonateurs.contains(donateur))
                listeDonateurs.add(donateur);
        }
    }

    @Override
    public void notifierPlantation(Arbre arbre) {
        if (seuilArbreAssociation(arbre))
            listeArbres.add(new ArbreAssociation(arbre));
    }

    @Override
    public void notifierAbattage(Arbre arbre) {
        listeArbres.remove(getArbreById(arbre.getId()));
        for (Membre m : listeMembres)
            m.retirerVoteArbre(arbre.getId());
    }

    @Override
    public void notifierClassification(Arbre arbre, int[] dateClassification) {
        notifierClassification(arbre);
    }

    @Override
    public void notifierClassification(Arbre arbre) {
        for (Membre m : listeMembres)
            m.retirerVoteArbre(arbre.getId());
    }

    /**
     * Détermine si un arbre est au dessus des seuils acceptables pour l'association.
     *
     * @param arbre L'arbre considéré.
     * @return Un booléen à vrai si l'arbre est acceptable par l'association.
     */
    public boolean seuilArbreAssociation(Arbre arbre) {
        return arbre.getCirconference() > seuilCirconference || arbre.getHauteur() > seuilHauteur;
    }

    /**
     * Permet d'enregistrer un don dans le budget de l'association.
     *
     * @param donateur L'entité finançant l'association.
     * @param montant  Le montant de la transaction.
     */
    public void enregistrerDon(String donateur, float montant) {
        if (montant > 0) {
            soldeTotal += montant;
            budget.addRecette(new Operation(donateur, montant, true));
        } else
            System.out.println("[Association] : Montant du don négatif.");
    }

    /**
     * Permet d'enregister la cotisation d'un membre.
     *
     * @param membre Le membre considéré.
     */
    public void enregistrerCotisation(Membre membre) {
        soldeTotal += montantCotisation;
        Operation op = new Operation(membre.getPrenomNom(), montantCotisation, true);
        budget.addRecette(op);
        membre.ajouterDefraiement(op);
    }

    /**
     * Permet de payer une facture.
     *
     * @param facture La facture à payer.
     * @return Un booléen à vrai si la facture a été payée, à faux sinon.
     */
    public boolean payerFacture(Facture facture) {
        if (soldeTotal - facture.getMontant() > 0) {
            listeFactures.remove(facture);
            soldeTotal -= facture.getMontant();
            budget.addDepense(facture);
            return true;
        } else
            return false;
    }

    //todo à appeler en fin de visite

    /**
     * Permet d'enregistrer un défraiement dans le budget de l'association.
     *
     * @param membre  Le membre ayant touché le défraiement.
     * @param montant Le montant du défraiement.
     * @return Un booléen à vrai si le défraiement peut être effectué, à faux sinon.
     */
    public boolean enregistrerDefraiement(Membre membre, float montant) {
        if (soldeTotal - montant > 0) {
            soldeTotal -= montant;
            budget.addDepense(new Operation(membre.getPrenomNom(), montant, false));
            return true;
        } else
            return false;
    }

    /**
     * Retourne le budget de l'association.
     *
     * @return Le budget de l'association.
     */
    public String getPointBudgetaire() {
        return budget.toString();
    }

    // TODO bien adapter la classe CR

    /**
     * Retourne la liste de toutes les activités de l'année en cours de l'association.
     *
     * @return La liste de toutes les activités de l'année en cours de l'association.
     */
    public String getSyntheseActivites() {
        StringBuilder sb = new StringBuilder("# Synthèse des activités :\n");
        for (ArbreAssociation aa : listeArbres) {
            for (CompteRendu cr : aa.listeCR) {
                sb.append(cr.toString());
            }
        }
        sb.append("# Fin de la synthèse des activités.\n");
        return sb.toString();
    }

    /**
     * Retourne le rapport d'activité de l'année en cours de l'association.
     * Il comporte un point budgétaire et la liste de toutes les activités de l'année.
     *
     * @return Le rapport d'activité de l'association.
     */
    public String getRapportActivite() {
        return "## Rapport d'activité ##\n" + getPointBudgetaire() + getSyntheseActivites() +
                "## Fin du rapport d'activité.\n";
    }

    /**
     * Retourne la liste des factures à payer.
     *
     * @return La liste des factures à payer.
     */
    public ArrayList<Facture> getListeFactures() {
        return listeFactures;
    }

    /**
     * Effectue le paiement de la cotisation d'un membre.
     *
     * @param m Le membre considéré.
     */
    public void payerCotisation(Membre m) {
        if (m.payer()) {
            enregistrerCotisation(m);
            System.out.println("[Association] : Le paiement effectué.");
        }
    }
}
