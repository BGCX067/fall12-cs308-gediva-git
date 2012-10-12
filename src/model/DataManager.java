package model;

import controller.Controller;
import java.util.HashMap;
import java.util.List;
import visualizations.Visualization;


/**
 * Manages data.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class DataManager {
    private final InputReader myFileOpener;
    private InputParser myFileParser;

    /**
     * Creates a new model.
     */
    public DataManager () {
        myFileOpener = new InputReader();
    }

    /**
     * loads a file and fills HashMaps, checks for success
     */
    public boolean loadFile () {

        boolean readValid = myFileOpener.readFile();
        myFileParser = myFileOpener.getFileParser();
        boolean fillValid = myFileParser.fillData();

        boolean inputIsValid = readValid && fillValid;
        return inputIsValid;
    }

    /**
     * 
     * @param visType string
     * @param selectedRowOrCol select row or column
     * @param contr controller
     */
    public void setVisualization (String visType, String selectedRowOrCol,
            Controller contr) {

        Visualization myVis = Factory.VISUALIZATIONS.get(visType);

        for (String name : Factory.VISUALIZATIONS.keySet()) {
            if (name.equals(visType)) {
                boolean isRowInput = myVis.isRowInput();
                myVis.setValues(
                        getSelectedInputData(isRowInput).get(selectedRowOrCol),
                        selectedRowOrCol, contr);
            }
        }

        myVis.visualize();

    }

    /**
     * Pick hashmap of rows or columns
     * 
     * @param isRowInput boolean
     * @return
     */
    public HashMap<String, List<Double>> getSelectedInputData (
            boolean isRowInput) {
        if (isRowInput) {
            return myFileParser.getValuesByRow();
        }
        else {
            return myFileParser.getValuesByCol();
        }
    }

    /**
     * Get list of years.
     * 
     * @return
     */

    public String[] getAllColTitles () {
        return myFileParser.getAllColTitles();
    }

    /**
     * Get list of countries.
     * 
     * @return
     */
    public String[] getAllRowTitles () {
        return myFileParser.getAllRowTitles();
    }
}
