package visualizations;

import java.util.HashMap;

public class LineGraph extends Visualization {
    private HashMap<Double, Double> myValues;
    
    public void addData (String country, double year, double value) {
        myValues.put(year, value);
    }

}
