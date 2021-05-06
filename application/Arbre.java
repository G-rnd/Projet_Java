package application;

public class Arbre {
    private int id;
    private String genre;
    private String espece;
    private String nomCommun;
    private int circonference;
    private double hauteur;
    private String stadeDeveloppement;
    private String adresse;
    private double[] localisationGPS = new double[2];

    private boolean estRemarquable;
    private int[] dateRemarquable = new int[] {0, 0, 0};

    protected int getCirconference() { return this.circonference; }
    protected double getHauteur() { return this.hauteur; }

    protected boolean isEstRemarquable() { return this.estRemarquable; }
    protected int[] getDateRemarquable() { return this.dateRemarquable.clone(); }
    public void rendreRemarquable(int[] date) {
        if (application.date.estValide(date)){
            this.dateRemarquable = date;
            this.estRemarquable = true;
        }
        else {
            System.out.println("L'arbre n'a pas pu être rendu remarquable.");
        }
    }
    public Arbre(int id, String genre, String espece, String nomCommun, int circonference, double hauteur,
                 String stadeDeveloppement, String adresse, double[] localisationGPS, boolean remarquable) {
        try {
            if (localisationGPS.length != 2) {
                throw new ExceptionInInitializerError("Localisation incorrecte.");
            }
            this.localisationGPS = localisationGPS;

            this.id = id;
            this.genre = genre;
            this.espece = espece;
            this.nomCommun = nomCommun;
            this.circonference = circonference;
            this.hauteur = hauteur;
            this.stadeDeveloppement = stadeDeveloppement;
            this.adresse = adresse;
            this.estRemarquable = remarquable;
        }
        catch (ExceptionInInitializerError e) {
            System.out.println("Erreur : Localisation incorrecte.");
        }
    }

    public static void main(String[] args) {
    }
}
