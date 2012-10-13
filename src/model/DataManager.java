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
    private InputReader myInputReader;
    private InputParser myInputParser;

    /**
     * Creates a new model.
     */
    public DataManager () {
        myInputReader = new InputReader();
        myInputParser = myInputReader.getInputParser();
    }

    /**
     * loads and parses file; in case of failure 
     * restores previous data and reports to view
     */
    public boolean loadFile () {
        InputParser backupParser = new InputParser(myInputParser);
        boolean readValid = myInputReader.readFile();
        boolean parseValid = myInputParser.fillData();
        boolean inputIsValid = readValid && parseValid;
        if (!inputIsValid) {
            myInputParser = backupParser;
            myInputReader.setParser(backupParser);
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
            return myInputParser.getValuesByRow();
        }
        else {
            return myInputParser.getValuesByCol();
        }
    }

    /**
     * Get list of years.
     * 
     * @return
     */

    public String[] getAllColTitles () {
        return myInputParser.getAllColTitles();
    }

    /**
     * Get list of countries.
     * 
     * @return
     */
    public String[] getAllRowTitles () {
        return myInputParser.getAllRowTitles();
    }
}
