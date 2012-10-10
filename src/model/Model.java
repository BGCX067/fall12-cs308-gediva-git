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
public class Model {
    private FileOpener myFileOpener;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;

    /**
     * Creates a new model.
     */
    public Model () {
        myFileOpener = new FileOpener();
    }

    /**
     * loads a file and fills HashMaps.
     */
    public void loadFile () {
        myFileOpener.readFile();
        myFileOpener.getFileParser().fillData();
        myAllValuesByRow = myFileOpener.getFileParser().getValuesByRow();
        myAllValuesByCol = myFileOpener.getFileParser().getValuesByCol();
    }

    /**
     * 
     * @param visType
     * @param selectedRowOrCol
     * @param contr
     */
    public void setVisualization (String visType, String selectedRowOrCol, Controller contr) {
        /*
         * for (String name : Constants.myNameMap.keySet()) {
         * if (name.equals(visType)) {
         * Constants.myNameMap.get(name).setValues(myAllValuesByCol.get(
         * selectedRowOrCol),
         * selectedRowOrCol, contr);
         * }
         * }
         */

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

    public String[] getAllYears () {
        return myFileOpener.getFileParser().getAllYears();
    }

    /**
     * Get list of countries.
     * 
     * @return
     */
    public String[] getAllCountries () {
        return myFileOpener.getFileParser().getAllCountries();
    }
}
