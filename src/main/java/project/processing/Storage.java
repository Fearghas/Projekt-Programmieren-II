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

    public double calculateMaximum(ArrayList<Double> axis) {
        double maximum = axis.get(0);
        for (Double axisItem : axis) {
            if (axisItem > maximum) {
                maximum = axisItem;
            }
        }
        return maximum;
    }
}