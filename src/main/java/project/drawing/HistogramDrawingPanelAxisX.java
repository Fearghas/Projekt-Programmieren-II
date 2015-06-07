package project.drawing;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import project.processing.Storage;

public class HistogramDrawingPanelAxisX extends JPanel
{
    private Storage data;
    private int indexVariableX;
    private int indexVariableY;

    public HistogramDrawingPanelAxisX(Storage data) {
        this.data = data;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Double> arrayX = data.getValuesOfVariable().get(indexVariableX).getValues();
        double xMaximum = calculateMaximum(arrayX);
        double xMinimum = calculateMinimum(arrayX);
        int totalValues = getTotalValues(arrayX);
        int howManyBins;

        howManyBins = getHowManyBins(totalValues);
        ArrayList<Double> arrayFrequency = createBins(arrayX, xMaximum, xMinimum, totalValues, howManyBins);
        drawBins(g, howManyBins, arrayFrequency);
    }

    private void drawBins(Graphics g, int howManyBins, ArrayList<Double> arrayFrequency) {
        double contentHeight;
        int barWidth = getWidth() / howManyBins;
        contentHeight = calculateMaximum(arrayFrequency) + calculateMinimum(arrayFrequency);
        for (int i = 0; i < arrayFrequency.size(); i++) {
            double x_1 = arrayFrequency.get(i);
            int barHeight = (int) (x_1 * (getHeight() / contentHeight));
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
        double modulus = ((xMaximum - xMinimum) / howManyBins);
        double frequency = 0.0;
        double upperLimitInterval = xMinimum + modulus;
        ArrayList<Double> arrayFrequency = new ArrayList<>();
        for (int i = 0; i < howManyBins; i++) {
            for (int a = 0; a < totalValues; a++) {
                double z = arrayX.get(a);
                if (z >= xMinimum && z < upperLimitInterval) {
                    frequency++;
                }
            }
            arrayFrequency.add(frequency);
            frequency = 0.0;
            xMinimum = upperLimitInterval;
            upperLimitInterval = xMinimum + modulus;
        }
        return arrayFrequency;
    }

    private int getHowManyBins(int totalValues) {
        int howManyBins;
        if (totalValues <= 500) {
            howManyBins = (int) Math.round(Math.sqrt(totalValues));
        } else {
            howManyBins = 20; //aus Wikipedia entnommen, wenn Anzahl Werte > 500
        }
        return howManyBins;
    }

    public void setIndexVariableX(int indexNumber) {
        indexVariableX = indexNumber;
        repaint();
    }

    public void setIndexVariableY(int indexNumber) {
        indexVariableY = indexNumber;
        repaint();
    }

    public String getXlabel(int indexNumber) {
        indexVariableX = indexNumber;
        String ylabel = data.getValuesOfVariable().get(indexVariableX).getName();
        return ylabel;
    }

    public double calculateMaximum(ArrayList<Double> axis) {
        double maximum = axis.get(0);
        for (Double axisItem : axis) {
            if (axisItem > maximum) {
                maximum = axisItem;
            }
        }
        return maximum;
    }

    public double calculateMinimum(ArrayList<Double> axis) {
        double minimum = axis.get(0);
        for (Double axisItem : axis) {
            if (axisItem < minimum) {
                minimum = axisItem;
            }
        }
        return minimum;
    }

    public int getTotalValues(ArrayList<Double> axis) {
        int count = 0;
        for (int i = 0; i <= axis.size(); i++) {
            count = i;
        }
        return count;
    }
}