package loadformat;

import loadformat.Variable;

import java.util.ArrayList;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Format
{
    private Variable xAxis;
    private Variable yAxis;
    private ArrayList<String> label;
    private ArrayList<Variable> liste;
    private Variable variableOne;


    public Format(ArrayList<String> label, ArrayList<Variable> liste)
    {
        this.label = label;
        this.liste = liste;
    }

    public Variable getVariableX(ArrayList<Variable> test)
    {
        xAxis = test.get(0);
        return xAxis;
    }

    public Variable getVariableY(ArrayList<Variable> test)
    {
        yAxis = test.get(1);
        return yAxis;
    }

    public ArrayList<String> getLabel() {
        return label;
    }

    public ArrayList<Variable> getListe()
    {
        return liste;
    }

   /* public double calculateMaximum(ArrayList<Double> axis)
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

    public double getMaximumx()
    {
        return calculateMaximum(xAxis.getValues());
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

    /*public ArrayList getarrayx()
    {
        return xAxis.getValues();
    }

    public ArrayList getarrayy()
    {
        return yAxis.getValues();
    }

    /*@Override
    public String toString()
    {
        return "x-Achse: "  + "\ny-Achse: " + "\nx-Werte: " + getarrayx() + "\ny-Werte: " + getarrayy()
                + "\nmaximum x-Wert: " + getMaximumx() + "\nminimum x-Wert: " + getMinimumx() + "\nAnzahl x-Werte: " + getTotalValues(xAxis.getValues())
                + "\nmaximum y-Wert: " + getMaximumy() + "\nminimum y-Wert: " + getMinimumy() + "\nAnzahl y-Werte: " + getTotalValues(yAxis.getValues());
    }*/
}