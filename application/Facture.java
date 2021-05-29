package application;

import java.util.ArrayList;

public class Facture extends Operation {

    public Facture(String entite, double montant) {
        super(entite, montant, false);
    }

    /**
     * Sélectionne les factures provenant de la même entité dans la liste des factures passée en paramètre.
     *
     * @param listeFactures Liste des factures dans laquelle chercher.
     * @param entite        Nom de l'entité à chercher.
     * @return Liste des factures provenant de la même entité.
     */
    public static ArrayList<Facture> getFactures(ArrayList<Facture> listeFactures, String entite) {
        ArrayList<Facture> factures = new ArrayList<>();
        for (Facture f : listeFactures) {
            if (f.getEntite().equals(entite))
                factures.add(f);
        }
        return factures;
    }

    /**
     * Sélectionne les factures d'un même montant.
     *
     * @param listeFactures Liste des factures dans laquelle chercher.
     * @param montant       Montant recherché
     * @return Liste des factures avec le même montant.
     */
    public static ArrayList<Facture> getFactures(ArrayList<Facture> listeFactures, double montant) {
        ArrayList<Facture> factures = new ArrayList<>();
        for (Facture f : listeFactures) {
            if (f.getMontant() == montant)
                factures.add(f);
        }
        return factures;
    }

    /**
     * Renvoie une chaîne de caractères comportant la liste des factures, en ajoutant un préfixe particulier.
     *
     * @param listeFactures Liste des factures considérée.
     * @param prefixe       Préfixe considéré.
     * @return La liste des factures avec un préfixe particulier.
     */
    public static String listeFacturesToString(ArrayList<Facture> listeFactures, String prefixe) {
        StringBuilder sb = new StringBuilder();

        for (Facture f : listeFactures) {
            sb.append(prefixe).append(f.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return super.getEntite() + " - " + super.getMontant();
    }
}
