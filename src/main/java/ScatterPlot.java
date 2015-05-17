import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterPlot extends Application implements Formatloader//Scatterplot bekommt Application vererbt
{
    @Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis x_Achse = new NumberAxis(0, 10, 1); //flexibel anpassen aus Originaldaten
        final NumberAxis y_Achse = new NumberAxis(-100, 500, 100);
        final ScatterChart<Number,Number> ScatterChart = new ScatterChart<>(x_Achse, y_Achse);
        x_Achse.setLabel("X");
        y_Achse.setLabel("Y");
        ScatterChart.setTitle("BlaBla");

        loadformat("spalten.txt"); //testen


        XYChart.Series Serie1 = new XYChart.Series(); //XY Chart aus Scene anpassen
        Serie1.setName("Legende1");
        Serie1.getData().add(new XYChart.Data(4.2, 193.2)); //Originaldaten hier
        Serie1.getData().add(new XYChart.Data(2.8, 33.6));
        Serie1.getData().add(new XYChart.Data(6.2, 24.8));


        XYChart.Series Serie2 = new XYChart.Series();
        Serie2.setName("Legende2");
        Serie2.getData().add(new XYChart.Data(2, 229.2)); //Originaldaten hier
        Serie2.getData().add(new XYChart.Data(2.4, 37.6));
        Serie2.getData().add(new XYChart.Data(3.2, 49.8));
        Serie2.getData().add(new XYChart.Data(1.8, 134));


        ScatterChart.getData().addAll(Serie1, Serie2);
        Scene scene  = new Scene(ScatterChart, 600, 400); //grösse des Fensters
        stage.setScene(scene);
        stage.show();
    }


 
    @Override
    public Format loadformat(String fileName) {
        return null;
    }
}
