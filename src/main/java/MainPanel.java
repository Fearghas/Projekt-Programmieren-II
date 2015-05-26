import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class MainPanel extends JFrame {
    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu editMenue;
    JMenuItem openItem;
    JMenuItem closeItem;

    protected MainPanel() {
        this.setTitle("Scattergramm und Histogramm Applikation");
        this.setSize(500, 300);

        menueBar = new JMenuBar();
        fileMenue = new JMenu("Datei");
        editMenue = new JMenu("Bearbeiten");

        // Men¸punkte  erzeuge
        openItem = new JMenuItem("\u00d6ffnen");
        closeItem = new JMenuItem("Schliessen");

        // Men¸punkte dem Datei-Men¸ hinzuf¸gen
        fileMenue.add(openItem);
        fileMenue.add(closeItem);

        //Datei-Men¸  Men¸leiste hinzuf¸g
        menueBar.add(fileMenue);
        menueBar.add(editMenue);

        //Men¸leiste JFrame hinzuf¸g
        this.add(menueBar, BorderLayout.NORTH);


        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));  // Dialog zum Oeffnen von Dateien anzeigen
           int returnValue= fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            try {
                String pathname = file.getCanonicalPath();
                if (pathname.contains("txt"))
                {
                    Formatloader loader = new TabDelimited();//Loader, der benutzt wird
                    Format tab = loader.loadformat(pathname);//Loader, der benutzt wird auf File benutzt
                    System.out.println(tab);
                    JFrame frame = new JFrame(pathname);
                    //JFrame histogramm = new JFrame(pathname);

                    //histogramm.setSize(800, 800);
                    //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    //histogramm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    //frame = new MenueBar(); //erstellt Taskleiste im Scatterplot
                    DrawingPanel pain = new DrawingPanel(tab);
                    frame.add(pain);
                    frame.setSize(500, 500);
                    frame.setLocationRelativeTo(null); // center on screen
                    //Histogramm horror = new Histogramm(tab);
                    //histogramm.add(horror);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    //histogramm.setVisible(true);
                }
                else if (pathname.contains("lin")) {
                    /*Formatloader loader = new RowDelimited();
                    Format lin = loader.loadformat(pathname);
                    System.out.println(lin);
                    JFrame frame = new MenueBar();
                    frame.setLocationRelativeTo(null); // center on screen
                    DrawingPanel pain = new DrawingPanel(lin);
                    frame.add(pain);
                    frame.setVisible(true);*/
                }
                else
                {
                    System.out.println("Oh, oh... File is not supported!");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        closeItem.addActionListener(e -> {
            //Programm schlieﬂen
            System.exit(0);
        });
    }


}