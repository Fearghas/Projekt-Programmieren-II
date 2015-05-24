import javafx.beans.binding.DoubleExpression;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Briareus on 19.05.2015.
 */
public class TestDrawingPanel extends JPanel
{
    private final Format datenmodell; //Übernahme von Format Klasse; muss nicht Bezeichnung datenmodell haben
    private String line;
    double endpointx;
    double endpointy;
    /*int x;
    int y;
    int nextpointx;
    int nextpointy;*/

    public TestDrawingPanel(Format datenmodell)
    {
        this.datenmodell = datenmodell;
    }

    protected void paintComponent(Graphics g)
    {
        //Instanzen
        ArrayList wertex = datenmodell.getarrayx();
        ArrayList wertey = datenmodell.getarrayy();
        double xMaximum = datenmodell.getMaximumx();
        double xMinimum = datenmodell.getMinimumx();
        double yMaximum = datenmodell.getMaximumy();
        double yMinimum = datenmodell.getMinimumy();
        double contentWidth;
        double contentHeight;
        String xlabel = datenmodell.getxName();
        String ylabel = datenmodell.getyName();

        //4 Bedingungen Koordinatensystem
        if (xMinimum >= 0 && yMinimum >= 0)
        {
            contentWidth = xMaximum + xMinimum;
            contentHeight = yMaximum + yMinimum;
            xMinimum = 0;
            yMinimum = 0;
        }
        else if (xMinimum < 1 && yMinimum >= 0)
        {
            xMinimum = -1 * xMinimum;
            contentWidth = xMaximum + xMinimum;
            contentHeight = yMaximum + yMinimum;
            yMinimum = 0;
        }
        else if (xMinimum >= 0 && yMinimum < 1)
        {
            yMinimum = -1 * yMinimum;
            contentWidth = xMaximum + xMinimum;
            contentHeight = yMaximum + yMinimum;
            xMinimum = 0;
        }
        else
        {
            xMinimum = -1 * xMinimum;
            yMinimum = -1 * yMinimum;
            contentWidth = xMaximum + xMinimum;
            contentHeight = yMaximum + yMinimum;
        }

        final double scaleWidth = getWidth() / contentWidth;
        final double scaleHeight = getHeight() / contentHeight;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();
        g2d.translate(0 + (scaleWidth) * xMinimum, getHeight() - (scaleHeight) * yMinimum);//Koordinatenursprung verschieben
        g2d.scale(1, -1); //Invert the y-axis

        //Punkte einzeichnen

        for (int i = 0; i < wertex.size(); i++)
        {
            double getX = (Double) wertex.get(i);
            double getY = (Double) wertey.get(i);
            /*ArrayList<Double> modifiedx = new ArrayList<>();
            modifiedx.add(getX);
            System.out.println(wertex.size());
            ArrayList<Double> modifiedy = new ArrayList<>();
            modifiedy.add(getY);*/


                if (i == wertex.size() - 1)
                {
                    endpointx = (Double) wertex.get(0);
                    endpointy = (Double) wertey.get(0);
                }
                else
                {
                    endpointx = (Double) wertex.get(i + 1);
                    endpointy = (Double) wertey.get(i + 1);
                }



            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);
            int nextpointx = (int) (endpointx * scaleWidth);
            int nextpointy = (int) (endpointy * scaleHeight);


            g.setColor(Color.BLUE);
            g.fillOval(x, y, 10, 10);

            for (int a = 0; a < 10; a++)
            {
                g.drawLine(x, y, nextpointx, nextpointy);
                x = nextpointx;
                y = nextpointy;
            }
        }


        g2d.setTransform(at);

        //Test Histogramm mit einer Variable
        /*int barWidth = getWidth() / wertex.size();
        for (int i = 0; i < wertex.size(); i++)
        {
            double x_1 = (Double) wertex.get(i);
            int barHeight = (int) ( x_1/ 500 * getHeight()); //500 magic number! => Skalierung einstellen?
            int x = i * barWidth;
            int y = getHeight() - barHeight;
            int x = i * barWidth;
            int y = getHeight() - barHeight;
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.YELLOW);
            g.drawRect(x, y, barWidth, barHeight);
        }*/
    }
}
