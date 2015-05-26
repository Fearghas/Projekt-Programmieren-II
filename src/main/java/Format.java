import java.util.ArrayList;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Format
{
    private Variable xAxis;
    private Variable yAxis;
    /*private String xName;
    private String yName;
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;*/

    /*public Format(String xName, ArrayList<Double> xValues, String yName, ArrayList<Double> yValues)
    {
        this.xName = xName;
        this.yName = yName;
        this.xValues = xValues;
        this.yValues = yValues;
    }*/

    public Format(Variable a, Variable b)
    {
        this.xAxis = a;
        this.yAxis = b;
    }

    public String getxName()
    {
        return xAxis.getName();
    }
    public String getyName()
    {
        return yAxis.getName();
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
        return calculateMaximum(xAxis.getValues());
    }

    public double getMaximumy()
    {
        return calculateMaximum(yAxis.getValues());
    }

    public double getMinimumx()
    {
        return calculateMinimum(xAxis.getValues());
    }

    public double getMinimumy()
    {
        return calculateMinimum(yAxis.getValues());
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
        return xAxis.getValues();
    }

    public ArrayList getarrayy()
    {
        return yAxis.getValues();
    }

    @Override
    public String toString()
    {
        return "x-Achse: " + getxName() + "\ny-Achse: " + getyName() + "\nx-Werte: " + getarrayx() + "\ny-Werte: " + getarrayy()
                + "\nmaximum x-Wert: " + getMaximumx() + "\nminimum x-Wert: " + getMinimumx() + "\nAnzahl x-Werte: " + getTotalValues(xAxis.getValues())
                + "\nmaximum y-Wert: " + getMaximumy() + "\nminimum y-Wert: " + getMinimumy() + "\nAnzahl y-Werte: " + getTotalValues(yAxis.getValues());
    }
}