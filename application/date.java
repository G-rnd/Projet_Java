package application;

/**
 * Date.
 */
public class date {
    /**
     * Renvoie un tableau d'entiers à partir d'une chaîne de caractères.
     *
     * @param s Une chaîne de caractères dont la date associée est au format DD/MM/AAAA.
     * @return Un tableau contenant le jour, le mois et l'année si la date est valide, un tableau vide de taille 3 sinon.
     */
    public static int[] stringToDate(String s) {
        int[] date = new int[3];
        try {
            String[] tab = s.split("/");
            for (int i = 0; i < 3; i++) {
                date[i] = Integer.parseInt(tab[i]);
            }
        } catch (Exception ignored) {
        }
        return date;
    }

    /**
     * Renvoie une chaîne de caractères à partir d'un tableau d'entiers.
     *
     * @param date Un tableau contenant le jour, le mois et l'année de la date considéréeL
     * @return Une chaîne de caractère comportant une date au format DD/MM/AAAA si elle est valide, vide sinon.
     */
    public static String dateToString(int[] date) {
        if (estValide(date)) {
            return date[0] + "/" + date[1] + "/" + date[2];
        }
        return "";
    }

    /**
     * Permet de savoir si deux dates sont égales.
     *
     * @param d1 Un tableau d'entiers comportant la première date.
     * @param d2 Un tableau d'entiers comportant la seconde date.
     * @return Un booléen à VRAI si les dates sont les mêmes, à FAUX sinon.
     */
    public static boolean datesEgales(int[] d1, int[] d2) {
        if (estValide(d1) && estValide(d2)) {
            return (d1[0] == d2[0]) && (d1[1] == d2[1]) && (d1[2] == d2[2]);
        }
        return false;
    }

    // TODO peut être faire le truc des mois à 30/31 et fév 28/29

    /**
     * Permet de savoir si une date est valide.
     *
     * @param date Un tableau d'entiers comportant la date considérée.
     * @return Un booléen à VRAI si la date existe, à FAUX sinon.
     */
    public static boolean estValide(int[] date) {
        try {
            StringBuilder error = new StringBuilder();
            boolean res = true;
            if (date.length != 3) {
                error.append("Taille incorrecte");
                res = false;
            }
            if (date[0] <= 0 || date[0] > 31) {
                if (!res) {
                    error.append(", ");
                }
                error.append("Jour incorrect");
                res = false;
            }
            if (date[1] <= 0 || date[1] > 12) {
                if (!res) {
                    error.append(", ");
                }
                error.append("Mois incorrect");
                res = false;
            }
            if (date[2] <= 1900) {
                if (!res) {
                    error.append(", ");
                }
                error.append("Année incorrecte");
                res = false;
            }
            if (!res) {
                throw new ExceptionInInitializerError(error.toString());
            } else {
                return true;
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println("Erreur : Date saisie incorrecte : " + e.toString() + ".");
            return false;
        }
    }
}
