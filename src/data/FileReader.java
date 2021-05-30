package data;


import application.Arbre;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReader
{
    public static ArrayList<Arbre> getDataFromCSVFile(String csvFilePath)
    {
        String line;
        String[] data;
        String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        ArrayList<Arbre> listeArbre = new ArrayList<>();

        //Document data
        Integer idBase;
        String adresse;
        String libelleFrancais;
        String genre;
        String espece;
        Integer circonferenceEnCm;
        Integer hauteurEnM;
        String stadeDeveloppement;
        Boolean remarquable;
        Float[] geographicalPoint2D = new Float[2];

        boolean valide = true;
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

            //Read the file line by line
            while ((line = bufferedReader.readLine()) != null)
            {
                //Get data from the line
                data = line.split(separator, -1);
                //Sort data


                valide = true;
                //Get the base ID
                try
                {
                    idBase = Integer.parseInt(data[0]);
                }
                catch (Exception exception)
                {
                    idBase = null;
                    valide = false;
                }

                //Get the address
                try
                {
                    adresse = data[6];
                }
                catch (Exception exception)
                {
                    adresse = "";
                    valide = false;
                }

                //Get the French name
                try
                {
                    libelleFrancais = data[8];
                }
                catch (Exception exception)
                {
                    libelleFrancais = "";
                    valide = false;
                }

                //Get the genus
                try
                {
                    genre = data[9];
                }
                catch (Exception exception)
                {
                    genre = "";
                    valide = false;
                }

                //Get the specie
                try
                {
                    espece = data[10];
                }
                catch (Exception exception)
                {
                    espece = "";
                    valide = false;
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
                    stadeDeveloppement = "";
                    valide = false;
                }

                //Get whether the tree is remarquable or not
                try {
                    remarquable = data[15].equals("OUI") || data[15].equals("oui");
                }
                catch (Exception exception)
                {
                    remarquable = false;
                    valide = false;
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
                    valide = false;
                }

                //TODO Do something with data
                if (valide) {
                    listeArbre.add(new Arbre(idBase, genre, espece, libelleFrancais, circonferenceEnCm, hauteurEnM, stadeDeveloppement,
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
