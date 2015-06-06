package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import loadformat.*;

/**
 * Created by Briareus on 25.05.2015.
 */
public class HistogramDrawingPanelForXaxis extends JPanel
{
    private final Format datenmodell; //Übernahme von loadformat.Format Klasse; muss nicht Bezeichnung datenmodell haben
    private String line;
    private int indexVariableX = 0;
    private int indexVariableY = 1;
    private String xlabel;
    private String ylabel;

    public HistogramDrawingPanelForXaxis(Format datenmodell)
    {
        this.datenmodell = datenmodell;
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Instanzen
        ArrayList arrayX = datenmodell.getListe().get(indexVariableX).getValues();
        double xMaximum = calculateMaximum(arrayX);
        double xMinimum = calculateMinimum(arrayX);
        double contentHeight;
        int totalValues = getTotalValues(arrayX);
        //setXlabel(xlabel = datenmodell.getListe().get(indexVariableX).getName());

        //ylabel = datenmodell.getListe().get(indexVariableY).getName();
        int howManyBins;

        //Klassenanzahl berechnen
        if (totalValues <= 500)
        {
            howManyBins = (int) Math.round(Math.sqrt(totalValues));
        }
        else
        {
            howManyBins = 20;
        }

        double modulus = ((xMaximum - xMinimum) / howManyBins); //Wie breit ist Intervall mit den Werten

        //Anzahl Werte pro Intervall berechnen
        double frequency = 0.0;
        double upperLimitInterval = xMinimum + modulus;
        ArrayList<Double> arrayFrequency = new ArrayList<>();
        for (int i = 0; i < howManyBins; i++)
        {
            for (int a = 0; a < totalValues; a++)
            {
                double z = (double) arrayX.get(a);
                if (z >= xMinimum && z < upperLimitInterval)
                {
                    frequency++;
                }

            }
            arrayFrequency.add(frequency);
            frequency = 0;
            xMinimum = upperLimitInterval;
            upperLimitInterval = xMinimum + modulus;
        }

        //Test Histogramm mit einer loadformat.Variable
        int barWidth = getWidth() / howManyBins;
        contentHeight = calculateMaximum(arrayFrequency) + calculateMinimum(arrayFrequency);
        for (int i = 0; i < arrayFrequency.size(); i++)
        {
            double x_1 = arrayFrequency.get(i);
            int barHeight = (int) (x_1 * (getHeight() / contentHeight));
            //so sein lassen
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g.setColor(Color.RED);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }

    }
    //Index setzen für mehrere Variablen
    public void setIndexVariableX(int indexNumber)
    {
        indexVariableX = indexNumber;
        repaint();
    }

    public void setIndexVariableY(int indexNumber)
    {
        indexVariableY = indexNumber;
        repaint();
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

    public int getTotalValues(ArrayList<Double> axis)
    {
        int anzahl = 0;
        for (int i = 0; i <= axis.size(); i++)
        {
            anzahl = i;
        }
        return anzahl;
    }



    public String getXlabel(int indexNumber)
    {
        indexVariableX = indexNumber;
        String xlabel = datenmodell.getListe().get(indexVariableX).getName();
        return xlabel;
    }

    public String getYlabel()
    {
        String ylabel = datenmodell.getListe().get(indexVariableY).getName();
        return ylabel;
    }
}


