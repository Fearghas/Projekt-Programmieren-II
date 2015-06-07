package project.drawing;
import project.processing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class MainPanel extends JFrame
{
    JFrame histogramFrameAxisX;
    JFrame histogramFrameAxisY;

    public void createMainPanel() {
        this.setTitle("Plot and Histogram Viewer");
        this.setSize(300, 100);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenuOne = new JMenu("File");
        JMenu fileMenuTwo = new JMenu("Application");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem closeItem = new JMenuItem("Close");
        addMainPanelOptions(menuBar, fileMenuOne, fileMenuTwo, openItem, closeItem);
        this.add(menuBar, BorderLayout.NORTH);

        openItem.addActionListener(e ->
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnValue = fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();

            try {
                String pathname = file.getCanonicalPath();
                int defaultPointSize = 3;
                int defaultIndexAxisX = 0;
                int defaultIndexAxisY = 1;
                if (pathname.contains("txt")) {
                    FormatReader loader = new TabFormatScanner();
                    Storage tab = loader.readFormat(pathname);
                    JFrame scatterPlotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(tab);
                    createScatterPlot(defaultPointSize, scatterPlotFrame, plot);
                    histogramFrameAxisX = new JFrame();
                    HistogramDrawingPanelAxisX xPlot = new HistogramDrawingPanelAxisX(tab);
                    createHistogramAxisX(defaultIndexAxisX, xPlot);
                    histogramFrameAxisY = new JFrame();
                    HistogramDrawingPanelAxisY yPlot = new HistogramDrawingPanelAxisY(tab);
                    createHistogramAxisY(defaultIndexAxisY, yPlot);
                    addOptionsBar(scatterPlotFrame, plot, xPlot, yPlot, tab.getLabel(), tab);
                } else if (pathname.contains("lin")) {
                    FormatReader loader = new RowFormatScanner();
                    Storage lin = loader.readFormat(pathname);
                    JFrame scatterPlotFrame = new JFrame(pathname);
                    ScatterplotDrawingPanel plot = new ScatterplotDrawingPanel(lin);
                    createScatterPlot(defaultPointSize, scatterPlotFrame, plot);
                    histogramFrameAxisX = new JFrame();
                    HistogramDrawingPanelAxisX xPlot = new HistogramDrawingPanelAxisX(lin);
                    createHistogramAxisX(defaultIndexAxisX, xPlot);
                    histogramFrameAxisY = new JFrame();
                    HistogramDrawingPanelAxisY yPlot = new HistogramDrawingPanelAxisY(lin);
                    createHistogramAxisY(defaultIndexAxisY, yPlot);
                    addOptionsBar(scatterPlotFrame, plot, xPlot, yPlot, lin.getLabel(), lin);
                } else {
                    System.out.println("File is not supported!");
                }
            } catch (IOException e1) {

                e1.printStackTrace();

            }
        });

        closeItem.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void createHistogramAxisY(int defaultIndexAxisY, HistogramDrawingPanelAxisY yPlot) {
        histogramFrameAxisY.setTitle(yPlot.getYlabel(defaultIndexAxisY));
        histogramFrameAxisY.add(yPlot);
        histogramFrameAxisY.setSize(250, 250);
        histogramFrameAxisY.setLocation(100, 420);
        histogramFrameAxisY.setVisible(true);
        histogramFrameAxisY.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createHistogramAxisX(int defaultIndexAxisX, HistogramDrawingPanelAxisX xPlot) {
        histogramFrameAxisX.setTitle(xPlot.getXlabel(defaultIndexAxisX));
        histogramFrameAxisX.add(xPlot);
        histogramFrameAxisX.setSize(250, 250);
        histogramFrameAxisX.setLocation(100, 160);
        histogramFrameAxisX.setVisible(true);
        histogramFrameAxisX.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createScatterPlot(int defaultPointSize, JFrame scatterplotFrame, ScatterplotDrawingPanel plot) {
        plot.setPointSize(defaultPointSize);
        scatterplotFrame.add(plot);
        scatterplotFrame.setSize(500, 500);
        scatterplotFrame.setLocationRelativeTo(null);
        scatterplotFrame.setVisible(true);
        scatterplotFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void addMainPanelOptions(JMenuBar menuBar, JMenu fileMenuOne, JMenu fileMenuTwo,
                                     JMenuItem openItem, JMenuItem closeItem) {
        fileMenuOne.add(openItem);
        fileMenuTwo.add(closeItem);
        menuBar.add(fileMenuOne);
        menuBar.add(fileMenuTwo);
    }

    private void addOptionsBar(JFrame frame, ScatterplotDrawingPanel plot,
                               HistogramDrawingPanelAxisX histogramX, HistogramDrawingPanelAxisY histogramY,
                               ArrayList<String> label, Storage data) {
        JMenuBar optionsBar;
        JMenu pointSize, optionsOne, optionsTwo, optionsThree;
        JMenuItem connectPoints, removePoints, PointSizeTwo, PointSizeThree, PointSizeFive,
                PointSizeSeven, PointSizeTen, changeColor;
        JComboBox axisX, axisY;

        optionsBar = new JMenuBar();
        optionsOne = new JMenu("Dots");
        optionsTwo = new JMenu("Size");
        optionsThree = new JMenu("Color");

        connectPoints = new JMenuItem("Connect");
        removePoints = new JMenuItem("Unconnect");
        pointSize = new JMenu("Pixel");
        changeColor = new JMenuItem("Choose");
        PointSizeTwo = new JMenuItem("2");
        PointSizeThree = new JMenuItem("3");
        PointSizeFive = new JMenuItem("5");
        PointSizeSeven = new JMenuItem("7");
        PointSizeTen = new JMenuItem("10");

        String[] box = label.toArray(new String[label.size()]);
        axisX = new JComboBox(box);
        axisX.setSelectedIndex(0);
        axisY = new JComboBox(box);
        axisY.setSelectedIndex(1);

        optionsBar.add(optionsOne);
        optionsOne.add(connectPoints);
        connectPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.addLines();
                data.getValuesOfVariable().get(0);
            }
        });

        optionsOne.add(removePoints);
        removePoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.removeLines();
            }
        });

        optionsBar.add(optionsTwo);
        optionsTwo.add(pointSize);
        pointSize.add(PointSizeTwo);
        PointSizeTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPointSize(2);
            }
        });
        pointSize.add(PointSizeThree);
        PointSizeThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPointSize(3);
            }
        });
        pointSize.add(PointSizeFive);
        PointSizeFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPointSize(5);
            }
        });
        pointSize.add(PointSizeSeven);
        PointSizeSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPointSize(7);
            }
        });
        pointSize.add(PointSizeTen);
        PointSizeTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPointSize(10);
            }
        });

        optionsBar.add(optionsThree);
        optionsThree.add(changeColor);
        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.setPlotColor();

            }
        });

        optionsBar.add(axisX);
        axisX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox xAxis = (JComboBox) event.getSource();
                int indexComboBox = xAxis.getSelectedIndex();
                plot.setIndexVariableX(indexComboBox);
                histogramX.setIndexVariableX(indexComboBox);
                histogramY.setIndexVariableX(indexComboBox);
                histogramFrameAxisX.setTitle(histogramX.getXlabel(indexComboBox));
            }
        });

        optionsBar.add(axisY);
        axisY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox yAxis = (JComboBox) event.getSource();
                int indexComboBox = yAxis.getSelectedIndex();
                plot.setIndexVariableY(indexComboBox);
                histogramX.setIndexVariableY(indexComboBox);
                histogramY.setIndexVariableY(indexComboBox);
                histogramFrameAxisY.setTitle(histogramY.getYlabel(indexComboBox));
            }
        });

        frame.add(optionsBar, BorderLayout.NORTH);
    }
}

