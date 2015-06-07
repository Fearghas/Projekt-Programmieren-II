package project.drawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import project.processing.Storage;

public class HistogramDrawingPanelAxisY extends JPanel {
    private final Storage data;
    private int indexVariableY;

    public HistogramDrawingPanelAxisY(Storage data) {
        this.data = data;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Double> arrayY = data.getValuesOfVariable().get(indexVariableY).getValues();
        double yMaximum = data.calculateMaximum(arrayY);
        double yMinimum = data.calculateMinimum(arrayY);
        int totalValues = data.getTotalValues(arrayY);
        int howManyBins;

        howManyBins = getHowManyBins(totalValues);
        ArrayList<Double> arrayFrequency = createBins(arrayY, yMaximum, yMinimum, totalValues, howManyBins);
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

            g.setColor(Color.WHITE);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
    }

    private ArrayList<Double> createBins(ArrayList<Double> arrayY, double yMaximum,
                                         double yMinimum, int totalValues, int howManyBins) {
        double intervalLength = ((yMaximum - yMinimum) / howManyBins);
        final double START_VALUE_FREQUENCY = 0.0;
        double frequency = START_VALUE_FREQUENCY;
        double nextInterval = yMinimum + intervalLength;
        ArrayList<Double> arrayFrequency = new ArrayList<Double>();
        for (int i = 0; i < howManyBins; i++) {
            for (int a = 0; a < totalValues; a++) {
                double z = arrayY.get(a);
                if (z >= yMinimum && z < nextInterval) {
                    frequency++;
                }
            }
            arrayFrequency.add(frequency);
            frequency = START_VALUE_FREQUENCY;
            yMinimum = nextInterval;
            nextInterval = yMinimum + intervalLength;
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
        int indexVariableX = indexNumber;
        repaint();
    }

    public void setIndexVariableY(int indexNumber) {
        indexVariableY = indexNumber;
        repaint();
    }

    public String getYlabel(int indexNumber) {
        indexVariableY = indexNumber;
        String yLabel = data.getValuesOfVariable().get(indexVariableY).getName();
        return yLabel;
    }
}