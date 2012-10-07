package controller;

import model.Model;
import visualizations.Visualization;

/**
 * 
 * @author  Howard Chung, Volodymyr Zavidovych,Xi Du,Sam Rang
 *
 */
public class Controller {
    private Model myModel;
    /**
     * constructor
     */
    public Controller() {
        myModel = new Model();
    }

    /**
     * load file
     */
    public final void loadFile() {
        myModel.loadFile();
    }

    /**
     * @param requestedVis Visualization being updated
     */
    public Visualization createVisualization (String visType, String selectedRowOrCol) {
        return myModel.createVisualization(visType, selectedRowOrCol, this);
    }

    /**
     * 
     * @return get years
     */
    public final String[] getAllYears() {
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
