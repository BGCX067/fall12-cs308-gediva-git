package model;

import java.util.HashMap;
import visualizations.BarChart;
import visualizations.LineChart;
import visualizations.Visualization;

/**
 * 
 * @author Howard
 *
 */
public class Factory {
    /**
     * Name of Visualization the type Bar Graph
     */
    public static final String BAR_GRAPH = "Bar Chart";
    /**
     * Name of Visualization the type Line Graph
     */
    public static final String LINE_GRAPH = "Line Chart";
    /**
     * Maps the name to the type of object
     */
    public static HashMap<String, Visualization> myVisualizations = new HashMap<String, Visualization>() {
        {
            put(Factory.BAR_GRAPH, new BarChart());
            put(Factory.LINE_GRAPH, new LineChart());
        };
    };

}
