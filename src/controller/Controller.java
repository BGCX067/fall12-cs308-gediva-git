package controller;

import java.util.HashMap;
import model.Model;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;

/**
 * 
 * @author  Howard Chung, Volodymyr Zavidovych,Xi Du,Sam Rang
 *
 */
public class Controller {
    /**
     * @param myModel model object
     */
    private Model myModel;
    /**
     * @param myNameMap
     */
    private HashMap<String, Visualization> myNameMap;

    /**
     * constructor
     */
    public Controller() {
        myModel = new Model();
        myNameMap = new HashMap<String, Visualization>();
        generateMap();
    }

    /**
     * load file
     */
    public final void loadFile() {
        myModel.loadFile();
    }
    
    /**
     * generate name map
     */
    public final void generateMap() {
        myNameMap.put("Bar Graph", new BarGraph());
        myNameMap.put("Line Graph", new LineGraph());
    }

    /**
     * 
     * @param visType graph type
     * @param countries countries
     * @param years years
     * @return return Visualization class object
     */
    public final Visualization getData(final String visType,
            final String[] countries, final double[] years) {
        Visualization requestedVis =  myNameMap.get(visType);
        myModel.updateVisualization(requestedVis, countries, years);
        return requestedVis;
    }

    /**
     * 
     * @return get years
     */
    public final double[] getYears() {
        return myModel.getYears();
    }

    /**
     * 
     * @return get countries
     */
    public final String[] getCountries() {
        return myModel.getCountries();
    }
}
