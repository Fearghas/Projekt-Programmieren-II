import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
                    Formatloader loader = new TabDelimited();
                    loader = new TabDelimited();
                    loader.loadformat(pathname);
                    Format tab = loader.loadformat(pathname);
                    System.out.println(tab);
                    JFrame frame = new MenueBar();
                    frame.setLocationRelativeTo(null); // center on screen
                    TestDrawingPanel pain = new TestDrawingPanel(tab);
                    frame.add(pain);
                    frame.setVisible(true);
                }
                else if (pathname.contains("lin")) {
                    Formatloader loader;
                    loader = new RowDelimited();
                    loader.loadformat(pathname);
                    Format lin = loader.loadformat(pathname);
                    System.out.println(lin);
                    JFrame frame = new MenueBar();
                    frame.setLocationRelativeTo(null); // center on screen
                    TestDrawingPanel pain = new TestDrawingPanel(lin);
                    frame.add(pain);
                    frame.setVisible(true);
                }
                else
                {
                    System.out.println("Oh, oh... File is not supported!");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        closeItem.addActionListener((ActionEvent e) -> {
            //Programm schlieﬂen
            System.exit(0);
        });
    }


}