import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Briareus on 26.05.2015.
 */
public class DrawingOptions extends JPanel
{
    //Instanzen
    private final DrawingPanel datenmodell;
    private String line;
    private int pointSize;
    double endpointx;
    double endpointy;

    public DrawingOptions(DrawingPanel datenmodell)
    {
        this.datenmodell = datenmodell;
    }

    public int getPointSize()
    {
        return pointSize;
    }

    public void setPointSize(int pointSize)
    {
        this.pointSize = pointSize;
    }

    public void drawLines(ArrayList<Double> xWerte, ArrayList<Double> yWerte, Graphics g, double scaleWidth, double scaleHeight)
    {
        for (int i = 0; i < xWerte.size(); i++)
        {
            double getX = xWerte.get(i);
            double getY = yWerte.get(i);
            if (i == xWerte.size() - 1)//magic number
            {
                //endpointx = (Double) wertex.get(0);
                //endpointy = (Double) wertey.get(0);
            }
            else
            {
                endpointx = xWerte.get(i + 1);
                endpointy = yWerte.get(i + 1);
            }
            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);
            int nextpointx = (int) (endpointx * scaleWidth);
            int nextpointy = (int) (endpointy * scaleHeight);
            int correctionX = getPointSize() / 2;
            int correctionY = getPointSize() / 2;
            g.setColor(Color.red);
            g.drawLine(x + correctionX, y + correctionY, nextpointx + correctionX, nextpointy + correctionY);
        }
    }

    public int pointSize()
    {
        return 1;
    }

    public String colourPoints()
    {
        return null;
    }
}
