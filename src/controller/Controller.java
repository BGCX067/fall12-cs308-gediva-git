package controller;

import model.DataManager;


/**
 * 
 * @author Howard, Volodymyr, Xi Du, Sam Rang
 * 
 */
public class Controller {
    private final DataManager myDataManager;

    /**
     * constructor
     */
    public Controller () {
        myDataManager = new DataManager();
    }

    /**
     * load file
     */
    public boolean loadFile () {
        return myDataManager.loadFile();
    }
/**
 * Sets state of a visualization.
 * @param visType the type of the visualization
 * @param selectedRowOrCol a selected row or column
 */
    public void setVisualization (String visType, String selectedRowOrCol) {
        myDataManager.setVisualization(visType, selectedRowOrCol, this);
    }

    /**
     * 
     * @return get years
     */
    public String[] getAllColTitles () {
        return myDataManager.getAllColTitles();
    }

    /**
     * 
     * @return get countries
     */
    public String[] getAllRowTitles () {
        return myDataManager.getAllRowTitles();
    }
}
