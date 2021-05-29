package application;

public interface Donataire {
    /**
     * Permet de recevoir un don.
     *
     * @param donateur Entité effectuant le don.
     * @param montant  Montant du don.
     */
    void recevoirDon(Donateur donateur, float montant);

    /**
     * Permet de demander un don à une entité donatrice.
     *
     * @param donateur Entité effectuant le don.
     * @param message  Demande écrite.
     */
    void demanderDon(Donateur donateur, float montant, String message, String rapportActivite);
}
