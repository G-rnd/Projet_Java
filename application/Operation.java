package application;

public class Operation {
    private final double montant;
    private final boolean isRecette;
    private String entite;

    public Operation(String entite, double montant, boolean isRecette) {
        this.entite = entite;
        this.montant = montant;
        this.isRecette = isRecette;
    }

    /**
     * Permet d'obtenir le nom de l'entité ayant réalisé l'opération.
     *
     * @return Le nom de l'entité.
     */
    public String getEntite() {
        return entite;
    }

    /**
     * Permet de changer le nom de l'entité ayant réalisé l'opération.
     *
     * @param nom Le nouveau nom de l'entité.
     */
    public void setEntite(String nom) {
        entite = nom;
    }

    /**
     * Permet d'obtenir le montant de l'opération.
     *
     * @return Le montant de l'opération.
     */
    public double getMontant() {
        return montant;
    }

    /**
     * Permet de savoir si l'opération est une recette ou une dépense.
     *
     * @return Un booléen à vrai si l'opération est une recette, à faux sinon.
     */
    public boolean getIsRecette() {
        return isRecette;
    }

    @Override
    public String toString() {
        return entite + " " + ((isRecette) ? "+" : "-") + " " + montant + "\n";
    }

}
