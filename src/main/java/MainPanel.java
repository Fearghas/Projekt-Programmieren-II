import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class MainPanel extends JFrame {
    //DrawingPanel plot;
    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu optionsOne;
    JMenuItem openItem;
    JMenuItem closeItem;
    private Color chooser;

    protected MainPanel() {
        this.setTitle("Scattergramm und Histogramm Applikation");
        this.setSize(200, 200);

        menueBar = new JMenuBar();
        fileMenue = new JMenu("Datei");
        optionsOne = new JMenu("Bearbeiten");

        // Menüpunkte  erzeuge
        openItem = new JMenuItem("\u00d6ffnen");
        closeItem = new JMenuItem("Schliessen");

        // Menüpunkte dem Datei-Menü hinzufügen
        fileMenue.add(openItem);
        fileMenue.add(closeItem);

        //Datei-Menü  Menüleiste hinzufüg
        menueBar.add(fileMenue);
        menueBar.add(optionsOne);

        //Menüleiste JFrame hinzufüg
        this.add(menueBar, BorderLayout.NORTH);


        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));  // Dialog zum Oeffnen von Dateien anzeigen
            int returnValue = fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            try {
                String pathname = file.getCanonicalPath();
                if (pathname.contains("txt")) {
                    Formatloader loader = new TabDelimited();//Loader, der benutzt wird
                    Format tab = loader.loadformat(pathname);//Loader, der benutzt wird auf File benutzt
                    System.out.println(tab);
                    JFrame scatterplotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(tab);
                    plot.setPointSize(5);   //default point size
                    scatterplotFrame.add(plot);
                    addOptionsBar(scatterplotFrame, plot);
                    scatterplotFrame.setSize(500, 500);
                    scatterplotFrame.setLocationRelativeTo(null); // center on screen
                    scatterplotFrame.setVisible(true);
                    scatterplotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                    //Histogramm aufrufen für eine Variable
                    JFrame histogramFrame = new JFrame(pathname);
                    HistogramDrawingPanel xPlot = new HistogramDrawingPanel(tab);
                    histogramFrame.add(xPlot);
                    histogramFrame.setTitle(tab.getxName());
                    histogramFrame.setSize(250, 250);
                    histogramFrame.setVisible(true);
                    histogramFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                    //histogramm.setVisible(true);
                }
                else if (pathname.contains("lin")) {
                    Formatloader loader = new RowDelimited();
                    Format lin = loader.loadformat(pathname);
                    System.out.println(lin);
                    JFrame scatterplotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(lin);
                    plot.setPointSize(5);   //default point size
                    scatterplotFrame.add(plot);
                    addOptionsBar(scatterplotFrame, plot);
                    scatterplotFrame.setSize(500, 500);
                    scatterplotFrame.setLocationRelativeTo(null); // center on screen
                    scatterplotFrame.setVisible(true);
                    scatterplotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                    //Histogramm aufrufen für eine Variable
                    JFrame histogramFrame = new JFrame(pathname);
                    HistogramDrawingPanel xPlot = new HistogramDrawingPanel(lin);
                    histogramFrame.add(xPlot);
                    histogramFrame.setTitle(lin.getxName());
                    histogramFrame.setSize(250, 250);
                    histogramFrame.setVisible(true);
                    histogramFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    System.out.println("Oh, oh... File is not supported!");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        closeItem.addActionListener(e -> {
            //Programm schließen
            System.exit(0);
        });
    }

    private void addOptionsBar(JFrame frame, ScatterplotDrawingPanel area) {
        JMenuBar optionsBar;
        JMenu optionsOne;
        JMenu optionsTwo;
        JMenu optionsThree;
        JMenuItem connectPoints;
        JMenuItem removePoints;
        JMenuItem one, two, three, four, five;
        JMenu pointSize;
        JMenuItem changeColor;

        //Initialisierung
        optionsBar = new JMenuBar();
        optionsOne = new JMenu("Dots");
        optionsTwo = new JMenu("Size");
        optionsThree = new JMenu("Color");

        // Menüpunkte  erzeugen
        connectPoints = new JMenuItem("Connect");
        removePoints = new JMenuItem("Unconnect");
        pointSize = new JMenu("Pixel");
        changeColor = new JMenuItem("Choose");
        one = new JMenuItem("2");
        two = new JMenuItem("3");
        three = new JMenuItem("5");
        four = new JMenuItem("7");
        five = new JMenuItem("10");

        // Menüpunkte dem Datei-Menü hinzufügen
        optionsOne.add(connectPoints);
        optionsOne.add(removePoints);
        optionsTwo.add(pointSize);
        pointSize.add(one);
        pointSize.add(two);
        pointSize.add(three);
        pointSize.add(four);
        pointSize.add(five);
        optionsThree.add(changeColor);


        //Datei-Menü  Menüleiste hinzufüg
        optionsBar.add(optionsOne);
        optionsBar.add(optionsTwo);
        optionsBar.add(optionsThree);

        //Menüleiste JFrame hinzufüg
        frame.add(optionsBar, BorderLayout.NORTH);

        //Listener
        connectPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.addLines();
            }
        });

        removePoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.removeLines();
            }
        });

        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPointSize(2);

            }
        });

        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPointSize(3);

            }
        });

        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPointSize(5);

            }
        });

        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPointSize(7);
            }
        });

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPointSize(10);
            }
        });

        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setPlotColor();

            }
        });
    }
}

