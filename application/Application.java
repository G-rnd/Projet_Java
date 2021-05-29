package application;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Interface textuelle.
 */
public class Application {

    /**
     * Affiche le menu de l'application.
     */
    public static void afficherMenu() {
        System.out.println(
                """

                        ## Menu de l'application ##
                          Actions Membres :
                            [ 1] : Inscription d'un Membre
                            [ 2] : Désinscription d'un Membre
                            ( 3) : Afficher liste des Membres
                            [ 4] : Payer Cotisation
                          Actions vote Arbres d'un Membre :
                            [ 5] : Voter pour un Arbre
                            ( 6) : Afficher liste des votes
                            ( 7) : Retirer un vote
                            ( 8) : Supprimer les votes d'un Membre.
                            ( 9) : Remplacer un vote
                          Actions Arbres :
                            (10) : Afficher la liste des Arbres
                          Actions Administration :
                            (11) : Nommer nouveau Président
                            [12] : Payer une facture
                            (13) : Afficher le point budgétaire
                            [14] : Ajouter un donateur
                            [15] : Retirer un donateur
                          Actions Mairie de Paris :
                            [16] : Planter un arbre
                            [17] : Abattre un arbre
                            [18] : Classifier un arbre

                            ( 0) : Quitter l'application
                         - Veuillez saisir une action. -"""
        );
    }

    /**
     * Lance l'action saisie au clavier.
     *
     * @param nb      L'id de l'action à affectuer.
     * @param a       L'association associée.
     * @param scanner -
     * @return Un booléen pour interrompre l'application ou non.
     */
    public static boolean action(int nb, Association a, Municipalite municipalite, ArrayList<Donateur> listeDonateurs, Scanner scanner) {
        switch (nb) {
            case 0:
                return false;
            case 1:
                Application.inscriptionMembre(a, scanner);
                break;
            case 2:
                Application.desinscriptionMembre(a, scanner);
                break;
            case 3:
                Application.afficherMembres(a);
                break;
            case 4:
                Application.payerCotisation(a, scanner);
                break;
            case 5:
                Application.ajouterVoteArbre(a, scanner);
                break;
            case 6:
                Application.afficherVoteArbreListe(a, scanner);
                break;
            case 7:
                Application.retirerVoteArbre(a, scanner);
                break;
            case 8:
                Application.resetVoteArbre(a, scanner);
                break;
            case 9:
                Application.remplacerVoteArbre(a, scanner);
                break;
            case 10:
                Application.afficherArbresAssociation(a);
                break;
            case 11:
                Application.nommerPresident(a, scanner);
                break;
            case 12:
                Application.payerFacture(a, scanner);
                break;
            case 13:
                Application.afficherPointBudgetaire(a);
                break;
            case 14:
                Application.ajouterDonateur(a, listeDonateurs, scanner);
                break;
            case 15:
                Application.retirerDonateur(a, listeDonateurs, scanner);
                break;
            case 16:
                Application.planterArbre(municipalite, scanner);
                break;
            case 17:
                Application.abattreArbre(municipalite, scanner);
                break;
            case 18:
                Application.classifierArbre(municipalite, scanner);
                break;
            default:
                System.out.println("[Application] : Action non reconnue.");
                return true;
        }
        return true;
    }

    /**
     * Renvoie un membre de l'association s'il existe.
     *
     * @param a       L'association associée.
     * @param scanner -
     * @return Le membre de l'association si existant, null sinon.
     */
    public static Membre getMembreByScanner(Association a, Scanner scanner) {
        try {
            System.out.println("Saisir le nom de la personne.");
            String nom = scanner.nextLine();
            System.out.println("Saisir le prénom Mr ou Mme " + nom + ".");
            String prenom = scanner.nextLine();
            System.out.println("Saisir la date de naissance de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateNaissance = scanner.nextLine();
            System.out.println("Saisir l'adresse de " + nom + " " + prenom + ".");
            String adresse = scanner.nextLine();

            return a.getMembreByPersonne(nom, prenom, dateNaissance, adresse);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Permet d'obtenir un arbre dans la liste des arbres vivants d'une municipalité par son id.
     *
     * @param municipalite La municipalité associée.
     * @param scanner      -
     * @return L'arbre s'il existe.
     */
    public static Arbre getArbreById(Municipalite municipalite, Scanner scanner) {
        try {
            System.out.println("Saisir l'id de l'arbre.");
            int id = scanner.nextInt();

            for (Arbre a : municipalite.getListeArbres())
                if (a.getId() == id)
                    return a;
            return null;
        } catch (Exception e) {
            System.out.println("[Application] : L'arbre n'a pas pu être généré par le scanner");
            return null;
        }
    }

    // -*- Méthodes obligatoires.
    // 1. Inscription d’un nouveau membre et désinscription d’un membre existant.

    /**
     * Lance l'action inscription.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void inscriptionMembre(Association a, Scanner scanner) {
        try {
            System.out.println("Saisir le nom de la personne.");
            String nom = scanner.nextLine();
            System.out.println("Saisir le prénom Mr ou Mme " + nom + ".");
            String prenom = scanner.nextLine();
            System.out.println("Saisir la date de naissance de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateNaissance = scanner.nextLine();
            System.out.println("Saisir l'adresse de " + nom + " " + prenom + ".");
            String adresse = scanner.nextLine();
            System.out.println("Saisir la date d'inscription de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateInscription = scanner.nextLine();

            Membre m = a.inscrire(new Personne(nom, prenom, date.stringToDate(dateNaissance), adresse),
                    date.stringToDate(dateInscription));
            if (m != null) {
                if (a.getNbMembres() == 1) {
                    m.setPresident();
                }
                System.out.println("[Application] : Inscription réussie :\n" + m.toString());
            } else throw new Exception();
        } catch (Exception e) {
            System.out.println("[Application] : L'ajout d'un nouveau membre a échoué ...");
        }
    }

    /**
     * Lance l'action disinscription.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void desinscriptionMembre(Association a, Scanner scanner) {
        try {
            a.desinscriptionMembre(getMembreByScanner(a, scanner));
        } catch (Exception e) {
            System.out.println("[Application] : La suppression du membre a échoué ...");
        }
    }

    // 2. Recette de la cotisation d’un membre.

    /**
     * Lance l'action payer
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void payerCotisation(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);
            assert m != null;
            a.payerCotisation(m);
        } catch (Exception e) {
            System.out.println("[Application] : Le paiement a échoué ...");
        }
    }

    // 3. Paiement d’une facture reçue.

    /**
     * Paiement d'une facture
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void payerFacture(Association a, Scanner scanner) {
        try {
            System.out.println("Liste des factures à payer :\n" +
                    Facture.listeFacturesToString(a.getListeFactures(), "  * "));
            System.out.println("Saisir le nom de la personne ou entité à payer.");
            String entite = scanner.nextLine();
            ArrayList<Facture> liste = Facture.getFactures(a.getListeFactures(), entite);
            if (liste.size() == 0)
                System.out.println("[Application] : Aucune facture trouvée au nom de " + entite + ".");
            else if (liste.size() == 1)
                a.payerFacture(liste.get(0));
            else {
                System.out.println("Plusieurs factures ont été trouvés au nom : " + entite + ".");
                for (Facture f : liste)
                    System.out.println("  * " + f.toString());
                System.out.println("Saisir le montant de la facture.\n");
                double montant = scanner.nextDouble();
                liste = Facture.getFactures(a.getListeFactures(), montant);
                if (liste.size() == 0)
                    System.out.println("[Application] : Aucune facture au nom de " + entite
                            + " possède un montant de " + montant);
                else
                    a.payerFacture(liste.get(0));
            }

        } catch (Exception e) {
            System.out.println("[Application] : La recherche de facture a echouée ...");
        }
    }

    // 4. Ajout d’un nouveau donateur et suppression d’un donateur existant.

    /**
     * Ajout d'un donateur à l'association.
     *
     * @param a              L'association associée.
     * @param listeDonateurs La liste des donateurs potentiels.
     * @param scanner        -
     */
    public static void ajouterDonateur(Association a, ArrayList<Donateur> listeDonateurs, Scanner scanner) {
        try {
            if (a.getListeDonateurs().containsAll(listeDonateurs))
                System.out.println("[Application] : L'association demande déjà à tous les donateurs de la financer.");
            else {
                System.out.println("[Application] : Saisir le nom du donateur à ajouter parmi la liste suivante.");
                for (Donateur d : listeDonateurs)
                    System.out.println("  * " + d.getNom());
                boolean res = false;
                String nom = scanner.nextLine();
                for (Donateur d : listeDonateurs)
                    if (d.getNom().equals(nom)) {
                        if (a.getListeDonateurs().contains(d)) {
                            System.out.println("[Application] : " + nom + " est déjà un donateur.");
                        } else {
                            a.ajouterDonateur(d);
                            res = true;
                        }
                    }
                if (res) {
                    System.out.println("[Application] : " + nom + " est maintenant un donateur.");
                } else
                    System.out.println("[Application] : " + nom + " n'est pas un donateur.");
            }
        } catch (Exception e) {
            System.out.println("[Application] : L'ajout a échoué.");
        }
    }

    /**
     * Suppression d'un donateur de l'association.
     *
     * @param a              L'association associée.
     * @param listeDonateurs La liste des donateurs potentiels.
     * @param scanner        -
     */
    public static void retirerDonateur(Association a, ArrayList<Donateur> listeDonateurs, Scanner scanner) {
        try {
            if (a.getListeDonateurs().size() == 0)
                System.out.println("[Application] : L'association ne possède aucun donateurs.");
            else {
                System.out.println("[Application] : Saisir le nom du donateur à supprimer parmi la liste suivante.");
                for (Donateur d : listeDonateurs)
                    System.out.println("  * " + d.getNom());
                boolean res = false;
                System.out.println("[Application] : Saisir le nom du donateur à retirer.");
                String nom = scanner.nextLine();
                for (Donateur d : listeDonateurs)
                    if (d.getNom().equals(nom)) {
                        a.retirerDonateur(d);
                        res = true;
                    }
                if (res) {
                    System.out.println("[Application] : " + nom + " n'est maintenant plus un donateur.");
                } else {
                    System.out.println("[Application] : " + nom + " n'est pas un donateur.");
                }
            }
        } catch (Exception e) {
            System.out.println("[Application] : La suppression a échouée .");
        }
    }

    // 5. Ajout d’une programmation de visite d’arbre remarquable et réception d’un compte-rendu pour
    //    une visite programmée.

    /**
     * Lance la visite d'un arbre remarquable.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void visiteArbreRemarquable(Association a, Scanner scanner) {
        // todo
        System.out.println("En cours ...");
    }

    // 6. Vote d’un membre en faveur de la reconnaissance d’un arbre remarquable.

    /**
     * Lance l'action voter en faveur de la reconnaissance d'un arbre remarquable.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void ajouterVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);

            System.out.println("[Application] : Saisir l'identifiant de l'arbre.");

            assert m != null;
            m.voterArbre(a.getArbreById(scanner.nextInt()));
        } catch (Exception e) {
            System.out.println("[Application] : Le vote a échoué .");
        }
    }

    // 7. Réception de notifications concernant la plantation ou l’abattage d’arbres ou la classification en
    //    arbre remarquable.

    /**
     * Lance l'action planter un arbre de la municipalité.
     *
     * @param municipalite La municipalité associée.
     * @param scanner      -
     */
    public static void planterArbre(Municipalite municipalite, Scanner scanner) {
        try {
            System.out.println("Saisir l'id de l'arbre.");
            int id = scanner.nextInt();

            System.out.println("Saisir le genre de l'arbre.");
            String genre = scanner.next();

            System.out.println("Saisir l'espèce de l'arbre.");
            String espece = scanner.next();

            System.out.println("Saisir le nom commun de l'arbre.");
            String nomCommun = scanner.next();

            System.out.println("Saisir la circonférence de l'arbre en cm.");
            int circonference = scanner.nextInt();

            System.out.println("Saisir la hauteur de l'arbre en m.");
            double hauteur = scanner.nextInt();

            System.out.println("Saisir le stade de développement de l'arbre.");
            String stadeDeveloppement = scanner.next();

            System.out.println("Saisir l'adresse de l'arbre.");
            String adresse = scanner.next();

            double[] localisationGPS = {0., 0.};
            System.out.println("Saisir latitude de l'arbre.");
            localisationGPS[0] = scanner.nextDouble();
            System.out.println("Saisir latitude de l'arbre.");
            localisationGPS[1] = scanner.nextDouble();

            municipalite.planterArbre(new Arbre(id, genre, espece, nomCommun, circonference,
                    hauteur, stadeDeveloppement, adresse, localisationGPS, false));
        } catch (Exception e) {
            System.out.println("[Application] : L'arbre n'a pas pu être généré par le scanner");
        }
    }

    /**
     * Lance l'abattage d'un arbre.
     *
     * @param municipalite La municipalité associée.
     * @param scanner      -
     */
    public static void abattreArbre(Municipalite municipalite, Scanner scanner) {
        municipalite.abattreArbre(getArbreById(municipalite, scanner));
    }

    /**
     * Lance la classification d'un arbre.
     *
     * @param municipalite La municipalité associée.
     * @param scanner      -
     */
    public static void classifierArbre(Municipalite municipalite, Scanner scanner) {
        municipalite.classifierArbre(getArbreById(municipalite, scanner));
    }

    // -*- Méthodes facultatives.

    /**
     * Affiche la liste des informations des membres.
     *
     * @param a L'association associée.
     */
    public static void afficherMembres(Association a) {
        a.afficherMembres();
    }

    /**
     * Affiche la liste des id des arbres de l'association.
     *
     * @param a L'association associée.
     */
    public static void afficherArbresAssociation(Association a) {
        a.afficherArbreAssociation();
    }

    /**
     * Affiche la liste des votes d'un membre de l'association.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void afficherVoteArbreListe(Association a, Scanner scanner) {
        try {
            Objects.requireNonNull(getMembreByScanner(a, scanner)).afficherVote();
        } catch (Exception e) {
            System.out.println("[Application] : L'affichage des votes du membre a échoué ...");
        }
    }

    /**
     * Lance l'action retirer un vote d'un membre.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void retirerVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);

            System.out.println("Saisir l'identifiant de l'arbre.");
            int idArbre = scanner.nextInt();

            assert m != null;
            m.retirerVoteArbre(idArbre);
        } catch (Exception e) {
            System.out.println("[Application] : Le retrait du vote a échoué ...");
        }
    }

    /**
     * Lance l'action remplacer un vote d'un membre.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void remplacerVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);
            System.out.println("Saisir l'identifiant de l'ancien vote.");
            int idAncien = scanner.nextInt();
            System.out.println("Saisir l'identifiant du nouveau vote.");
            int idNouveau = scanner.nextInt();

            assert m != null;
            m.remplacerVoteArbre(a, idAncien, idNouveau);
        } catch (Exception e) {
            System.out.println("[Application] : Le changement de vote a échoué ...");
        }
    }

    /**
     * Lance l'action effacer la liste des votes d'un membre.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void resetVoteArbre(Association a, Scanner scanner) {
        try {
            Objects.requireNonNull(getMembreByScanner(a, scanner)).resetVote();
        } catch (Exception e) {
            System.out.println("[Application] : La suppression des votes a échoué ...");
        }
    }

    /**
     * Lance l'action nommer un nouveau président de l'association.
     *
     * @param a       L'association associée.
     * @param scanner -
     */
    public static void nommerPresident(Association a, Scanner scanner) {
        try {
            a.nommerPresident(getMembreByScanner(a, scanner));
        } catch (Exception e) {
            System.out.println("[Application] : Le changement de président a échoué ...");
        }
    }

    /**
     * Affiche le bugdet actuel d'une association.
     *
     * @param a L'association associée.
     */
    public static void afficherPointBudgetaire(Association a) {
        System.out.println(a.getPointBudgetaire());
    }

    /**
     * Programme Principal.
     *
     * @param a              L'association associée.
     * @param municipalite   La municipalité associée.
     * @param listeDonateurs La liste des donateurs potentiels.
     */
    public static void main(Association a, Municipalite municipalite, ArrayList<Donateur> listeDonateurs) {
        System.out.println("\n--*-- Début de l'application --*--\n  Les actions [] sont les contraintes");
        System.out.println("## Association d'amateurs d'arbres ##");
        System.out.println("--*-------------------------------*--\n");
        boolean ok = true;
        try {
            Scanner scanner = new Scanner(System.in);
            boolean affiche = true;
            while (ok) {
                if (affiche) {
                    Application.afficherMenu();
                    affiche = false;
                }
                // affichage interface choix.
                String lu = scanner.nextLine();
                try {
                    int res = Integer.parseInt(lu);
                    affiche = true;
                    ok = Application.action(res, a, municipalite, listeDonateurs, scanner);

                } catch (Exception e) {
                    System.out.println("[Application] : Erreur : Saisie incorrecte");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("--*-- Fin de l'application --*--");
    }
}
