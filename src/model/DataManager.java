package model;

import static resources.Constants.BAR_VIS_TITLE;
import static resources.Constants.LINE_VIS_TITLE;
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

        HashMap<String, List<Double>> inputMap = myAllValuesByCol;
        if (LINE_VIS_TITLE.equals(visType)) {
            inputMap = myAllValuesByRow;
        }
        for (String name : Factory.myVisualizations.keySet()) {
            if (name.equals(visType)) {
                Factory.myVisualizations.get(name).setValues(inputMap.get(selectedRowOrCol),
                                                             selectedRowOrCol, contr);
            }
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
