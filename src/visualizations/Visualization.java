package visualizations;

import java.util.HashMap;

public abstract class Visualization {
    private String myCategory;
    private double[] myYear;
    private String[] myCountries;
    private HashMap<String, Double> myValues;
    
    public String getMyCategory () {
        return myCategory;
    }

    public void setMyCategory (String myCategory) {
        this.myCategory = myCategory;
    }

    public double[] getMyYear () {
        return myYear;
    }

    public void setMyYear (double[] myYear) {
        this.myYear = myYear;
    }

    public String[] getMyCountries () {
        return myCountries;
    }

    public void setMyCountries (String[] myCountries) {
        this.myCountries = myCountries;
    }
    
    public void clearValues () {
        myValues.clear();
    }
    
    public abstract void addData (String country, double year, double value);
}
