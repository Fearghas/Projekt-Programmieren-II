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

    public TestDrawingPanel(Format datenmodell)
    {
        this.datenmodell = datenmodell;
    }

    protected void paintComponent(Graphics g)
    {
        String xlabel = datenmodell.getxName();
        String ylabel = datenmodell.getyName();
        int xMaximum = (int) datenmodell.getMaximumx();
        int yMaximum = (int) datenmodell.getMaximumy();
        ArrayList wertex = datenmodell.getarrayx();
        ArrayList wertey = datenmodell.getarrayy();
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();
        g2d.translate(0, getHeight());
        g2d.scale(1, -1); //Invert the y-axis.



        for (int i = 0; i < wertex.size(); i++)
        {
            double x_1 = (Double) wertex.get(i);
            double y_1 = (Double) wertey.get(i);
            int x = (int) x_1 ;
            int y = (int) y_1;
            if (x > 500 || y > 500 )
            {
                x = x / 10;
                y = y / 10;
            }
            else if (x < 5 || y < 5)
            {
                x = x * 100;
                y = x * 100;
            }
            g.setColor(Color.BLUE);
            g.fillOval(x, y, 10, 10);
        }
        g2d.setTransform(at);

        //Test Histogramm mit einer Variable
        /*int barWidth = getWidth() / wertex.size();
        for (int i = 0; i < wertex.size(); i++)
        {
            double x_1 = (Double) wertex.get(i);
            int barHeight = (int) ( x_1/ 500 * getHeight()); //500 magic number! => Skalierung einstellen?
<<<<<<< HEAD
            int x = i * barWidth;
            int y = getHeight() - barHeight;
=======

            int x = i * barWidth;
            int y = getHeight() - barHeight;

>>>>>>> fc4f9b8f6e9c53e00727fc20500a399d081bb641
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.YELLOW);
            g.drawRect(x, y, barWidth, barHeight);
        }*/
    }
<<<<<<< HEAD
}
=======
}

>>>>>>> fc4f9b8f6e9c53e00727fc20500a399d081bb641
