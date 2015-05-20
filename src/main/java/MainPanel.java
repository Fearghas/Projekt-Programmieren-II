import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Andreas on 19.05.2015.
 */
public class MainPanel extends JFrame{
    private static final int WIDTH = 800;
    private static  final int HEIGHT =600;
    private static Format format;

    private MainPanel(){

        //Configure frame appearance
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("ScatterPlot");
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        //Set UI parts
        JPanel InputPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JButton X_Achse = new JButton("X Achse");
        ButtonPanel.add(X_Achse);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        mainPanel.add(InputPanel);
        mainPanel.add(ButtonPanel);
        mainPanel.add(mainPanel);
        setContentPane(mainPanel);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           @Override public void run() {
                MainPanel Scatter = new MainPanel();
                Scatter.setVisible(true);
            }
            ArrayList<Double> min = format.getMinimum(); //testen ob es geht

        });
    }
}
