package visualizations;

import java.util.HashMap;

public class LineGraph extends Visualization {
    private HashMap<String, Double> myValues;
    
    public void addData (String country, double year, double value) {
        myValues.put(Double.toString(year), value);
    }
}
