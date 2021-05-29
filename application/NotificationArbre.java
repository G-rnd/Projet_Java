package application;

public interface NotificationArbre {
    /**
     * Notifie de la plantation d'un arbre.
     *
     * @param arbre L'arbre planté.
     */
    void notifierPlantation(Arbre arbre);

    /**
     * Notifie de l'abattage d'un arbre.
     *
     * @param arbre L'arbre abattu.
     */
    void notifierAbattage(Arbre arbre);

    /**
     * Notifie de la classification d'un arbre à une date particulière.
     *
     * @param arbre              L'arbre classifié.
     * @param dateClassification La date de classification.
     */
    void notifierClassification(Arbre arbre, int[] dateClassification);

    /**
     * Notifie de la classification d'un arbre sans dater la classification.
     *
     * @param arbre L'arbre classifié.
     */
    void notifierClassification(Arbre arbre);
}
