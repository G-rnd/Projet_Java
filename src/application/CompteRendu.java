package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Compte Rendu
 */
public class CompteRendu {

    //Attributs:
    private String contenu="";
    private Membre membre;
    private int[] dateRedaction=null;

    //Méthodes:

   /**
     * Rédaction du contenu du compte rendu
     * @return le contenu du compte rendu
     * @throws IOException
     */
    public String setContenu() throws IOException {
        try (Scanner sc = new Scanner(System.in);){
           this.contenu = sc.next();
            return this.contenu;
       }
    }

    /**
     * Renvoie le contenu du compte rendu.
     * @return une chaine de caractère, le contenu du compte rendu.
     */
    public String getContenu(){return this.contenu;}

    /**
     * Renvoie le compte rendu, son contenu, le membre l'ayant rédigé et la date de rédaction.
     * @return Une chaîne de caractères comportant les informations du compte rendu.
     */
    @Override
            public String toString(){
                return "Compte rendu rédigé par"+this.membre+", le:"+ Arrays.toString(this.dateRedaction) +"\n"+
                contenu;
    }

    //Constructeur
     CompteRendu(String contenu,Membre membre,int[] dateRedaction) throws IOException {
        /* Il faut d'abord créer le compte rendu puis créer son contenu avec la méthode setContenu(). */
        this.contenu=null;
        this.membre=membre;
        this.dateRedaction=dateRedaction;

    }


}
