import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Briareus on 25.05.2015.
 */
public class Histogramm extends JPanel
{
    private final Format datenmodell; //Übernahme von Format Klasse; muss nicht Bezeichnung datenmodell haben
    private String line;
    double endpointx;
    double endpointy;

    public Histogramm(Format datenmodell)
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

        //Test Histogramm mit einer Variable
        int barWidth = getWidth() / wertex.size();
        for (int i = 0; i < wertex.size(); i++)
        {
            double x_1 = (Double) wertex.get(i);
            int barHeight = (int) ( x_1/ 500 * getHeight()); //500 magic number! => Skalierung einstellen?
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g.setColor(Color.ORANGE);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.YELLOW);
            g.drawRect(x, y, barWidth, barHeight);
        }

    }
}
