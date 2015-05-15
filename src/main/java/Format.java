import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Format
{
    private String x_Achse;
    private String y_Achse;
    private ArrayList<Double> x_Wert;
    private ArrayList<Double> y_Wert;

    public Format(String x_Achse, String y_Achse, ArrayList<Double> x_Wert, ArrayList<Double> y_Wert)
    {
        this.x_Achse = x_Achse;
        this.y_Achse = y_Achse;
        this.x_Wert = x_Wert;
        this.y_Wert = y_Wert;
    }

    public String getX_Achse()
    {
        return x_Achse;
    }
    public String getY_Achse()
    {
        return y_Achse;
    }

    public double getMaximum(ArrayList<Double> achse)
    {
        double maximum = achse.get(0);
        for (int i = 0; i < achse.size(); i++)
        {
            if (achse.get(i) > maximum)
            {
                maximum = achse.get(i);
            }
        }
        return maximum;
    }

    public double getMinimum(ArrayList<Double> achse)
    {
        double minimum = achse.get(0);
        for (int i = 0; i < achse.size(); i++)
        {
            if (achse.get(i) < minimum) {
                minimum = achse.get(i);
            }
        }
        return minimum;
    }

    public int getAnzahlWerte(ArrayList<Double> achse)
    {
        int anzahl = 0;
        for (int i = 0; i <= achse.size(); i++)
        {
            anzahl = i;
        }
        return anzahl;
    }

    @Override
    public String toString()
    {
        return "x-Achse: " + x_Achse + "\ny-Achse: " + y_Achse + "\nx-Werte: " + x_Wert + "\ny-Werte: " + y_Wert
                + "\nmaximum x-Wert: " + getMaximum(x_Wert) + "\nminimum x-Wert: " + getMinimum(x_Wert) + "\nAnzahl x-Werte: " + getAnzahlWerte(x_Wert)
                + "\nmaximum y-Wert: " + getMaximum(y_Wert) + "\nminimum y-Wert: " + getMinimum(y_Wert) + "\nAnzahl x-Werte: " + getAnzahlWerte(y_Wert);
    }
}