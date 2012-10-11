package model;

import java.util.HashMap;
import java.util.List;
import visualizations.Visualization;
import controller.Controller;


/**
 * Manages data.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class DataManager {
    private final InputReader myFileOpener;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;
    private String[] myAllRowTitles;
    private String[] myAllColTitles;

    /**
     * Creates a new model.
     */
    public DataManager () {
        myFileOpener = new InputReader();
    }

    /**
     * loads a file and fills HashMaps.
     */
    public boolean loadFile () {
        final boolean inputIsValid =
                myFileOpener.readFile() && myFileOpener.getFileParser().fillData();
        if (inputIsValid) {
            myAllValuesByRow = myFileOpener.getFileParser().getValuesByRow();
            myAllValuesByCol = myFileOpener.getFileParser().getValuesByCol();
            myAllRowTitles = myFileOpener.getFileParser().getAllRowTitles();
            myAllColTitles = myFileOpener.getFileParser().getAllColTitles();
        }
        return inputIsValid;
    }

    /**
     * 
     * @param visType
     * @param selectedRowOrCol
     * @param contr
     */
    public void setVisualization (final String visType, final String selectedRowOrCol,
                                  final Controller contr) {

        final Visualization myVis = Factory.myVisualizations.get(visType);

        for (String name : Factory.myVisualizations.keySet()) {
            if (name.equals(visType)) {
                boolean isRowInput = Factory.myVisualizations.get(name).isRowInput();
                Factory.myVisualizations.get(name).setValues(getSelectedInputData(isRowInput)
                                                                     .get(selectedRowOrCol),
                                                             selectedRowOrCol, contr);
            }
        }
        myVis.visualize();
    }

    public HashMap<String, List<Double>> getSelectedInputData (boolean isRowInput) {
        if (isRowInput) {
            return myAllValuesByRow;
        }
        else {
            return myAllValuesByCol;
        }
    }

    /**
     * Get list of years.
     * 
     * @return
     */

    public String[] getAllColTitles () {
        return myAllColTitles;
    }

    /**
     * Get list of countries.
     * 
     * @return
     */
    public String[] getAllRowTitles () {
        return myAllRowTitles;
    }
}
