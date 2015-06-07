package project;

import project.drawing.MainPanel;
import javax.swing.*;

public class PlotAndHistogramViewer extends JFrame {
    public static void main(String[] args) {
        MainPanel project = new MainPanel();
        project.createMainPanel();
        project.setLocation(50, 50);
        project.setVisible(true);
        project.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
