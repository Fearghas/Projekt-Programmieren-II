import java.util.ArrayList;

/**
 * Created by Briareus on 13.05.2015.
 */
public class Backbone
{
    private String x_Achse;
    private String y_Achse;
    private double x_Wert;
    private double y_Wert;
//test
    public Backbone(/*String x_Achse, String y_Achse,*/ double x_Wert, double y_Wert)
    {
        //this.x_Achse = x_Achse;
        //this.y_Achse = y_Achse;
        this.x_Wert = x_Wert;
        this.y_Wert = y_Wert;
    }

    /*public String getX_Achse()
    {
        return x_Achse;
    }

    public String getY_Achse()
    {
        return y_Achse;
    }*/

    public double getX_Wert()
    {
        return x_Wert;
    }

    public double getY_Wert()
    {
        return y_Wert;
    }
}
