package loadformat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Created by Briareus on 15/05/2015.
 */


public class TabDelimited implements Formatloader
{
    @Override
    public Format loadformat(String fileName)
    {
        ArrayList<String> arrayLabel = new ArrayList<>();

        //Initialisierung Scanner
        Scanner fileScanner;
        try
        {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        //alle Rohdaten trennen und im Container speichern
        int variablenAnzahl = 0;
        String [] numberOfVariables = fileScanner.nextLine().split("\\t");
        for( int i = 0; i <= numberOfVariables.length; i++)
        {
            variablenAnzahl = i;
        }

        for (int i = 0; i < variablenAnzahl; i++)
        {
            arrayLabel.add(numberOfVariables[i]);
        }

        ArrayList[] temporaryValuesList = new ArrayList[variablenAnzahl];
        for (int i = 0; i < variablenAnzahl; i++)
        {
            temporaryValuesList[i] = new ArrayList<Double>();
        }

        while (fileScanner.hasNext())
        {
            String[] value = fileScanner.nextLine().split ("\\t");
            for (int i = 0; i < variablenAnzahl; i++)
            {
                temporaryValuesList[i].add(Double.parseDouble(value[i]));
            }
        }

        ArrayList<Variable> variablesList = new ArrayList();
        for (int i = 0; i < variablenAnzahl; i++)
        {
            variablesList.add(createVariable(arrayLabel.get(i), temporaryValuesList[i]));
        }

        System.out.println(temporaryValuesList[0].size());
        return new Format(arrayLabel, variablesList);
    }


    public Variable createVariable(String string, ArrayList<Double> arraylist)
    {
        return new Variable(string, arraylist);
    }

}



