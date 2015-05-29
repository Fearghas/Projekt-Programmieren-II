import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Briareus on 16.05.2015.
 */
public class RowDelimited implements Formatloader
{
    private Variable chooseVariableX;
    private Variable chooseVariableY;

    public Variable getChooseVariableX() {
        return chooseVariableX;
    }

    public void setChooseVariableX(Variable chooseVariableX) {
        this.chooseVariableX = chooseVariableX;
    }

    public Variable getChooseVariableY() {
        return chooseVariableY;
    }

    public void setChooseVariableY(Variable chooseVariableY) {
        this.chooseVariableY = chooseVariableY;
    }

    @Override
    public Format loadformat(String fileName)
    {
        //Container vorbereiten für Achsenname, x-Werte und y-Werte
        ArrayList<String> beschriftung = new ArrayList<String>();
        ArrayList<Double> ersteVariable = new ArrayList<Double>();
        ArrayList<Double> zweiteVariable = new ArrayList<Double>();
        String ersterName, zweiterName;
        Variable one, two;

        //Initialisierung Scanner
        Scanner fileScanner;
        try
        {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        //alle Rohdaten trennen und im Container speichern
        String fetchVariable = fileScanner.nextLine();
        int variablenAnzahl = Integer.parseInt(fetchVariable);

        for (int i = 0; i < variablenAnzahl; i++)
        {
            String berechnungAnzahlVariablen = fileScanner.nextLine();
            beschriftung.add(berechnungAnzahlVariablen);
        }

        //get delimiter character
        String delimiter = fileScanner.nextLine();

        //scan values and store in array
        String [] value = fileScanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            ersteVariable.add(Double.parseDouble(value[i]));
        }

        String [] value2 = fileScanner.nextLine().split(delimiter);
        for (int i = 0; i < value.length; i++)
        {
            zweiteVariable.add(Double.parseDouble(value2[i]));
        }
        ersterName = beschriftung.get(0);
        zweiterName = beschriftung.get(1);
        one = createVariable(ersterName, ersteVariable);
        two = createVariable(zweiterName, zweiteVariable);

        setChooseVariableX(one);
        setChooseVariableY(two);

        return new Format(getChooseVariableX(), getChooseVariableY());

    }
    public Variable createVariable(String a, ArrayList<Double> b)
    {
        return new Variable(a, b);
    }

}




