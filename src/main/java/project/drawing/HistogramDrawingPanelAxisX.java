package project.drawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import project.processing.Storage;

public class HistogramDrawingPanelAxisX extends JPanel {
    private final Storage data;
    private int indexVariableX;

    public HistogramDrawingPanelAxisX(Storage data) {
        this.data = data;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Double> arrayX = data.getValuesOfVariable().get(indexVariableX).getValues();
        double xMaximum = data.calculateMaximum(arrayX);
        double xMinimum = data.calculateMinimum(arrayX);
        int totalValues = data.getTotalValues(arrayX);
        int howManyBins;

        howManyBins = getHowManyBins(totalValues);
        ArrayList<Double> arrayFrequency = createBins(arrayX, xMaximum, xMinimum, totalValues, howManyBins);
        drawBins(g, howManyBins, arrayFrequency);
    }

    private void drawBins(Graphics g, int howManyBins, ArrayList<Double> arrayFrequency) {
        double contentHeight;
        int barWidth = getWidth() / howManyBins;
        contentHeight = data.calculateMaximum(arrayFrequency) + data.calculateMinimum(arrayFrequency);
        for (int i = 0; i < arrayFrequency.size(); i++) {
            double getX = arrayFrequency.get(i);
            int barHeight = (int) (getX * (getHeight() / contentHeight));
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g.setColor(Color.RED);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
    }

    private ArrayList<Double> createBins(ArrayList<Double> arrayX, double xMaximum,
                                         double xMinimum, int totalValues, int howManyBins) {
        double intervalLength = ((xMaximum - xMinimum) / howManyBins);
        final double START_VALUE_FREQUENCY = 0.0;
        double frequency = START_VALUE_FREQUENCY;
        double nextInterval = xMinimum + intervalLength;
        ArrayList<Double> arrayFrequency = new ArrayList<Double>();
        for (int i = 0; i < howManyBins; i++) {
            for (int a = 0; a < totalValues; a++) {
                double z = arrayX.get(a);
                if (z >= xMinimum && z < nextInterval) {
                    frequency++;
                }
            }
            arrayFrequency.add(frequency);
            frequency = START_VALUE_FREQUENCY;
            xMinimum = nextInterval;
            nextInterval = xMinimum + intervalLength;
        }
        return arrayFrequency;
    }

    private int getHowManyBins(int totalValues) {
        int howManyBins;
        final int TRESHOLD_VALUE = 500;
        final int BINS_IF_OVER_TRESHOLD = 20;//aus http://de.wikipedia.org/wiki/Histogramm
        if (totalValues <= TRESHOLD_VALUE) {
            howManyBins = (int) Math.round(Math.sqrt(totalValues));
        } else {
            howManyBins = BINS_IF_OVER_TRESHOLD;
        }
        return howManyBins;
    }

    public void setIndexVariableX(int indexNumber) {
        indexVariableX = indexNumber;
        repaint();
    }

    public void setIndexVariableY(int indexNumber) {
        int indexVariableY = indexNumber;
        repaint();
    }

    public String getXlabel(int indexNumber) {
        indexVariableX = indexNumber;
        String xLabel = data.getValuesOfVariable().get(indexVariableX).getName();
        return xLabel;
    }
}