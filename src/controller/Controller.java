package controller;

import model.Model;
import visualizations.Visualization;


/**
 * 
 * @author Howard Chung, Volodymyr Zavidovych,Xi Du,Sam Rang
 * 
 */
public class Controller {
    private Model myModel;

    /**
     * constructor
     */
    public Controller () {
        myModel = new Model();
    }

    /**
     * load file
     */
    public final void loadFile () {
        // pass in a delimiter character here
        myModel.loadFile();
    }

    public Visualization createVisualization (String visType, String selectedRowOrCol) {
        // THIS FUNCTION SHOULD NOT EXIST
        // use setVisualization to modify the contents of a visualization
        // Visualization now has a setValues method to allow setting values
        // bar and line graph can set their titles in setTitle method
        return myModel.createVisualization(visType, selectedRowOrCol, this);
    }

    public void setVisualization (String visType, String selectedRowOrCol) {
        myModel.setVisualization(visType, selectedRowOrCol, this);
    }

    /**
     * 
     * @return get years
     */
    public final String[] getAllYears () {
        return myModel.getAllYears();
    }

    /**
     * 
     * @return get countries
     */
    public final String[] getAllCountries () {
        return myModel.getAllCountries();
    }
}
