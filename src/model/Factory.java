package model;

import java.util.HashMap;
import resources.Constants;
import visualizations.BarChart;
import visualizations.LineChart;
import visualizations.Visualization;


/**
 * 
 * @author Howard, Volodymyr
 * 
 */
public final class Factory {
    /**
     * Maps the name to the type of object
     */
    @SuppressWarnings("serial")
    public static final HashMap<String, Visualization> MYVISUALIZATIONS = new 
    HashMap<String, Visualization>() {
        {
            put(Constants.BAR_VIS_TITLE, new BarChart());
            put(Constants.LINE_VIS_TITLE, new LineChart());
        };
    };

    private Factory () {
        // doesn't do anything
    }
}
