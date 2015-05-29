import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Briareus on 25.05.2015.
 */
public class HistogramDrawingPanel extends JPanel
{
    private final Format datenmodell; //Übernahme von Format Klasse; muss nicht Bezeichnung datenmodell haben
    private String line;
    double endpointx;
    double endpointy;

    public HistogramDrawingPanel(Format datenmodell)
    {
        this.datenmodell = datenmodell;
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Instanzen
        ArrayList wertex = datenmodell.getarrayx();
        ArrayList wertey = datenmodell.getarrayy();
        double xMaximum = datenmodell.getMaximumx();
        double xMinimum = datenmodell.getMinimumx();
        double yMaximum = datenmodell.getMaximumy();
        double yMinimum = datenmodell.getMinimumy();
        int anzahlWerte = datenmodell.getTotalValues(datenmodell.getarrayx());
        double contentWidth;
        double contentHeight;
        String xlabel = datenmodell.getxName();
        String ylabel = datenmodell.getyName();

        System.out.println(anzahlWerte);

        //Test Histogramm mit einer Variable
        int barWidth = getWidth() / wertex.size();
        contentHeight = xMaximum + xMinimum;
        for (int i = 0; i < wertex.size(); i++)
        {
            double x_1 = (Double) wertex.get(i);
            int barHeight = (int) ( x_1 * (getHeight() / contentHeight));
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g.setColor(Color.RED);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }

    }
}
