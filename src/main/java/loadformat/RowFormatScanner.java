package loadformat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RowFormatScanner implements FormatReader
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
            String message = "File not found.";
            return null;
        }

        int TotalVariables = Integer.parseInt(fileScanner.nextLine());
        ArrayList<String> arrayLabel = new ArrayList<String>();
        storeLabels(arrayLabel, fileScanner, TotalVariables);
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
        String delimiter = fileScanner.nextLine();
        while (fileScanner.hasNextLine())
        {
            for (int a = 0; a < totalVariables; a++)
            {
                String[] value = fileScanner.nextLine().split(delimiter);
                for (int i = 0; i < value.length; i++)
                {
                    containerValues[a].add(Double.parseDouble(value[i]));
                }
            }
        }
    }

    private ArrayList[] createContainers(int totalVariables) {
        ArrayList[] temporaryValuesList = new ArrayList[totalVariables];
        for (int i = 0; i < totalVariables; i++)
        {
            temporaryValuesList[i] = new ArrayList<Double>();
        }
        return temporaryValuesList;
    }

    private void storeLabels(ArrayList<String> arrayLabel, Scanner fileScanner, int totalVariables) {
        for (int i = 0; i < totalVariables; i++)
        {
            String numberOfVariables = fileScanner.nextLine();
            arrayLabel.add(numberOfVariables);
        }
    }

    public Variable createVariable(String a, ArrayList<Double> b)
    {
        return new Variable(a, b);
    }
}




