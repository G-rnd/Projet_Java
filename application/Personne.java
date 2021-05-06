package application;

public class Personne {
    private String nom;
    private String prenom;
    private int[] dateNaissance;
    private String adresse;

    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public String getStringDateNaissance() {
        return date.dateToString(this.dateNaissance);
    }
    public int[] getArrayDateNaissance() {
        return this.dateNaissance.clone();
    }
    public String getAdresse() {
        return this.adresse;
    }

    @Override
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Informatiions de la personne ---\n");
        sb.append("Nom                   : ");
        sb.append(this.getNom());
        sb.append("\nPrénom                : ");
        sb.append(this.getPrenom());
        sb.append("\nDate de naissance     : ");
        sb.append(this.getStringDateNaissance());
        sb.append("\nAdresse               :\n    ");
        sb.append(this.getAdresse());
        return sb.toString();
    }

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

    public static void main(String[] s) {
        Personne a = new Personne("a","a", date.stringToDate("1/1/2000"), "a");
        Personne b = new Personne("a","a", date.stringToDate("1/1/2000"), "a");

        System.out.println(a.equals(b));
    }
}
