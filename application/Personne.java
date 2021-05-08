package application;

/**
 * Personne.
 */
public class Personne {
    // Attributs
    private String nom;
    private String prenom;
    private int[] dateNaissance;
    private String adresse;

    //Méthodes

    /**
     * Renvoie le nom de la personne.
     * @return L'attribut nom de la personne.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Renvoie le prénom de la personne.
     * @return L'attribut prenom de la personne.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Renvoie la date de naissance de la personne.
     * @return Renvoie une chaîne de caractère correspondant à la date de naissance de la personne au format DD/MM/AAAA.
     */
    public String getStringDateNaissance() {
        return date.dateToString(this.dateNaissance);
    }

    /**
     * Renvoie une copie de la date de naissance de la personne.
     * @return L'attribut dateNaissance copié.
     */
    public int[] getArrayDateNaissance() {
        return this.dateNaissance.clone();
    }

    /**
     * Renvoie l'adresse de la personne.
     * @return L'attribut adresse de la personne.
     */
    public String getAdresse() {
        return this.adresse;
    }

    @Override

    /**
     * Renvoie un booléen à VRAI si l'objet passé en paramètre est la même personne.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        Personne p = (Personne) obj;
        return  this.nom.equals(p.nom)
                && this.prenom.equals(p.prenom)
                && date.datesEgales(this.dateNaissance, p.dateNaissance)
                && this.adresse.equals(p.adresse);
    }

    @Override
    /**
     * Renvoie une fiche d'information de la personne.
     * @return Une chaîne de caractères comportant les informations de la personne.
     */
    public String toString() {
        return "--- Informations de la personne ---\n" +
               "Nom                   : " +
               this.getNom() +
               "\nPrénom                : " +
               this.getPrenom() +
               "\nDate de naissance     : " +
               this.getStringDateNaissance() +
               "\nAdresse               :\n    " +
               this.getAdresse();
    }

    // Constructeur
    /**
     * Initialise une personne à partir d'un nom, d'un prénom, d'une date de naissance et une adresse.
     * @param nom Le nom de la personne.
     * @param prenom Le prénom de la personne.
     * @param dateNaissance Un tableau d'entiers comportant le jour, le mois et l'année de naissance de la personne.
     * @param adresse L'adresse de la personne.
     */
    public Personne(String nom, String prenom, int[] dateNaissance, String adresse) {
        try {
            if (date.estValide(dateNaissance)) {
                this.nom = nom;
                this.prenom = prenom;
                this.adresse = adresse;
                this.dateNaissance = dateNaissance;
            }
            else {
                throw new ExceptionInInitializerError("Date de naissance invalide");
            }
        }
        catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }
}
