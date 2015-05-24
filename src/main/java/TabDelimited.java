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
        int variablenAnzahl = 0;
        String [] berechnungAnzahlVariablen = fileScanner.nextLine().split("\\t");
        for( int i = 0; i <= berechnungAnzahlVariablen.length; i++)
        {
            variablenAnzahl = i;
        }

        for (int i = 0; i < variablenAnzahl; i++)
        {
            beschriftung.add(berechnungAnzahlVariablen[i]);
        }
        String xAchse = beschriftung.get(0);
        String yAchse = beschriftung.get(1);

        while (fileScanner.hasNext())
        {
            String [] value = fileScanner.nextLine().split ("\\t");
            xWerte.add(Double.parseDouble(value[0]));
            yWerte.add(Double.parseDouble(value[1]));
        }

        return new Format(xAchse, yAchse, xWerte, yWerte);
    }

}



