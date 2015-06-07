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
}