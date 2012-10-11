package model;

import controller.Controller;
import java.util.HashMap;
import java.util.List;
import visualizations.BarChart;
import visualizations.LineChart;
import visualizations.Visualization;


/**
 * Manages data.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class DataManager {
    private InputReader myFileOpener;
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
     * @param visType
     * @param selectedRowOrCol
     * @param contr
     */
    public void setVisualization (String visType, String selectedRowOrCol, Controller contr) {
        Visualization myVis = Factory.myVisualizations.get(visType);
        if (Factory.BAR_GRAPH.equals(visType)) {
            myVis.setValues(myAllValuesByCol.get(selectedRowOrCol), selectedRowOrCol, contr);
        }
        else if (Factory.LINE_GRAPH.equals(visType)) {
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
