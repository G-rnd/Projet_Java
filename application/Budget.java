package application;

import java.util.ArrayList;

public class Budget {
    private final ArrayList<Operation> depenses;
    private final ArrayList<Operation> recettes;

    public Budget() {
        depenses = new ArrayList<>();
        recettes = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("# Buget Annuel :\n  - Dépenses :\n");
        for (Operation op : depenses)
            sb.append(op.toString());
        sb.append("\n  - Recettes :\n");
        for (Operation op : recettes)
            sb.append(op.toString());
        sb.append("# Fin du budget annuel.\n");
        return sb.toString();
    }

    /**
     * Ajoute une dépense au budget.
     *
     * @param op L'opération considérée.
     */
    public void addDepense(Operation op) {
        try {
            if (op.getMontant() < 0 || op.getIsRecette())
                throw new Exception();
            depenses.add(op);
        } catch (Exception e) {
            System.out.println("[Budget] : La dépense est invalide.");
        }
    }

    /**
     * Ajoute une recette au budget.
     *
     * @param op L'opération considérée.
     */
    public void addRecette(Operation op) {
        try {
            if (op.getMontant() < 0 || (!op.getIsRecette()))
                throw new Exception();
            recettes.add(op);
        } catch (Exception e) {
            System.out.println("[Budget] : La recette est invalide.");
        }
    }
}
