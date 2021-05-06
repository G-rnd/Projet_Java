package data;

import application.Arbre;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader
{
    public static ArrayList<Arbre> getDataFromCSVFile(String csvFilePath)
    {
        String line = "";
        String[] data = null;
        String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        ArrayList<Arbre> listeArbre = new ArrayList<Arbre>();

        //Document data
        Integer idBase;
        String typeEmplacement;
        String domanialite;
        String arrondissement;
        String complementAdresse;
        String adresse;
        Integer idEmplacement;
        String libelleFrancais;
        String genre;
        String espece;
        String varieteOuCultivar;
        Integer circonferenceEnCm;
        Integer hauteurEnM;
        String stadeDeveloppement;
        Boolean remarquable;
        Float[] geographicalPoint2D = new Float[2];

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1))
        {
            //Read the first line
            line = bufferedReader.readLine();

            //Get data from the line
            data = line.split(separator, -1);

            if(data.length != 17)
            {
                System.out.println("[FileReader] The file at " + csvFilePath + " does not contain the right number of columns.");
                return listeArbre;
            }

            int i = 1;

            //Read the file line by line
            while ((line = bufferedReader.readLine()) != null)
            {
                //Get data from the line
                data = line.split(separator, -1);
                boolean valide = true;
                //Sort data

                //Get the base ID
                try
                {
                    idBase = Integer.parseInt(data[0]);
                }
                catch (Exception exception)
                {
                    idBase = null;
                }

                //Get the location type
                try
                {
                    typeEmplacement = data[1];
                }
                catch (Exception exception)
                {
                    typeEmplacement = new String();
                }

                //Get the domain
                try
                {
                    domanialite = data[2];
                }
                catch (Exception exception)
                {
                    domanialite = new String();
                }

                //Get the district
                try
                {
                    arrondissement = data[3];
                }
                catch (Exception exception)
                {
                    arrondissement = new String();
                }

                //Get the complementary address
                try
                {
                    complementAdresse = data[4];
                }
                catch (Exception exception)
                {
                    complementAdresse = new String();
                }

                //data[5] is the number

                //Get the address
                try
                {
                    adresse = data[6];
                }
                catch (Exception exception)
                {
                    adresse = new String();
                    valide = false;
                }

                //Get the location ID
                try
                {
                    idEmplacement = Integer.parseInt(data[7]);
                }
                catch (Exception exception)
                {
                    idEmplacement = null;
                }

                //Get the French name
                try
                {
                    libelleFrancais = data[8];
                }
                catch (Exception exception)
                {
                    libelleFrancais = new String();
                    valide = false;
                }

                //Get the genus
                try
                {
                    genre = data[9];
                }
                catch (Exception exception)
                {
                    genre = new String();
                    valide = false;
                }

                //Get the specie
                try
                {
                    espece = data[10];
                }
                catch (Exception exception)
                {
                    espece = new String();
                    valide = false;
                }

                //Get the variety
                try
                {
                    varieteOuCultivar = data[11];
                }
                catch (Exception exception)
                {
                    varieteOuCultivar = new String();
                }

                //Get the circumference (cm)
                try
                {
                    circonferenceEnCm = Integer.parseInt(data[12]);
                }
                catch (Exception exception)
                {
                    circonferenceEnCm = null;
                    valide = false;
                }

                //Get the height (m)
                try
                {
                    hauteurEnM = Integer.parseInt(data[13]);
                }
                catch (Exception exception)
                {
                    hauteurEnM = null;
                    valide = false;
                }

                //Get the development state
                try
                {
                    stadeDeveloppement = data[14];
                }
                catch (Exception exception)
                {
                    stadeDeveloppement = new String();
                }

                //Get whether the tree is remarquable or not
                try {
                    if(data[15].equals("OUI") || data[15].equals("oui"))
                    {
                        remarquable = true;
                    }
                    else
                    {
                        remarquable = false;
                    }
                }
                catch (Exception exception)
                {
                    remarquable = false;
                }

                //Get the geographical point
                try {

                    data = data[16].split(",", -1);
                    try
                    {
                        geographicalPoint2D[0] = Float.parseFloat(data[0]);
                    }
                    catch (Exception exception)
                    {
                        geographicalPoint2D[0] = null;
                        valide = false;
                    }
                    try
                    {
                        geographicalPoint2D[1] = Float.parseFloat(data[1]);
                    }
                    catch (Exception exception)
                    {
                        geographicalPoint2D[1] = null;
                        valide = false;
                    }
                }
                catch (Exception exception) {
                    geographicalPoint2D = new Float[]{null, null};
                }

                //TODO Do something with data
                if (valide && hauteurEnM > 20 && circonferenceEnCm > 200) {
                    listeArbre.add(new Arbre(genre, espece, libelleFrancais, circonferenceEnCm, hauteurEnM, stadeDeveloppement,
                            adresse, new double[]{geographicalPoint2D[0], geographicalPoint2D[1]}, remarquable));
                }
            }
        }
        catch (IOException exception)
        {
            System.err.println(exception);
        }
        return listeArbre;
    }
}
