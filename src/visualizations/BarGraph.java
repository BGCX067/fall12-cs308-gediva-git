package visualizations;

import java.util.HashMap;


public class BarGraph extends Visualization {
    private HashMap<String, Double> myValues;
    
    public void addData (String country, double year, double value) {
        myValues.put(country, value);
    }


}
