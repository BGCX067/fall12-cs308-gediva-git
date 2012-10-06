package visualizations;

import view.View;

public class LineGraph extends Visualization {
    public LineGraph (String s) {
        
    }
    public void addData (String country, double year, double value) {
        getValues().put(Double.toString(year), value);
    }

    @Override
    public void paint (View v) {
        // TODO Auto-generated method stub
        
    }
}
