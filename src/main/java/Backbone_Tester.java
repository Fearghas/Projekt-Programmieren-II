import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Backbone_Tester
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //create reference table
        ArrayList<Backbone> test = new ArrayList<Backbone>();

        Scanner test_Scanner = new Scanner(new File("c:\\mfg\\data.txt"));

        while (test_Scanner.hasNext())
        {
            //String x_Achse = test_Scanner.nextLine(); => if else bedingung erste zeile oder zweite zeile, dann nextLine und split
            //String y_Achse = x_Achse;
            double x_Wert = test_Scanner.nextDouble();
            double y_Wert = test_Scanner.nextDouble();
            Backbone paar = new Backbone(/*x_Achse, y_Achse,*/ x_Wert, y_Wert);
            test.add(paar);
        }

        //2 neue Array Listen für x_Spalte und y_Spalte
        ArrayList<Double> x_Spalte = new ArrayList<Double>();
        ArrayList<Double> y_Spalte = new ArrayList<Double>();

        //reference table anzeigen lassen
        for (int i = 0; i < test.size(); i++)
        {
            Backbone backbone = test.get(i);
            x_Spalte.add(backbone.getX_Wert());
            y_Spalte.add(backbone.getY_Wert());
            //System.out.println(backbone.getX_Wert());
            //System.out.println(backbone.getX_Achse() + "\n" + backbone.getX_Wert() + " " + backbone.getY_Wert());
        }
        //System.out.println(x_Spalte.get(0));
        //System.out.println(y_Spalte.get(0));

        //maximum Wert herausfinden für x_Spalte und y_Spalte
        double largest = x_Spalte.get(0);
        for (int i = 0; i < x_Spalte.size(); i++)
        {
            if (x_Spalte.get(i) > largest)
            {
                largest = x_Spalte.get(i);
            }
        }
        System.out.println(largest);

        //minimum Wert herausfinden für x_Spalte und y_Spalte
        double minimum = x_Spalte.get(0);
        for (int i = 0; i < x_Spalte.size(); i++)
        {
            if (x_Spalte.get(i) < minimum)
            {
                minimum = x_Spalte.get(i);
            }
        }
        System.out.println(minimum);

    }
}
