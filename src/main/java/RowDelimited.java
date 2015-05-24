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
        ArrayList<String> beschriftung = new ArrayList<String>();
        ArrayList<Double> xWerte = new ArrayList<Double>();
        ArrayList<Double> yWerte = new ArrayList<Double>();

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
            String berechnungAnzahlVariablen = fileScanner.nextLine();
            beschriftung.add(berechnungAnzahlVariablen);
        }
        String xAchse = beschriftung.get(0);
        String yAchse = beschriftung.get(1);

        //get delimiter character
        String delimiter = fileScanner.nextLine();

        //scan values and store in array
        String [] value = fileScanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            xWerte.add(Double.parseDouble(value[i]));
        }

        String [] value2 = fileScanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            yWerte.add(Double.parseDouble(value2[i]));
        }

        return new Format(xAchse, yAchse, xWerte, yWerte);

    }

}


