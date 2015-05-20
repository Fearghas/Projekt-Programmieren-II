import java.util.ArrayList;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Format
{
    private String xName;
    private String yName;
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;

    public Format(String xName, String yName, ArrayList<Double> xValues, ArrayList<Double> yValues)
    {
        this.xName = xName;
        this.yName = yName;
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public String getxName()
    {
        return xName;
    }
    public String getyName()
    {
        return yName;
    }

    public double calculateMaximum(ArrayList<Double> axis)
    {
        double maximum = axis.get(0);
        for (int i = 0; i < axis.size(); i++)
        {
            if (axis.get(i) > maximum)
            {
                maximum = axis.get(i);
            }
        }
        return maximum;
    }

    public double calculateMinimum(ArrayList<Double> axis)
    {
        double minimum = axis.get(0);
        for (int i = 0; i < axis.size(); i++)
        {
            if (axis.get(i) < minimum) {
                minimum = axis.get(i);
            }
        }
        return minimum;
    }

    public double getMaximumx()
    {
        return calculateMaximum(xValues);
    }

    public double getMaximumy()
    {
        return calculateMaximum(yValues);
    }

    public double getMinimumx()
    {
        return calculateMinimum(xValues);
    }

    public double getMinimumy()
    {
        return calculateMinimum(yValues);
    }

    public int getTotalValues(ArrayList<Double> axis)
    {
        int anzahl = 0;
        for (int i = 0; i <= axis.size(); i++)
        {
            anzahl = i;
        }
        return anzahl;
    }

    public ArrayList getarrayx()
    {
        return xValues;
    }

    public ArrayList getarrayy()
    {
        return yValues;
    }

    @Override
    public String toString()
    {
        return "x-Achse: " + xName + "\ny-Achse: " + yName + "\nx-Werte: " + xValues + "\ny-Werte: " + yValues
                + "\nmaximum x-Wert: " + getMaximumx() + "\nminimum x-Wert: " + getMinimumx() + "\nAnzahl x-Werte: " + getTotalValues(xValues)
                + "\nmaximum y-Wert: " + getMaximumy() + "\nminimum y-Wert: " + getMinimumy() + "\nAnzahl y-Werte: " + getTotalValues(yValues);
    }
}