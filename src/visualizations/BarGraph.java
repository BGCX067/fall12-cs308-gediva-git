package visualizations;

import java.util.HashMap;


public class BarGraph extends Visualization {
    private HashMap<String, Double> myValues;

    public void addData (String country, double value) {
        myValues.put(country, value);
    }

    public void clearValues () {
        myValues.clear();
    }

}
