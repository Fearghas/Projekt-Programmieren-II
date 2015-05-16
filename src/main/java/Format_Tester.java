import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static javax.swing.JFileChooser.APPROVE_OPTION;

/**
 * Created by Briareus on 15/05/2015.
 */
public class Format_Tester
{
    public static void main(String[] args) throws IOException

    {

        JFileChooser chooser = new JFileChooser(); // JFileChooser-Objekt erstellen
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));  // Dialog zum Oeffnen von Dateien anzeigen
        int returnValue = chooser.showOpenDialog(null);

        if (returnValue == APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            String pathname = file.getCanonicalPath();
            if (pathname.contains("txt"))
            {
                Formatloader loader;
                loader = new tab_delimited();
                loader.loadformat(pathname);
                Format one = loader.loadformat(pathname);
                System.out.println(one);
            }
            else if (pathname.contains("lin"))
            {
                Formatloader loader;
                loader = new zeilen_delimited();
                loader.loadformat(pathname);
                Format two = loader.loadformat(pathname);
                System.out.println(two);
            }
            else
            {
                System.out.println("Oh, oh... File is not supported!");
            }
        }
        else if (returnValue == JFileChooser.CANCEL_OPTION)
        {

        }
    }
}
