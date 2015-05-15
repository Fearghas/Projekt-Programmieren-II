import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Briareus on 15/05/2015.
 */
public class tab_delimited implements Formatloader
{

    @Override
    public Format loadformat(String fileName)
    {
        //Container vorbereiten für Achsenname, x-Werte und y-Werte
        ArrayList<String> beschriftung = new ArrayList<String>();
        ArrayList<Double> x_Werte = new ArrayList<Double>();
        ArrayList<Double> y_Werte = new ArrayList<Double>();

        //Initialisierung Scanner
        Scanner file_Scanner = null;
        try
        {
            file_Scanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        //alle Rohdaten trennen und im Containter speichern
        int variablen_Anzahl = 0;
        String [] berechnunganzahlvariablen = file_Scanner.nextLine().split("\\t");
        for( int i = 0; i <= berechnunganzahlvariablen.length; i++)
        {
            variablen_Anzahl = i;
        }

        for (int i = 0; i < variablen_Anzahl; i++)
        {
            beschriftung.add(berechnunganzahlvariablen[i]);
        }
        String x_Achse = beschriftung.get(0);
        String y_Achse = beschriftung.get(1);

        while (file_Scanner.hasNext())
        {
            String [] value = file_Scanner.nextLine().split ("\\t");
            x_Werte.add(Double.parseDouble(value[0]));
            y_Werte.add(Double.parseDouble(value[1]));
        }

        return new Format(x_Achse, y_Achse, x_Werte, y_Werte);
    }
}
