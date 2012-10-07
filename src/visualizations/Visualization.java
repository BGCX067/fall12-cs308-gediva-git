package visualizations;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JPanel;
import controller.Controller;



@SuppressWarnings("serial")
public abstract class Visualization extends JPanel {
    private double[] myYears;
    private String[] myCountries;
    private Controller myController;
    private HashMap<String, Double> myValues = new HashMap<String, Double>();

    public Visualization (Controller c) {
        myValues = new HashMap<String, Double>();
        myController = c;
        myYears = Arrays.copyOf(c.getAllYears(), c.getAllYears().length);
        myCountries = Arrays.copyOf(c.getAllCountries(), c.getAllCountries().length);
    }

    public double[] getMyYears () {
        return myYears;
    }

    public void setMyYears (double[] myYear) {
        this.myYears = myYear;
    }

    public String[] getMyCountries () {
        return myCountries;
    }

    public void setMyCountries (String[] myCountries) {
        this.myCountries = myCountries;
    }

    public HashMap<String, Double> getValues () {
        return myValues;
    }
    public void clearValues () {
        myValues.clear();
        
    }

    public abstract void addData (String country, double year, double value);

    public abstract void paint (Graphics g);
}
