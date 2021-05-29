package application;

/**
 * Arbre.
 */
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

    private boolean isDateRemarquable = false;
    private boolean estRemarquable;
    private int[] dateRemarquable = new int[]{0, 0, 0};

    /**
     * Constructeur de l'arbre.
     *
     * @param id                 L'id de l'arbre considéré.
     * @param genre              Le genre de l'arbre considéré.
     * @param espece             L'espèce de l'arbre considéré.
     * @param nomCommun          Le nom en français commun de l'arbre considéré.
     * @param circonference      La circonférence en cm de l'arbre considéré.
     * @param hauteur            La hauteur en m de l'arbre considéré.
     * @param stadeDeveloppement Le stade de développement de l'arbre considéré.
     * @param adresse            L'adresse de l'arbre considéré.
     * @param localisationGPS    La localisation GPS de l'arbre considéré.
     * @param remarquable        Le caractère remarquable de l'arbre considéré.
     */
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
        } catch (ExceptionInInitializerError e) {
            System.out.println("Erreur : Localisation incorrecte.");
        }
    }

    /**
     * Donne la circonférence de l'arbre en cm.
     *
     * @return L'attribut circonférence  de l'arbre.
     */
    protected int getCirconference() {
        return this.circonference;
    }

    /**
     * Donne la hauteur de l'arbre en m.
     *
     * @return L'attribut hauteur de l'arbre.
     */
    protected double getHauteur() {
        return this.hauteur;
    }

    /**
     * Donne l'id de l'arbre.
     *
     * @return L'attribut id de l'arbre.
     */
    protected int getId() {
        return this.id;
    }

    /**
     * Renvoie un booléen à VRAI si l'arbre est remarquable, à FAUX sinon.
     *
     * @return L'attribut estRemarquable de l'arbre.
     */
    protected boolean getEstRemarquable() {
        return this.estRemarquable;
    }

    /**
     * Renvoie la date de classification en arbre remarquable.
     *
     * @return Une copie de l'attribut dateRemarquable de l'arbre.
     */
    protected int[] getDateRemarquable() {
        return this.dateRemarquable.clone();
    }

    /**
     * Classifie un arbre comme remarquable en donnant une date de classification.
     *
     * @param date Date à laquelle l'arbre est reconnu comme remarquable.
     */
    public void rendreRemarquable(int[] date) {
        if (application.date.estValide(date)) {
            this.dateRemarquable = date;
            this.estRemarquable = true;
            this.isDateRemarquable = true;
        } else {
            System.out.println("[Arbre] : L'arbre n'a pas pu être rendu remarquable.");
        }
    }

    /**
     * Classifie un arbre comme remarquable sans préciser de date de classification.
     */
    public void rendreRemarquable() {
        estRemarquable = true;
    }
}
