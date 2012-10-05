package visualizations;

import java.util.HashMap;
import view.View;


public abstract class Visualization {
    private double[] myYears;
    private String[] myCountries;
    private HashMap<String, Double> myValues;

    public Visualization () {
        myValues = new HashMap<String, Double>();
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

    public abstract void paint (View v);
}
