package model;

import static resources.Constants.BAR_VIS_TITLE;
import static resources.Constants.LINE_VIS_TITLE;
import java.util.HashMap;
import visualizations.BarChart;
import visualizations.LineChart;
import visualizations.Visualization;


/**
 * 
 * @author Howard, Volodymyr
 * 
 */
public class Factory {
    /**
     * Maps the name to the type of object
     */
    public static HashMap<String, Visualization> myVisualizations =
            new HashMap<String, Visualization>() {
                {
                    put(BAR_VIS_TITLE, new BarChart());
                    put(LINE_VIS_TITLE, new LineChart());
                };
            };

}
