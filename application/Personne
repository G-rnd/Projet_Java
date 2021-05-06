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
        StringBuilder sb = new StringBuilder();
        sb.append(this.dateNaissance[0]);
        for(int i = 1; i < 3; i ++) {
            sb.append("/ ");
            sb.append(this.dateNaissance[i]);
        }
        return sb.toString();
    }
    public int[] getArrayDateNaissance() {
        return this.dateNaissance.clone();
    }
    public String getAdresse() {
        return this.adresse;
    }

    public Personne(String nom, String prenom, int[] dateNaissance, String adresse) {
        try {
            if (dateValide.estValide(dateNaissance)) {
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
