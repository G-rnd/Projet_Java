package application;

import java.util.Scanner;

/**
 * Interface textuelle.
 */
public class Application {

    /**
     * Affiche le menu de l'application.
     */
    public static void afficherMenu() {
        System.out.println("\n## Menu de l'application ##\n" +
                "  Actions Membres :\n"+
                "    [ 1] : Inscription d'un Membre\n" +
                "    [ 2] : Désinscription d'un Membre\n" +
                "    ( 3) : Afficher liste des Membres\n" +
                "    [ 4] : Payer Cotisation\n" +
                "  Actions vote Arbres d'un Membre :\n" +
                "    ( 5) : Voter pour un Arbre\n" +
                "    ( 6) : Afficher liste des votes\n" +
                "    ( 7) : Retirer un vote\n" +
                "    ( 8) : Supprimer les votes d'un Membre.\n" +
                "    ( 9) : Remplacer un vote\n" +
                "  Actions Arbres :\n" +
                "    (10) : Afficher la liste des Arbres\n" +
                // ...
                "  Actions Administration :\n" +
                "    (11) : Nommer nouveau Président\n" +
                // ...
                "\n    ( 0) : Quitter l'application\n" +
                " - Veuillez saisir une action. -");
    }

    /**
     * Lance l'action saisie au clavier.
     * @param nb L'id de l'action à affectuer.
     * @param a L'association associée.
     * @param scanner
     * @return Un booléen pour interrompre l'application ou non.
     *
     */
    public static boolean action(int nb, Association a, Scanner scanner) {
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
            default:
                System.out.println("[Application] : Action non reconnue.");
                return true;
        }
        return true;
    }

    /**
     * Renvoie un membre de l'association s'il existe.
     * @param a L'association associée.
     * @param scanner
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
            System.out.println("Saisir l'adresse de "+ nom + " " + prenom + ".");
            String adresse = scanner.nextLine();

            return a.getMembreByPersonne(nom, prenom, dateNaissance, adresse);
        }
        catch (Exception e) { return null; }
    }


    // -*- Méthodes obligatoires.
    // 1. Inscription d’un nouveau membre et désinscription d’un membre existant.

    /**
     * Lance l'action inscription.
     * @param a L'association associée.
     * @param scanner
     */
    public static void inscriptionMembre(Association a, Scanner scanner) {
        try {
            System.out.println("Saisir le nom de la personne.");
            String nom = scanner.nextLine();
            System.out.println("Saisir le prénom Mr ou Mme " + nom + ".");
            String prenom = scanner.nextLine();
            System.out.println("Saisir la date de naissance de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateNaissance = scanner.nextLine();
            System.out.println("Saisir l'adresse de "+ nom + " " + prenom + ".");
            String adresse = scanner.nextLine();
            System.out.println("Saisir la date d'inscription de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateInscription = scanner.nextLine();

            Membre m = a.inscrire(new Personne(nom,prenom, date.stringToDate(dateNaissance),adresse),
                    date.stringToDate(dateInscription));
            if (m != null) {
                if (a.getNbMembres() == 1) { m.setPresident(); }
                System.out.println("[Application] : Inscription réussie :\n" + m.toString());
            }
            else throw new Exception();
        }
        catch (Exception e) {
            System.out.println("[Application] : L'ajout d'un nouveau membre a échoué ...");
        }
    }

    /**
     * Lance l'action disinscription.
     * @param a L'association associée.
     * @param scanner
     */
    public static void desinscriptionMembre(Association a, Scanner scanner) {
        try {
            a.desinscriptionMembre(getMembreByScanner(a, scanner));
        }
        catch (Exception e) {
            System.out.println("[Application] : La suppression du membre a échoué ...");
        }
    }

    // 2. Recette de la cotisation d’un membre.

    /**
     * Lance l'action payer
     * @param a L'association associée.
     * @param scanner
     */
    public static void payerCotisation(Association a, Scanner scanner) {
        try {
            if (getMembreByScanner(a, scanner).payer()) {
                // TODO ajouter au paiement global (a.getMontantCotisation()).
                System.out.println("[Application] : Le paiement effectué.");
            }
        }
        catch (Exception e) {
            System.out.println("[Application] : Le paiement a échoué ...");
        }
    }

    // 3. Paiement d’une facture reçue.

    // 4. Ajout d’un nouveau donateur et suppression d’un donateur existant.

    // 5. Ajout d’une programmation de visite d’arbre remarquable et réception d’un compte-rendu pour
    //    une visite programmée.

    // 6. Vote d’un membre en faveur de la reconnaissance d’un arbre remarquable.

    /**
     * Lance l'action voter en faveur de la reconnaissance d'un arbre remarquable.
     * @param a L'association associée.
     * @param scanner
     */
    public static void ajouterVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);

            System.out.println("[Application] : Saisir l'identifiant de l'arbre.");

            m.voterArbre(a.getArbreById(scanner.nextInt()));
        }
        catch (Exception e) { System.out.println("[Application] : Le vote a échoué ."); }
    }

    // 7. Réception de notifications concernant la plantation ou l’abattage d’arbres ou la classification en
    //    arbre remarquable.

    // -*- Méthodes facultatives.

    /**
     * Affiche la liste des informations des membres.
     * @param a L'association associée.
     */
    public static void afficherMembres(Association a) {
        a.afficherMembres();
    }

    /**
     * Affiche la liste des id des arbres de l'association.
     * @param a L'association associée.
     */
    public static void afficherArbresAssociation(Association a) {
        a.afficherArbreAssociation();
    }

    /**
     * Affiche la liste des votes d'un membre de l'association.
     * @param a L'association associée.
     * @param scanner
     */
    public static void afficherVoteArbreListe(Association a, Scanner scanner) {
        try {
            getMembreByScanner(a, scanner).afficherVote();
        }
        catch (Exception e) {
            System.out.println("[Application] : L'affichage des votes du membre a échoué ...");
        }
    }

    /**
     * Lance l'action retirer un vote d'un membre.
     * @param a L'association associée.
     * @param scanner
     */
    public static void retirerVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);

            System.out.println("Saisir l'identifiant de l'arbre.");
            int idArbre = scanner.nextInt();

            m.retirerVoteArbre(idArbre);
        }
        catch (Exception e) {
            System.out.println("[Application] : Le retrait du vote a échoué ...");
        }
    }

    /**
     * Lance l'action remplacer un vote d'un membre.
     * @param a L'association associée.
     * @param scanner
     */
    public static void remplacerVoteArbre(Association a, Scanner scanner) {
        try {
            Membre m = getMembreByScanner(a, scanner);
            System.out.println("Saisir l'identifiant de l'ancien vote.");
            int idAncien = scanner.nextInt();
            System.out.println("Saisir l'identifiant du nouveau vote.");
            int idNouveau = scanner.nextInt();

            m.remplacerVoteArbre(a,idAncien,idNouveau);
        }
        catch (Exception e) {
            System.out.println("[Application] : Le changement de vote a échoué ...");
        }
    }

    /**
     * Lance l'action effacer la liste des votes d'un membre.
     * @param a L'association associée.
     * @param scanner
     */
    public static void resetVoteArbre(Association a, Scanner scanner) {
        try {
            getMembreByScanner(a, scanner).resetVote();
        }
        catch (Exception e) {
            System.out.println("[Application] : La suppression des votes a échoué ...");
        }
    }

    /**
     * Lance l'action nommer un nouveau président de l'association.
     * @param a L'association associée.
     * @param scanner
     */
    public static void nommerPresident(Association a, Scanner scanner) {
        try {
            a.nommerPresident(getMembreByScanner(a, scanner));
        }
        catch (Exception e) {
            System.out.println("[Application] : Le changement de président a échoué ...");
        }
    }

    /**
     * Programme principal.
     * @param a L'association associée.
     */
    public static void main(Association a) {
        System.out.println("\n--*-- Début de l'application --*--\n  Les actions [] sont les contraintes");
        System.out.println("## Association d'amateurs d'arbres ##");
        System.out.println("--*-------------------------------*--\n");
        boolean ok = true;
        try {
            Scanner scanner = new Scanner(System.in);
            boolean affiche = true;
            while(ok) {
                if (affiche) {
                    Application.afficherMenu();
                    affiche = false;
                }
                // affichage interface choix.
                String lu = scanner.nextLine();
                try {
                    int res = Integer.parseInt(lu);
                    affiche = true;
                    ok = Application.action(res, a, scanner);

                }
                catch (Exception e) {
                    System.out.println("[Application] : Erreur : Saisie incorrecte");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("--*-- Fin de l'application --*--");
    }
}
