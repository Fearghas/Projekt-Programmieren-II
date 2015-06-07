package project.processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TabFormatScanner implements FormatReader {
    @Override
    public Storage readFormat(String fileName) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }

        ArrayList<String> arrayLabel = new ArrayList<String>();
        String[] numberOfVariables = fileScanner.nextLine().split("\\t");
        int totalVariables = 0;
        totalVariables = getTotalVariables(totalVariables, numberOfVariables);
        storeLabels(arrayLabel, totalVariables, numberOfVariables);
        ArrayList[] containerValues = createContainers(totalVariables);
        storeValues(fileScanner, totalVariables, containerValues);
        ArrayList<Variable> variablesList = createVariablesList(arrayLabel, totalVariables, containerValues);
        return new Storage(arrayLabel, variablesList);
    }

    private int getTotalVariables(int totalVariables, String[] numberOfVariables) {
        for (int i = 0; i <= numberOfVariables.length; i++) {
            totalVariables = i;
        }
        return totalVariables;
    }

    private void storeLabels(ArrayList<String> arrayLabel, int totalVariables, String[] numberOfVariables) {
        for (int i = 0; i < totalVariables; i++) {
            arrayLabel.add(numberOfVariables[i]);
        }
    }

    private ArrayList[] createContainers(int totalVariables) {
        ArrayList[] containerValues = new ArrayList[totalVariables];
        for (int i = 0; i < totalVariables; i++) {
            containerValues[i] = new ArrayList<Double>();
        }
        return containerValues;
    }

    private void storeValues(Scanner fileScanner, int totalVariables, ArrayList[] containerValues) {
        while (fileScanner.hasNext()) {
            String[] value = fileScanner.nextLine().split("\\t");
            for (int i = 0; i < totalVariables; i++) {
                containerValues[i].add(Double.parseDouble(value[i]));
            }
        }
    }

    private ArrayList<Variable> createVariablesList(ArrayList<String> arrayLabel, int totalVariables, ArrayList[] containerValues) {
        ArrayList<Variable> variablesList = new ArrayList();
        for (int i = 0; i < totalVariables; i++) {
            variablesList.add(createVariable(arrayLabel.get(i), containerValues[i]));
        }
        return variablesList;
    }

    private Variable createVariable(String string, ArrayList<Double> arraylist) {
        return new Variable(string, arraylist);
    }
}



