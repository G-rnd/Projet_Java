package application;

/**
 * Personne.
 */
public class Personne {
    private String nom;
    private String prenom;
    private int[] dateNaissance;
    private String adresse;

    /**
     * Initialise une personne à partir d'un nom, d'un prénom, d'une date de naissance et une adresse.
     *
     * @param nom           Le nom de la personne.
     * @param prenom        Le prénom de la personne.
     * @param dateNaissance Un tableau d'entiers comportant le jour, le mois et l'année de naissance de la personne.
     * @param adresse       L'adresse de la personne.
     */
    public Personne(String nom, String prenom, int[] dateNaissance, String adresse) {
        try {
            if (date.estValide(dateNaissance)) {
                this.nom = nom;
                this.prenom = prenom;
                this.adresse = adresse;
                this.dateNaissance = dateNaissance;
            } else {
                throw new ExceptionInInitializerError("Date de naissance invalide");
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Renvoie le nom de la personne.
     *
     * @return L'attribut nom de la personne.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le prénom de la personne.
     *
     * @return L'attribut prenom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne une chaîne de caractères composé du prénom et du nom de la personne.
     *
     * @return Le prénom et le nom de la personne.
     */
    public String getPrenomNom() {
        return prenom + " " + nom;
    }

    /**
     * Renvoie la date de naissance de la personne.
     *
     * @return Renvoie une chaîne de caractère correspondant à la date de naissance de la personne au format DD/MM/AAAA.
     */
    public String getStringDateNaissance() {
        return date.dateToString(dateNaissance);
    }

    /**
     * Renvoie l'adresse de la personne.
     *
     * @return L'attribut adresse de la personne.
     */
    public String getAdresse() {
        return adresse;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Personne p = (Personne) obj;
        return this.nom.equals(p.nom)
                && this.prenom.equals(p.prenom)
                && date.datesEgales(this.dateNaissance, p.dateNaissance)
                && this.adresse.equals(p.adresse);
    }

    @Override
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
}
