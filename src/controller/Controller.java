package controller;

import model.DataProcessModel;
import visualizations.Visualization;


/**
 * 
 * @author Howard Chung, Volodymyr Zavidovych,Xi Du,Sam Rang
 * 
 */
public class Controller {
    private DataProcessModel myModel;

    /**
     * constructor
     */
    public Controller () {
        myModel = new DataProcessModel();
    }

    /**
     * load file
     */
    public final boolean loadFile () {
        return myModel.loadFile();
    }


    public void setVisualization (String visType, String selectedRowOrCol) {
        myModel.setVisualization(visType, selectedRowOrCol, this);
    }

    /**
     * 
     * @return get years
     */
    public final String[] getAllYears () {
        return myModel.getAllColTitles();
    }

    /**
     * 
     * @return get countries
     */
    public final String[] getAllCountries () {
        return myModel.getAllRowTitles();
    }
}
