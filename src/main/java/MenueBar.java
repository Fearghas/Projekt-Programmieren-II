import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;

/**
 * Created by Andreas on 24.05.2015.
 */
public class MenueBar extends JFrame
{
    //private final TestDrawingPanel datenmodell;
    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu editMenue;
    JMenuItem closeItem;
    JMenuItem pointItem;
    JMenuItem bigSize;

    protected MenueBar()
    {
        //this.datenmodell = datenmodell;
        this.setSize(600, 600);

        menueBar = new JMenuBar();
        fileMenue = new JMenu("Datei");
        editMenue = new JMenu("Punkte");

        // Menüpunkte  erzeuge
        closeItem = new JMenuItem("Schliessen");
        pointItem = new JMenuItem("Verbinden");
        bigSize = new JMenuItem("Gr\u00f6sse");

        // Menüpunkte dem Datei-Menü hinzufügen
        editMenue.add(pointItem);
        editMenue.add(bigSize);
        fileMenue.add(closeItem);

        //Datei-Menü  Menüleiste hinzufüg
        menueBar.add(fileMenue);
        menueBar.add(editMenue);

        //Menüleiste JFrame hinzufüg
        this.add(menueBar, BorderLayout.NORTH);

        pointItem.addActionListener(e ->{
        //Code hier für Punktverbindungen
        });

        bigSize.addActionListener(e ->{
            //Code hier für PunktGrösse einstellen
        });

    }


}
