import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Briareus on 19.05.2015.
 */
public class DrawingPanel extends JPanel implements ActionListener
{
    private final Format datenmodell; //Übernahme von Format Klasse; muss nicht Bezeichnung datenmodell haben
    private String line;
    private int pointSize;
    double endpointx;
    double endpointy;

    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu editMenue;
    JMenuItem closeItem;
    JMenuItem pointItem;
    JMenuItem bigSize;

    public DrawingPanel(Format datenmodell)
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
        setPointSize(5);
        drawPoints(wertex, wertey, g, scaleWidth, scaleHeight); //nur Punkte werden eingezeichnet
        drawLines(wertex, wertey, g, scaleWidth, scaleHeight);  //nur Linien werden eingezeichnet
        MenueBar();
        g2d.setTransform(at);//y-axis wieder rückgängig gemacht, nicht notwendig
    }


    protected void MenueBar()
    {
        //this.datenmodell = datenmodell;
        //this.setSize(600, 600);

        menueBar = new JMenuBar();
        fileMenue = new JMenu("Datei");
        editMenue = new JMenu("Punkte");

        // Menüpunkte  erzeuge
        closeItem = new JMenuItem("Schliessen");
        pointItem = new JMenuItem("Verbinden");
        bigSize = new JMenuItem("Gr\u00f6sse");

        // Menüpunkte dem Datei-Menü hinzufügen
        editMenue.add(pointItem);
        editMenue.add(bigSize);
        fileMenue.add(closeItem);

        //Datei-Menü  Menüleiste hinzufüg
        menueBar.add(fileMenue);
        menueBar.add(editMenue);

        //Menüleiste JFrame hinzufüg
        this.add(menueBar, BorderLayout.NORTH);

        pointItem.addActionListener(e ->{
            //Code hier für Punktverbindungen
        });

        bigSize.addActionListener(e ->{
            //Code hier für PunktGrösse einstellen
        });
    }

    public void drawPoints(ArrayList<Double> xWerte, ArrayList<Double> yWerte, Graphics g, double scaleWidth, double scaleHeight)
    {
        for (int i = 0; i < xWerte.size(); i++)
        {
            double getX = xWerte.get(i);
            double getY = yWerte.get(i);

            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);

            g.setColor(Color.BLUE);
            g.fillOval(x, y, getPointSize(), getPointSize());
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
