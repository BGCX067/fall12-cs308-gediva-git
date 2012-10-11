package model;

import controller.Controller;
import java.util.HashMap;
import java.util.List;
import resources.Constants;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;


/**
 * Model for data processing.
 * 
 * @author Howard
 * 
 */
public class DataProcessModel {
    private FileOpener myFileOpener;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;
    private String[] myAllRowTitles;
    private String[] myAllColTitles;

    /**
     * Creates a new model.
     */
    public DataProcessModel () {
        myFileOpener = new FileOpener();
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
     * @param visType
     * @param selectedRowOrCol
     * @param contr
     */
    public void setVisualization (String visType, String selectedRowOrCol, Controller contr) {
        Visualization myVis = Constants.myNameMap.get(visType);
        if (Constants.BAR_GRAPH.equals(visType)) {
            myVis.setValues(myAllValuesByCol.get(selectedRowOrCol), selectedRowOrCol, contr);
        }
        else if (Constants.LINE_GRAPH.equals(visType)) {
            myVis.setValues(myAllValuesByRow.get(selectedRowOrCol), selectedRowOrCol, contr);
        }
        myVis.visualize();
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
