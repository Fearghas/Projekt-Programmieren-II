import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Briareus on 16.05.2015.
 */
public class zeilen_delimited implements Formatloader
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

        //alle Rohdaten trennen und im Container speichern
        String fetchvariable = file_Scanner.nextLine();
        int variablen_Anzahl = Integer.parseInt(fetchvariable);

        for (int i = 0; i < variablen_Anzahl; i++)
        {
            String berechnunganzahlvariablen = file_Scanner.nextLine();
            beschriftung.add(berechnunganzahlvariablen);
        }
        String x_Achse = beschriftung.get(0);
        String y_Achse = beschriftung.get(1);

        //get delimiter character
        String delimiter = file_Scanner.nextLine();

        //scan values and store in array
        String [] value = file_Scanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            x_Werte.add(Double.parseDouble(value[i]));
        }

        String [] value2 = file_Scanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            y_Werte.add(Double.parseDouble(value2[i]));
        }

        return new Format(x_Achse, y_Achse, x_Werte, y_Werte);

    }
}
