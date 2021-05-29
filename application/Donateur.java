package application;

public interface Donateur {
    /**
     * Permet d'effectuer un don.
     *
     * @param donataire Entité recevant le don.
     * @param montant   Montant du don.
     */
    void don(Donataire donataire, float montant);

    /**
     * Permet de recevoir une demande de don.
     *
     * @param donataire       Entité demandant le don.
     * @param montant         Montant du don.
     * @param message         Message du donataire.
     * @param rapportActivite Rapport d'activité du donataire
     */
    void recevoirDemandeDon(Donataire donataire, float montant, String message, String rapportActivite);

    /**
     * Retourne le nom du donateur.
     *
     * @return Le nom du donateur.
     */
    String getNom();

}
