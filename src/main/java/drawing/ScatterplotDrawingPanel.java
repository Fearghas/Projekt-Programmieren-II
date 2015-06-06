package drawing;

import loadformat.Format;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Briareus on 19.05.2015.
 */
public class ScatterplotDrawingPanel extends JPanel {
    private final Format datenmodell; //Übernahme von loadformat.Format Klasse; muss nicht Bezeichnung datenmodell haben
    private int pointSize;
    private Color chooser;
    private double endpointx;
    private double endpointy;
    private int indexVariableX = 0;
    private int indexVariableY = 1;
    private boolean flagPoints = true;
    private boolean flagLines = false;
    private String xlabel;
    private String ylabel;


    public ScatterplotDrawingPanel(Format datenmodell) {
        this.datenmodell = datenmodell;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Instanzen

        ArrayList arrayX = datenmodell.getListe().get(indexVariableX).getValues();
        ArrayList arrayY = datenmodell.getListe().get(indexVariableY).getValues();
        double xMaximum = calculateMaximum(arrayX);
        double xMinimum = calculateMinimum(arrayX);
        double yMaximum = calculateMaximum(arrayY);
        double yMinimum = calculateMinimum(arrayY);
        double contentWidth;
        double contentHeight;

        System.out.println("xMax: " + xMaximum);
        System.out.println("xMin: " + xMinimum);
        System.out.println("yMax: " + yMaximum);
        System.out.println("yMin: " + yMinimum);
        //4 Bedingungen Koordinatensystem
        if (xMinimum >= 0 && yMinimum >= 0) {
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
            //xMinimum = 0;
            //yMaximum = yMaximum + yMinimum;
        } else if (xMinimum < 1 && yMinimum >= 0) {
            xMinimum = xMinimum;
            contentWidth = (xMaximum + 1) - (xMinimum + 1);
            contentHeight = (yMaximum + 1) - (yMinimum + 1);
            yMinimum = 0;
        } else if (xMinimum >= 0 && yMinimum < 1) {
            yMinimum = yMinimum;
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
            xMinimum = 0;
        } else {
            //xMinimum = -1 * xMinimum;
            //yMinimum = -1 * yMinimum;
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
        }

        final double scaleWidth = getWidth() / contentWidth;
        final double scaleHeight = getHeight() / contentHeight;
        System.out.println("Height: " + getHeight());
        System.out.println("Width: " + getWidth());
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();
        g2d.translate(0 - scaleWidth * (xMinimum - 1), /*getHeight()*/ +scaleHeight * (yMaximum + 1));//Koordinatenursprung verschieben
        //g2d.translate(0 - (xMinimum - 0.5) * scaleWidth, 0 + (yMaximum + 0.5) * scaleHeight ); //Koordinatenursprung bei 0/0, Standard

        //g2d.translate(0, getHeight()); //wenn alle positiv
        g2d.scale(1, -1); //Invert the y-axis

        if (flagPoints) {
            drawPoints(arrayX, arrayY, g, scaleWidth, scaleHeight);
        } //nur Punkte werden eingezeichnet
        if (flagLines) {
            drawLines(arrayX, arrayY, g, scaleWidth, scaleHeight);
        }
        //nur Linien werden eingezeichnet
        g2d.setTransform(at);//y-axis wieder rückgängig gemacht, nicht notwendig
    }


    //Methode Punkte zeichnen
    public void drawPoints(ArrayList<Double> xWerte, ArrayList<Double> yWerte, Graphics g, double scaleWidth, double scaleHeight) {

        for (int i = 0; i < xWerte.size(); i++) {
            double getX = xWerte.get(i);
            double getY = yWerte.get(i);

            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);

            g.setColor(chooser);
            g.fillOval(x, y, getPointSize(), getPointSize());
        }
    }

    //Methode Linien zeichnen
    public void drawLines(ArrayList<Double> xWerte, ArrayList<Double> yWerte, Graphics g, double scaleWidth, double scaleHeight) {
        for (int i = 0; i < xWerte.size(); i++) {
            double getX = xWerte.get(i);
            double getY = yWerte.get(i);
            if (i == xWerte.size() - 1)//magic number
            {
                //endpointx = (Double) wertex.get(0);
                //endpointy = (Double) wertey.get(0);
            } else {
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

    //Methode Punkte verbinden
    public void addLines() {
        flagLines = true;
        repaint();
    }

    //Methode Punkte entfernen
    public void removeLines() {
        flagLines = false;
        repaint();
    }

    public int getPointSize() {
        return pointSize;
    }

    //Methode Punktgrösse einstellen
    public void setPointSize(int number) {
        pointSize = number;
        repaint();
    }

    //Method Farbe aussuchen
    public void setPlotColor() {
        JColorChooser color = new JColorChooser(Color.BLUE);
        ActionListener okActionListener = (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooser = color.getColor();
            }
        });
        JDialog dialog = JColorChooser.createDialog(null, "Change Color of Dots",
                true, color, okActionListener, null);
        dialog.setVisible(true);

        repaint(); //wieder mit den alten Punkten und alte Farbe und gleichzeiteig neue Punkte mit neuen Farben
        //return chooser;
    }

    //Index setzen für mehrere Variablen
    public void setIndexVariableX(int indexNumber) {
        indexVariableX = indexNumber;
        repaint();
    }

    public void setIndexVariableY(int indexNumber) {
        indexVariableY = indexNumber;
        repaint();
    }

    public double calculateMaximum(ArrayList<Double> axis) {
        double maximum = axis.get(0);
        for (int i = 0; i < axis.size(); i++) {
            if (axis.get(i) > maximum) {
                maximum = axis.get(i);
            }
        }
        return maximum;
    }

    public double calculateMinimum(ArrayList<Double> axis) {
        double minimum = axis.get(0);
        for (int i = 0; i < axis.size(); i++) {
            if (axis.get(i) < minimum) {
                minimum = axis.get(i);
            }
        }
        return minimum;
    }

    public String getXlabel() {
        xlabel = datenmodell.getListe().get(indexVariableX).getName();
        return xlabel;
    }

    public String getYlabel() {
        ylabel = datenmodell.getListe().get(indexVariableY).getName();
        return ylabel;
    }
}

