package visualizations;

import view.View;

public class BarGraph extends Visualization {
    public BarGraph(String year) {
        
    }

    public void addData (String country, double year, double value) {
        getValues().put(country, value);
    }

    @Override
    public void paint (View v) {
        // TODO Auto-generated method stub
        
    }
}
