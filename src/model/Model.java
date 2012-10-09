package model;

import controller.Controller;
import java.util.HashMap;
import java.util.List;
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
    private static final String LINE = "Line Graph";
    private static final String BAR = "Bar Graph";
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
     * Creates a new visualization.
     * 
     * @param visType type of visualization to create
     * @param selectedRowOrCol column or row of data
     * @param contr controller to act on
     * @return
     */
    public Visualization createVisualization (String visType, String selectedRowOrCol,
                                              Controller contr) {

        if (BAR.equals(visType)) {
            return new BarGraph(myAllValuesByCol.get(selectedRowOrCol), selectedRowOrCol, contr);
        }
        else if (LINE.equals(visType)) {
            return new LineGraph(myAllValuesByRow.get(selectedRowOrCol), selectedRowOrCol, contr);
        }
        else {
            return null;
        }
    }

    public String[] getAllYears () {
        return myFileOpener.getFileParser().getAllYears();
    }

    public String[] getAllCountries () {
        return myFileOpener.getFileParser().getAllCountries();
    }
}
