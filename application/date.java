package application;

public class date {
    public static int[] stringToDate(String s) {
        int[] date = new int[3];
        try {
            String[] tab = s.split("/");
            for(int i = 0; i < 3; i++) {
                date[i] = Integer.parseInt(tab[i]);
            }
        }
        catch (Exception e) {
        }
        return date;
    }
    public static String dateToString(int[] date) {
        if (estValide(date)) {
            return new String(date[0] + "/" + date[1] +"/" + date[2]);
        }
        return new String();
    }

    public static boolean datesEgales(int[] d1, int[] d2) {
        if (estValide(d1) && estValide(d2)) {
            return  (d1[0] == d2[0]) && (d1[1] == d2[1]) && (d1[2] == d2[2]);
        }
        return false;
    }

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
            if (date[2] <= 1900) {
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
