import javax.swing.*;
import java.io.File;
import java.io.IOException;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Briareus on 15/05/2015.
 */
public class FormatTest
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
                loader = new TabDelimited();
                loader.loadformat(pathname);
                Format tab = loader.loadformat(pathname);
                System.out.println(tab);
                JFrame frame = new JFrame(pathname);
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // center on screen
                TestDrawingPanel pain = new TestDrawingPanel(tab);
                //pain.setLayout(new BorderLayout());
                frame.add(pain);
                frame.setVisible(true);


            }
            else if (pathname.contains("lin"))
            {
                Formatloader loader;
                loader = new RowDelimited();
                loader.loadformat(pathname);
                Format lin = loader.loadformat(pathname);
                System.out.println(lin);
                JFrame frame = new JFrame(pathname);
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // center on screen
                TestDrawingPanel pain = new TestDrawingPanel(lin);
                frame.add(pain);
                frame.setVisible(true);
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
