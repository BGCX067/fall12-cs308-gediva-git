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
    private BarGraph myBar;
    private LineGraph myLine;

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
    public void generateMap () {
        myNameMap.put("Bar Graph", myBar);
        myNameMap.put("Line Graph", myLine);
    }

    /**
     * @param requestedVis Visualization being updated
     */
    public void getData (Visualization requestedVis) {
        myModel.updateVisualization(requestedVis);
    }

    /**
     * 
     * @return get years
     */
    public final double[] getAllYears() {
        return myModel.getAllYears();
    }

    /**
     * 
     * @return get countries
     */
    public final String[] getAllCountries() {
        return myModel.getAllCountries();
    }
}
