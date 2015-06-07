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
    private int pointSize;
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

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ArrayList arrayX = data.getValuesOfVariable().get(indexVariableX).getValues();
        ArrayList arrayY = data.getValuesOfVariable().get(indexVariableY).getValues();
        double xMaximum = data.calculateMaximum(arrayX);
        double xMinimum = data.calculateMinimum(arrayX);
        double yMaximum = data.calculateMaximum(arrayY);
        double yMinimum = data.calculateMinimum(arrayY);
        double contentWidth, contentHeight;
        final double ADD_SPACE_NEAR_BORDER = 1.0;

        if (xMinimum >= 0 && yMinimum >= 0) {
            contentWidth = (xMaximum + ADD_SPACE_NEAR_BORDER) - (xMinimum - ADD_SPACE_NEAR_BORDER);
            contentHeight = (yMaximum + ADD_SPACE_NEAR_BORDER) - (yMinimum - ADD_SPACE_NEAR_BORDER);

        } else if (xMinimum < 1 && yMinimum >= 0) {

            contentWidth = (xMaximum + ADD_SPACE_NEAR_BORDER) - (xMinimum + ADD_SPACE_NEAR_BORDER);
            contentHeight = (yMaximum + ADD_SPACE_NEAR_BORDER) - (yMinimum + ADD_SPACE_NEAR_BORDER);

        } else if (xMinimum >= 0 && yMinimum < 1) {

            contentWidth = (xMaximum + ADD_SPACE_NEAR_BORDER) - (xMinimum - ADD_SPACE_NEAR_BORDER);
            contentHeight = (yMaximum + ADD_SPACE_NEAR_BORDER) - (yMinimum - ADD_SPACE_NEAR_BORDER);
            xMinimum = 0;
        } else {

            contentWidth = (xMaximum + ADD_SPACE_NEAR_BORDER) - (xMinimum - ADD_SPACE_NEAR_BORDER);
            contentHeight = (yMaximum + ADD_SPACE_NEAR_BORDER) - (yMinimum - ADD_SPACE_NEAR_BORDER);
        }

        double scaleWidth = getWidth() / contentWidth;
        double scaleHeight = getHeight() / contentHeight;
        Graphics2D axis = (Graphics2D) graphics;
        AffineTransform transform = axis.getTransform();
        axis.translate(0 - scaleWidth * (xMinimum - ADD_SPACE_NEAR_BORDER),
                scaleHeight * (yMaximum + ADD_SPACE_NEAR_BORDER));
        axis.scale(1, -1);
        if (flagPoints) {
            drawPoints(arrayX, arrayY, graphics, scaleWidth, scaleHeight);
        }
        if (flagLines) {
            drawLines(arrayX, arrayY, graphics, scaleWidth, scaleHeight);
        }
        axis.setTransform(transform);
    }

    public void drawPoints(ArrayList<Double> xValues, ArrayList<Double> yValues,
                           Graphics points, double scaleWidth, double scaleHeight) {

        for (int i = 0; i < xValues.size(); i++) {
            double getX = xValues.get(i);
            double getY = yValues.get(i);
            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);

            points.setColor(chooser);
            points.fillOval(x, y, getPointSize(), getPointSize());
        }
    }

    public void drawLines(ArrayList<Double> xValues, ArrayList<Double> yValues, Graphics points,
                          double scaleWidth, double scaleHeight) {
        for (int i = 0; i < xValues.size(); i++) {
            double getX = xValues.get(i);
            double getY = yValues.get(i);
            int OFFSET = 1;
            int CORRECTION_TO_MIDDLE = 2;
            if (i == xValues.size() - OFFSET) {

            } else {
                endpointX = xValues.get(i + OFFSET);
                endpointY = yValues.get(i + OFFSET);
            }
            int x = (int) (getX * scaleWidth);
            int y = (int) (getY * scaleHeight);
            int nextPointX = (int) (endpointX * scaleWidth);
            int nextPointY = (int) (endpointY * scaleHeight);
            int correctionX = getPointSize() / CORRECTION_TO_MIDDLE;
            int correctionY = getPointSize() / CORRECTION_TO_MIDDLE;

            points.setColor(Color.red);
            points.drawLine(x + correctionX, y + correctionY, nextPointX + correctionX, nextPointY + correctionY);
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
}

