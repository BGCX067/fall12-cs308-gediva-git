package visualizations;


public class LineGraph extends Visualization {
    
    public void addData (String country, double year, double value) {
        getValues().put(Double.toString(year), value);
    }
}
