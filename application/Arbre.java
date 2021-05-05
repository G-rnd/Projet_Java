package application;

public class Arbre {
    private String genre;
    private String espece;
    private String nomCommun;
    private int circonference;
    private double hauteur;
    private String stadeDeveloppement;
    private String adresse;
    private double[] localisationGPS = new double[2];

    public Arbre(String genre, String espece, String nomCommun, int circonference, double hauteur,
                 String stadeDeveloppement, String adresse, double[] localisationGPS) {
        try {
            if (localisationGPS.length != 2) {
                throw new ExceptionInInitializerError("Localisation incorrecte.");
            }
            this.localisationGPS = localisationGPS;

            this.genre = genre;
            this.espece = espece;
            this.nomCommun = nomCommun;
            this.circonference = circonference;
            this.hauteur = hauteur;
            this.stadeDeveloppement = stadeDeveloppement;
            this.adresse = adresse;
        }
        catch (ExceptionInInitializerError e) {
            System.out.println("Erreur : Localisation incorrecte.");
        }
    }

    public static void main(String[] args) {
    }
}
