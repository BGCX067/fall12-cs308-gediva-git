package resources;

import java.util.HashMap;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;


public class Constants {
    public static final String BAR_GRAPH = "Bar Graph";
    public static final String LINE_GRAPH = "Line Graph";
    public static HashMap<String, Visualization> myNameMap = new HashMap<String, Visualization>() {
        {
            put(Constants.BAR_GRAPH, new BarGraph());
            put(Constants.LINE_GRAPH, new LineGraph());
        };
    };

}
