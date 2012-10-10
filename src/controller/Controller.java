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

//    /**
//     * Tells the model to create a new Visualization
//     * @param visType the type of visualization
//     * @param selectedRowOrCol one row or column to visualize
//     */
//    public void createVisualization (String visType, String selectedRowOrCol) {
//        myModel.createVisualization(visType, selectedRowOrCol, this).visualize();
//    }

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
