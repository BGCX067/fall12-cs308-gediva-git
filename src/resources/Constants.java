package resources;

import java.util.HashMap;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;

/**
 * 
 * @author Howard
 *
 */
public class Constants {
    /**
     * Name of Visualization the type Bar Graph
     */
    public static final String BAR_GRAPH = "Bar Graph";
    /**
     * Name of Visualization the type Line Graph
     */
    public static final String LINE_GRAPH = "Line Graph";
    /**
     * Maps the name to the type of object
     */
    public static HashMap<String, Visualization> myNameMap = new HashMap<String, Visualization>() {
        {
            put(Constants.BAR_GRAPH, new BarGraph());
            put(Constants.LINE_GRAPH, new LineGraph());
        };
    };

}
