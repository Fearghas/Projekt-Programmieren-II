package loadformat;

import java.util.ArrayList;

/**
 * Created by Briareus on 25.05.2015.
 */
public class Variable
{
    private String name;
    private ArrayList<Double> values;

    public Variable(String name, ArrayList<Double> values)
    {
        this.name = name;
        this.values = values;
    }

    public ArrayList<Double> getValues()
    {
        return values;
    }

    public String getName()
    {

        return name;
    }
}

