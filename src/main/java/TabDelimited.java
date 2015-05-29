import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Briareus on 15/05/2015.
 */
public class TabDelimited implements Formatloader
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
        ArrayList<Double> dritteVariable = new ArrayList<Double>();
        ArrayList<Double> vierteVariable = new ArrayList<Double>();
        ArrayList<Double> fuenfteVariable = new ArrayList<Double>();
        ArrayList<Double> sechsteVariable = new ArrayList<Double>();
        ArrayList<Double> siebteVariable = new ArrayList<Double>();
        String ersterName, zweiterName, dritterName, vierterName, fuenfterName, sechsterName;
        Variable one, two, three, four, five;

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
        int variablenAnzahl = 0;
        String [] berechnungAnzahlVariablen = fileScanner.nextLine().split("\\t");
        for( int i = 0; i <= berechnungAnzahlVariablen.length; i++)
        {
            variablenAnzahl = i;
        }

        for (int i = 0; i < variablenAnzahl; i++)
        {
            beschriftung.add(berechnungAnzahlVariablen[i]);
        }

        while (fileScanner.hasNext())
        {
            String [] value = fileScanner.nextLine().split ("\\t");
            if (variablenAnzahl > 2)
            {
                ersteVariable.add(Double.parseDouble(value[0]));
                zweiteVariable.add(Double.parseDouble(value[1]));
                dritteVariable.add(Double.parseDouble(value[2]));
                vierteVariable.add(Double.parseDouble(value[3]));
                fuenfteVariable.add(Double.parseDouble(value[4]));
                sechsteVariable.add(Double.parseDouble(value[5]));
                //siebteVariable.add(Double.parseDouble(value[6])); keine Werte!
            }
            else
            {
                ersteVariable.add(Double.parseDouble(value[0]));
                zweiteVariable.add(Double.parseDouble(value[1]));
            }

        }
        ersterName = beschriftung.get(0);
        zweiterName = beschriftung.get(1);
        one = createVariable(ersterName, ersteVariable);
        two = createVariable(zweiterName, zweiteVariable);
        if (variablenAnzahl > 2)
        {
            zweiterName = beschriftung.get(2);
            dritterName = beschriftung.get(3);
            vierterName = beschriftung.get(4);
            fuenfterName = beschriftung.get(5);
            //sechsterName = beschriftung.get(6);
            three = createVariable(dritterName, dritteVariable);
            four = createVariable(vierterName, vierteVariable);
            five = createVariable(fuenfterName, fuenfteVariable);
            //Variable six = createVariable(ersterName, ersteVariable);
            //setChooseVariableX(two);
            //setChooseVariableY(three);
        }
        setChooseVariableX(one);
        setChooseVariableY(two);

        return new Format(getChooseVariableX(), getChooseVariableY());
    }


    public Variable createVariable(String a, ArrayList<Double> b)
    {
        return new Variable(a, b);
    }

}



