package drawing;
import loadformat.Format;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Briareus on 19.05.2015.
 */
public class ScatterplotDrawingPanel extends JPanel {
    private Format DataModel;
    private int pointSize;
    private Color chooser;
    private double endPointX;
    private double endPointY;
    private int indexVariableX = 0;
    private int indexVariableY = 1;
    private boolean flagPoints = true;
    private boolean flagLines = false;

    public ScatterplotDrawingPanel(Format DataModel) {
        this.DataModel = DataModel;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Double> arrayX = DataModel.getList().get(indexVariableX).getValues();
        ArrayList<Double> arrayY = DataModel.getList().get(indexVariableY).getValues();
        double xMaximum = calculateMaximum(arrayX);
        double xMinimum = calculateMinimum(arrayX);
        double yMaximum = calculateMaximum(arrayY);
        double yMinimum = calculateMinimum(arrayY);
        double contentWidth;
        double contentHeight;

        if (xMinimum >= 0 && yMinimum >= 0) {
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
        }
        else if (xMinimum < 1 && yMinimum >= 0) {
            contentWidth = (xMaximum + 1) - (xMinimum + 1);
            contentHeight = (yMaximum + 1) - (yMinimum + 1);

        }
        else if (xMinimum >= 0 && yMinimum < 1) {
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
            xMinimum = 0;
        }
        else {
            contentWidth = (xMaximum + 1) - (xMinimum - 1);
            contentHeight = (yMaximum + 1) - (yMinimum - 1);
        }

        final double scaleWidth = getWidth() / contentWidth;
        final double scaleHeight = getHeight() / contentHeight;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();
        g2d.translate(0 - scaleWidth * (xMinimum - 1),  +scaleHeight * (yMaximum + 1));
        g2d.scale(1, -1);

        if (flagPoints) {
            drawPoints(arrayX, arrayY, g, scaleWidth, scaleHeight);
        }
        if (flagLines) {
            drawLines(arrayX, arrayY, g, scaleWidth, scaleHeight);
        }

        g2d.setTransform(at);
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

    public void drawLines(ArrayList<Double> xValues, ArrayList<Double> yValues, Graphics g, double scaleWidth, double scaleHeight) {
        for (int i = 0; i < xValues.size(); i++) {
            double getX = xValues.get(i);
            double getY = yValues.get(i);
            if (i == xValues.size() - 1)
             {
                endPointX = xValues.get(i + 1);
                endPointY = yValues.get(i + 1);
            }
            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);
            int nextPointX = (int) (endPointX * scaleWidth);
            int nextPointY = (int) (endPointY * scaleHeight);
            int correctionX = getPointSize() / 2;
            int correctionY = getPointSize() / 2;
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

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int number) {
        pointSize = number;
        repaint();
    }

    public void setPlotColor() {
        JColorChooser color = new JColorChooser(Color.BLUE);
        ActionListener okActionListener = (e -> chooser = color.getColor());
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
        for (Double axisSize : axis) {
            if (axisSize > maximum) {
                maximum = axisSize;
            }
        }
        return maximum;
    }

    public double calculateMinimum(ArrayList<Double> axis) {
        double minimum = axis.get(0);
        for (Double axisSize : axis) {
            if (axisSize < minimum) {
                minimum = axisSize;
            }
        }
        return minimum;
    }
}
