package application;

import data.FileReader;

import java.io.File;
import java.util.ArrayList;

/**
 * Main.
 */
public class Main {
    /**
     * Fonction Principale du projet.
     *
     * @param args La liste des arguments : [0]: La source du fichier csv.
     *             [1]: Affiche le test avec l'argument test.
     *             [2]: Affiche seulement le test avec l'argument only.
     */
    public static void main(String[] args) {
        // techniquement déclarer dans espaces verts
        ArrayList<Arbre> listeArbres = null;
        if (args.length > 0) {
            File tempFile = new File(args[0]);
            if (tempFile.exists()) {
                System.out.println("[Main] : Lecture du fichier " + args[0] + " ...");

                //We start by reading the CSV file
                listeArbres = FileReader.getDataFromCSVFile(args[0]);

                System.out.println("[Main] : Fin du fichier " + args[0] + ".");
            } else {
                System.out.println("[Main] Aucun fichier trouvé.");
            }
        } else {
            System.out.println("[Main] Aucun argument trouvé.");
        }

        if (listeArbres != null) {
            Municipalite municipalite = new Municipalite("Mairie de Paris", listeArbres);
            Association association = new Association(listeArbres, 20, 40);
            municipalite.ajouterEntiteNotifiable(association);

            ArrayList<Donateur> listeDonateurs = new ArrayList<>();
            listeDonateurs.add(municipalite);

            // l'argument 2 est pour le debug
            try {
                if (args[1].equals("test")) {
                    Test.main(association);
                }
            } catch (Exception e) { /* pas de debug. */ }
            try {
                if (!args[2].equals("only")) {
                    // lancement de l'application.
                    Application.main(association, municipalite, listeDonateurs);
                }
            } catch (Exception e) {
                Application.main(association, municipalite, listeDonateurs);
            }
        } else {
            System.out.println("[Main] : Aucun Arbre n'a été lu.");
        }
    }
}
