package application;

public class dateValide {
    public static boolean estValide(int[] date) {
        try {
            StringBuilder error = new StringBuilder();
            boolean res = true;
            if (date.length != 3) {
                error.append("Taille incorrecte");
                res = false;
            }
            if (date[0] <= 0 || date[0] > 31) {
                if (!res) { error.append(", "); }
                error.append("Jour incorrect");
                res = false;
            }
            if (date[1] <= 0 || date[1] > 12) {
                if (!res) { error.append(", "); }
                error.append("Mois incorrect");
                res = false;
            }
            if (date[2] <= 2020) {
                if (!res) { error.append(", "); }
                error.append("Année incorrecte");
                res = false;
            }
            if (!res) { throw new ExceptionInInitializerError(error.toString()); }
            else { return true; }
        }
        catch (ExceptionInInitializerError e) {
            StringBuilder sb = new StringBuilder("Erreur : Date saisie incorrecte : ");
            sb.append(e.toString());
            sb.append(".");
            System.out.println(sb.toString());
            return false;
        }
    }
}
