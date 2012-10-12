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
        boolean inputIsValid = myFileOpener.readFile() && myFileOpener.getFileParser().fillData();
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
     * @param visType string
     * @param selectedRowOrCol select row or column
     * @param contr controller
     */
    public void setVisualization (String visType, String selectedRowOrCol,
            Controller contr) {

        Visualization myVis = Factory.MYVISUALIZATIONS.get(visType);

        for (String name : Factory.MYVISUALIZATIONS.keySet()) {
            if (name.equals(visType)) {
                boolean isRowInput = Factory.MYVISUALIZATIONS.get(name).isRowInput();
                System.out.println(isRowInput);
                System.out.println(getSelectedInputData(isRowInput).get(
                        selectedRowOrCol));
                Factory.MYVISUALIZATIONS.get(name).setValues(
                        getSelectedInputData(isRowInput).get(selectedRowOrCol),
                        selectedRowOrCol, contr);
            }
        }

        myVis.visualize();

    }

    /**
     * 
     * @param isRowInput boolean
     * @return
     */
    public HashMap<String, List<Double>> getSelectedInputData (
            boolean isRowInput) {
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
