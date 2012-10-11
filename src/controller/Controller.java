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
    public final boolean loadFile () {
        return myDataManager.loadFile();
    }

    public void setVisualization (final String visType, final String selectedRowOrCol) {
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
