package visualizations;



public class BarGraph extends Visualization {
    
    public void addData (String country, double year, double value) {
        getValues().put(country, value);
    }
}
