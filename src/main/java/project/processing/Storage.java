package project.processing;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> label;
    private ArrayList<Variable> list;

    public Storage(ArrayList<String> label, ArrayList<Variable> list) {
        this.label = label;
        this.list = list;
    }

    public ArrayList<String> getLabel() {
        return label;
    }

    public ArrayList<Variable> getValuesOfVariable() {
        return list;
    }

    public final double calculateMaximum(ArrayList<Double> axis) {
        final int INDEX_NUMBER = 0;
        double maximum = axis.get(INDEX_NUMBER);
        for (Double axisItem : axis) {
            if (axisItem > maximum) {
                maximum = axisItem;
            }
        }
        return maximum;
    }

    public final double calculateMinimum(ArrayList<Double> axis) {
        final int INDEX_NUMBER = 0;
        double minimum = axis.get(INDEX_NUMBER);
        for (int i = 0; i < axis.size(); i++) {
            if (axis.get(i) < minimum) {
                minimum = axis.get(i);
            }
        }
        return minimum;
    }

    public final int getTotalValues(ArrayList<Double> axis) {
        int count = 0;
        for (int i = 0; i <= axis.size(); i++) {
            count = i;
        }
        return count;
    }
}