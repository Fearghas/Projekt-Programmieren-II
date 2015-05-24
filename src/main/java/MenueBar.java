import javax.swing.*;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andreas on 24.05.2015.
 */
public class MenueBar extends JFrame {
    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu editMenue;
    JMenuItem closeItem;
    JMenuItem pointItem;
    JMenuItem bigSize;

    protected MenueBar (){

        this.setTitle("Scatter-App");
        this.setSize(600, 600);

        menueBar = new JMenuBar();
        fileMenue = new JMenu("Datei");
        editMenue = new JMenu("Punkte");

        // Menüpunkte  erzeuge
        closeItem = new JMenuItem("Schliessen");
        pointItem = new JMenuItem("Verbinden");
        bigSize = new JMenuItem("Vergr\u00f6ssern");

        // Menüpunkte dem Datei-Menü hinzufügen
        editMenue.add(pointItem);
        editMenue.add(bigSize);
        fileMenue.add(closeItem);


        //Datei-Menü  Menüleiste hinzufüg
        menueBar.add(fileMenue);
        menueBar.add(editMenue);

        //Menüleiste JFrame hinzufüg
        this.add(menueBar, BorderLayout.NORTH);
    }
}
