package project.drawing;
import project.processing.Storage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ScatterplotDrawingPanel extends JPanel {
    private final Storage data;
    private static int pointSize;
    private Color chooser;
    private double endpointX;
    private double endpointY;
    private int indexVariableX = 0;
    private int indexVariableY = 1;
    private boolean flagPoints = true;
    private boolean flagLines = false;

    public ScatterplotDrawingPanel(Storage data) {
        this.data = data;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList arrayX = data.getValuesOfVariable().get(indexVariableX).getValues();
        ArrayList arrayY = data.getValuesOfVariable().get(indexVariableY).getValues();
        double xMaximum = calculateMaximum(arrayX);
        double xMinimum = calculateMinimum(arrayX);
        double yMaximum = calculateMaximum(arrayY);
        double yMinimum = calculateMinimum(arrayY);
        double contentWidth;
        double contentHeight;
        double SPACE_TO_SHOW_EXTREME_VALUE = 1.0;

        if (xMinimum >= 0 && yMinimum >= 0) {
            contentWidth = (xMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (xMinimum - SPACE_TO_SHOW_EXTREME_VALUE);
            contentHeight = (yMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (yMinimum - SPACE_TO_SHOW_EXTREME_VALUE);

        } else if (xMinimum < 1 && yMinimum >= 0) {

            contentWidth = (xMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (xMinimum + SPACE_TO_SHOW_EXTREME_VALUE);
            contentHeight = (yMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (yMinimum + SPACE_TO_SHOW_EXTREME_VALUE);

        } else if (xMinimum >= 0 && yMinimum < 1) {

            contentWidth = (xMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (xMinimum - SPACE_TO_SHOW_EXTREME_VALUE);
            contentHeight = (yMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (yMinimum - SPACE_TO_SHOW_EXTREME_VALUE);
            xMinimum = 0;
        } else {

            contentWidth = (xMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (xMinimum - SPACE_TO_SHOW_EXTREME_VALUE);
            contentHeight = (yMaximum + SPACE_TO_SHOW_EXTREME_VALUE) - (yMinimum - SPACE_TO_SHOW_EXTREME_VALUE);
        }

        final double scaleWidth = getWidth() / contentWidth;
        final double scaleHeight = getHeight() / contentHeight;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = g2d.getTransform();
        g2d.translate(0 - scaleWidth * (xMinimum - SPACE_TO_SHOW_EXTREME_VALUE),
                scaleHeight * (yMaximum + SPACE_TO_SHOW_EXTREME_VALUE));
        g2d.scale(1, -1);
        if (flagPoints) {
            drawPoints(arrayX, arrayY, g, scaleWidth, scaleHeight);
        }
        if (flagLines) {
            drawLines(arrayX, arrayY, g, scaleWidth, scaleHeight);
        }
        g2d.setTransform(transform);
    }

    public void drawPoints(ArrayList<Double> xValues, ArrayList<Double> yValues, Graphics g, double scaleWidth, double scaleHeight) {

        for (int i = 0; i < xValues.size(); i++) {
            double getX = xValues.get(i);
            double getY = yValues.get(i);

            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);

            g.setColor(chooser);
            g.fillOval(x, y, getPointSize(), getPointSize());
        }
    }

    public void drawLines(ArrayList<Double> xValues, ArrayList<Double> yValues, Graphics g,
                          double scaleWidth, double scaleHeight) {
        for (int i = 0; i < xValues.size(); i++) {
            double getX = xValues.get(i);
            double getY = yValues.get(i);
            int OFFSET = 1;
            int CORRECTION = 2;
            if (i == xValues.size() - OFFSET) {

            } else {
                endpointX = xValues.get(i + OFFSET);
                endpointY = yValues.get(i + OFFSET);
            }
            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);
            int nextPointX = (int) (endpointX * scaleWidth);
            int nextPointY = (int) (endpointY * scaleHeight);
            int correctionX = getPointSize() / CORRECTION;
            int correctionY = getPointSize() / CORRECTION;
            g.setColor(Color.red);
            g.drawLine(x + correctionX, y + correctionY, nextPointX + correctionX, nextPointY + correctionY);
        }
    }

    public void addLines() {
        flagLines = true;
        repaint();
    }

    public void removeLines() {
        flagLines = false;
        repaint();
    }

    public void setPointSize(int number) {
        pointSize = number;
        repaint();
    }

    public int getPointSize() {
        return pointSize;
    }

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
        repaint();
    }

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
}

