package application;

import java.util.Scanner;

public class Application {
    public static void afficherMenu() {
        System.out.println("Menu de l'application\n" +
                "  Actions Membres :\n"+
                "    ( 0) : Ajouter Membre\n" +
                "    ( 1) : Retirer Membre\n" +
                "    ( 2) : Afficher liste des Membres\n" +
                "    ( 3) : Payer Cotisation\n" +
                "  Actions Arbres :\n" +
                "    ( 4) : Voter pour un Arbre\n" +
                // ...
                "  Actions Administration :\n" +
                "    ( 5) : Nommer nouveau Président\n" +
                // ...
                "\n    (-1) : Quitter l'application\n" +
                " - Veuillez saisir une action. -");
    }
    public static boolean action(int nb, Association a, Scanner scanner) {
        switch (nb) {
            case 0:
                Application.ajouterMembre(a, scanner);
                break;
            case 1:
                Application.retirerMembre(a, scanner);
                break;
            case 2:
                Application.afficherMembres(a);
                break;
            case 3:
                Application.payerCotisation(a, scanner);
                break;
            case 4:
                Application.voterArbre(a, scanner);
                break;
            case 5:
                Application.nommerPresident(a, scanner);
                break;

            case -1:
                return false;
        }
        return true;
    }

    public static void ajouterMembre(Association a, Scanner scanner) {
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
            System.out.println("Saisir le montant de la cotisation.");
            double cotisation = scanner.nextDouble();

            System.out.println("Création du compte ...");
            Membre m = a.inscrire(new Personne(nom,prenom, date.stringToDate(dateNaissance),adresse),
                    date.stringToDate(dateInscription), cotisation);
            if (m != null) {
                if (a.getNbMembres() == 1) { m.setPresident(); }
                System.out.println("Inscription réussie :\n" + m.toString());
            }
            else throw new Exception();
        }
        catch (Exception e) {
            System.out.println("L'ajout d'un nouveau membre a échoué ...");
        }
    }
    public static void retirerMembre(Association a, Scanner scanner) {
        try {
            System.out.println("Saisir le nom de la personne.");
            String nom = scanner.nextLine();
            System.out.println("Saisir le prénom Mr ou Mme " + nom + ".");
            String prenom = scanner.nextLine();
            System.out.println("Saisir la date de naissance de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateNaissance = scanner.nextLine();
            System.out.println("Saisir l'adresse de "+ nom + " " + prenom + ".");
            String adresse = scanner.nextLine();

            System.out.println("Recherche du compte ...");
            Membre m = a.getMembreByPersonne(nom, prenom, dateNaissance, adresse);

            if (m != null) {
                a.exclure(m);
                System.out.println("Suppression réussie.");
            }
            else throw new Exception();
        }
        catch (Exception e) {
            System.out.println("La suppression du membre a échoué ...");
        }
    }
    public static void afficherMembres(Association a) {
        System.out.println("Liste des " + a.getNbMembres() + " membre(s) de l'association.");
        for(int i = 0; i < a.getNbMembres(); i++) {
            System.out.println(a.getMembre(i).toString());
        }
    }

    public static void payerCotisation(Association a, Scanner scanner) {
        try {
            System.out.println("Saisir le nom de la personne.");
            String nom = scanner.nextLine();
            System.out.println("Saisir le prénom Mr ou Mme " + nom + ".");
            String prenom = scanner.nextLine();
            System.out.println("Saisir la date de naissance de " + nom + " " + prenom + " au format DD/MM/AAAA.");
            String dateNaissance = scanner.nextLine();
            System.out.println("Saisir l'adresse de "+ nom + " " + prenom + ".");
            String adresse = scanner.nextLine();
            System.out.println("Saisir le montant de la cotisation.");
            double cotisation = scanner.nextDouble();

            System.out.println("Recherche du compte ...");
            Membre m = a.getMembreByPersonne(nom, prenom, dateNaissance, adresse);
            if (m != null && !m.isCotisation() && cotisation > 0) {
                m.payer(cotisation);
                System.out.println("Paiement annuel réussi.");
            }
            else {
                if (m.isCotisation()) {
                    System.out.println("Le paiement annuel a déjà été effectué : " + m.listeCotisations.getLast());
                }
                else {
                    throw new Exception();
                }
            }
        }
        catch (Exception e) {
            System.out.println("Le paiement a échoué ...");
        }
    }
    public static void voterArbre(Association a, Scanner scanner) {
        // TODO
    }
    public static void nommerPresident(Association a, Scanner scanner) {
        // TODO
    }

    public static void main(Association a) {
        System.out.println("\n--*-- Début de l'application --*--");
        System.out.println("## Association d'amateurs d'arbres ##");
        System.out.println("--*-------------------------------*--\n");
        boolean ok = true;
        try {
            Scanner scanner = new Scanner(System.in);
            while(ok) {
                Application.afficherMenu();
                // affichage interface choix.
                String lu = scanner.nextLine();
                try {
                    int res = Integer.parseInt(lu);
                    ok = Application.action(res, a, scanner);
                }
                catch (Exception e) {
                    System.out.println("Erreur : Saisie incorrecte");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("--*-- Fin de l'application --*--");
    }
}
