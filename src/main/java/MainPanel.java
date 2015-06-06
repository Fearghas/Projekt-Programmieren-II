import drawing.HistogramDrawingPanelForXaxis;
import drawing.HistogramDrawingPanelForYaxis;
import drawing.ScatterplotDrawingPanel;
import loadformat.Format;
import loadformat.Formatloader;
import loadformat.RowDelimited;
import loadformat.TabDelimited;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class MainPanel extends JFrame
{

    JMenuBar menueBar;
    JMenu fileMenue;
    JMenu optionsOne;
    JMenuItem openItem;
    JMenuItem closeItem;
    JFrame histogramFrameXaxis;
    JFrame histogramFrameYaxis;


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
                    JFrame scatterplotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(tab);
                    HistogramDrawingPanelForXaxis xPlot = new HistogramDrawingPanelForXaxis(tab);
                    HistogramDrawingPanelForYaxis yPlot = new HistogramDrawingPanelForYaxis(tab);
                    plot.setPointSize(3);//default point size
                    scatterplotFrame.add(plot);
                    addOptionsBar(scatterplotFrame, plot, xPlot, yPlot, tab.getLabel(), tab);
                    scatterplotFrame.setSize(500, 500);
                    scatterplotFrame.setLocationRelativeTo(null); // center on screen
                    scatterplotFrame.setVisible(true);
                    scatterplotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    histogramFrameXaxis = new JFrame();
                    histogramFrameYaxis = new JFrame();
                    histogramFrameXaxis.add(xPlot);
                    histogramFrameYaxis.add(yPlot);
                    histogramFrameXaxis.setTitle(xPlot.getXlabel(0));
                    histogramFrameYaxis.setTitle(yPlot.getYlabel(1));
                    histogramFrameXaxis.setSize(250, 250);
                    histogramFrameXaxis.setVisible(true);
                    histogramFrameXaxis.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    histogramFrameYaxis.setSize(250, 250);
                    histogramFrameYaxis.setVisible(true);
                    histogramFrameYaxis.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
                    else if (pathname.contains("lin")) {
                    Formatloader loader = new RowDelimited();
                    Format lin = loader.loadformat(pathname);
                    JFrame scatterplotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(lin);
                    HistogramDrawingPanelForXaxis xPlot = new HistogramDrawingPanelForXaxis(lin);
                    HistogramDrawingPanelForYaxis yPlot = new HistogramDrawingPanelForYaxis(lin);
                    plot.setPointSize(3);   //default point size
                    scatterplotFrame.add(plot);
                    addOptionsBar(scatterplotFrame, plot, xPlot, yPlot, lin.getLabel(), lin);
                    scatterplotFrame.setSize(500, 500);
                    scatterplotFrame.setLocationRelativeTo(null); // center on screen
                    scatterplotFrame.setVisible(true);
                    scatterplotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    histogramFrameXaxis = new JFrame();
                    histogramFrameYaxis = new JFrame();
                    histogramFrameXaxis.add(xPlot);
                    histogramFrameYaxis.add(yPlot);
                    histogramFrameXaxis.setTitle(xPlot.getXlabel(0));
                    histogramFrameYaxis.setTitle(yPlot.getYlabel(1));
                    histogramFrameXaxis.setSize(250, 250);
                    histogramFrameXaxis.setVisible(true);
                    histogramFrameXaxis.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    histogramFrameYaxis.setSize(250, 250);
                    histogramFrameYaxis.setVisible(true);
                    histogramFrameYaxis.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
                else {
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

    private void addOptionsBar(JFrame frame, ScatterplotDrawingPanel area, HistogramDrawingPanelForXaxis area2, HistogramDrawingPanelForYaxis area3, ArrayList<String> label, Format format)
    {
        JMenuBar optionsBar;
        JMenu pointSize;
        JMenu optionsOne;
        JMenu optionsTwo;
        JMenu optionsThree;
        JMenuItem connectPoints;
        JMenuItem removePoints;
        JMenuItem one, two, three, four, five;
        JMenuItem changeColor;
        JComboBox xAxis;
        JComboBox yAxis;

        //Initialisierung
        optionsBar = new JMenuBar();
        optionsOne = new JMenu("Dots");
        optionsTwo = new JMenu("Size");
        optionsThree = new JMenu("Color");

        //Combobox
        String[] box = label.toArray(new String[label.size()]);

        xAxis = new JComboBox(box);
        xAxis.setSelectedIndex(0);
        yAxis = new JComboBox(box);
        yAxis.setSelectedIndex(1);

        //Combobox Actionlistener
        xAxis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JComboBox xAxis = (JComboBox) event.getSource();
                int indexComboBox = xAxis.getSelectedIndex();
                area.setIndexVariableX(indexComboBox);
                area2.setIndexVariableX(indexComboBox);
                area3.setIndexVariableX(indexComboBox);
                histogramFrameXaxis.setTitle(area2.getXlabel(indexComboBox));
            }
        });

        yAxis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JComboBox yAxis = (JComboBox) event.getSource();
                int indexComboBox = yAxis.getSelectedIndex();
                area.setIndexVariableY(indexComboBox);
                area2.setIndexVariableY(indexComboBox);
                area3.setIndexVariableY(indexComboBox);
                histogramFrameYaxis.setTitle(area3.getYlabel(indexComboBox));
            }
        });

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
        optionsBar.add(xAxis);
        optionsBar.add(yAxis);  //Combobox nicht als Menüitem behandeln!! Actionlistener funktioniert nicht...

        //Menüleiste JFrame hinzufüg
        frame.add(optionsBar, BorderLayout.NORTH);

        //Listener
        connectPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.addLines();
                format.getListe().get(0);
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

