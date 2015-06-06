package loadformat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TabFormatScanner implements FormatReader
{
    @Override
    public Storage readFormat(String fileName)
    {
        Scanner fileScanner;
        try
        {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        ArrayList<String> arrayLabel = new ArrayList<>();
        int TotalVariables = 0;
        String [] numberOfVariables = fileScanner.nextLine().split("\\t");
        TotalVariables = getTotalVariables(TotalVariables, numberOfVariables);
        storeLabels(arrayLabel, TotalVariables, numberOfVariables);
        ArrayList[] containerValues = createContainers(TotalVariables);
        storeValues(fileScanner, TotalVariables, containerValues);
        ArrayList<Variable> variablesList = createVariablesList(arrayLabel, TotalVariables, containerValues);
        return new Storage(arrayLabel, variablesList);
    }

    private ArrayList<Variable> createVariablesList(ArrayList<String> arrayLabel, int totalVariables, ArrayList[] containerValues) {
        ArrayList<Variable> variablesList = new ArrayList();
        for (int i = 0; i < totalVariables; i++)
        {
            variablesList.add(createVariable(arrayLabel.get(i), containerValues[i]));
        }
        return variablesList;
    }

    private void storeValues(Scanner fileScanner, int totalVariables, ArrayList[] containerValues) {
        while (fileScanner.hasNext())
        {
            String[] value = fileScanner.nextLine().split ("\\t");
            for (int i = 0; i < totalVariables; i++)
            {
                containerValues[i].add(Double.parseDouble(value[i]));
            }
        }
    }

    private ArrayList[] createContainers(int totalVariables) {
        ArrayList[] containerValues = new ArrayList[totalVariables];
        for (int i = 0; i < totalVariables; i++)
        {
            containerValues[i] = new ArrayList<Double>();
        }
        return containerValues;
    }

    private void storeLabels(ArrayList<String> arrayLabel, int totalVariables, String[] numberOfVariables) {
        for (int i = 0; i < totalVariables; i++)
        {
            arrayLabel.add(numberOfVariables[i]);
        }
    }

    private int getTotalVariables(int totalVariables, String[] numberOfVariables) {
        for( int i = 0; i <= numberOfVariables.length; i++)
        {
            totalVariables = i;
        }
        return totalVariables;
    }


    public Variable createVariable(String string, ArrayList<Double> arraylist)
    {
        return new Variable(string, arraylist);
    }
}



