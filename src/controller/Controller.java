package controller;

import model.DataManager;
import visualizations.Visualization;


/**
 * 
 * @author Howard Chung, Volodymyr Zavidovych, Xi Du, Sam Rang
 * 
 */
public class Controller {
    private DataManager myDataManager;

    /**
     * constructor
     */
    public Controller () {
        myDataManager = new DataManager();
    }

    /**
     * load file
     */
    public final boolean loadFile () {
        return myDataManager.loadFile();
    }


    public void setVisualization (String visType, String selectedRowOrCol) {
        myDataManager.setVisualization(visType, selectedRowOrCol, this);
    }

    /**
     * 
     * @return get years
     */
    public final String[] getAllColTitles () {
        return myDataManager.getAllColTitles();
    }

    /**
     * 
     * @return get countries
     */
    public final String[] getAllRowTitles () {
        return myDataManager.getAllRowTitles();
    }
}
