package loadformat;

import loadformat.Formatloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Briareus on 16.05.2015.
 */
public class RowDelimited implements Formatloader
{
    @Override
    public Format loadformat(String fileName)
    {
        //Container vorbereiten für Achsenname, x-Werte und y-Werte
        ArrayList<String> arrayLabel = new ArrayList<String>();

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
        String fetchVariable = fileScanner.nextLine();
        int variablenAnzahl = Integer.parseInt(fetchVariable);

        for (int i = 0; i < variablenAnzahl; i++)
        {
            String numberOfVariables = fileScanner.nextLine();
            arrayLabel.add(numberOfVariables);
        }

        ArrayList[] temporaryValuesList = new ArrayList[variablenAnzahl];
        for (int i = 0; i < variablenAnzahl; i++)
        {
            temporaryValuesList[i] = new ArrayList<Double>();
        }

        //get delimiter character
        String delimiter = fileScanner.nextLine();

        //scan values and store in array

        while (fileScanner.hasNextLine())
        {
            for (int a = 0; a < variablenAnzahl; a++)
            {
                String[] value = fileScanner.nextLine().split(delimiter);
                for (int i = 0; i < value.length; i++)
                {
                    temporaryValuesList[a].add(Double.parseDouble(value[i]));
                }
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
    public Variable createVariable(String a, ArrayList<Double> b)
    {
        return new Variable(a, b);
    }

}




