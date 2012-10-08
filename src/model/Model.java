package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;
/**
 * Model for data processing.
 * @author Howard
 *
 */
public class Model {
    private static final String LINE = "Line Graph";
    private static final String BAR = "Bar Graph";
    private FileOpener myFileOpener;
    private String[] myAllRowTitles;
    private String[] myAllColTitles;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;
/**
 * Creates a new model.
 */
    public Model () {
        myFileOpener = new FileOpener();
        myAllValuesByRow = new HashMap<String, List<Double>>();
        myAllValuesByCol = new HashMap<String, List<Double>>();
    }
/**
 * loads a file and fills HashMaps.
 */
    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        myAllRowTitles = new String[lines.size() - 1];
        myAllColTitles = new String[lines.get(0).length - 1];
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                processColTitles(lines.get(0));
            }
            else {
                myAllRowTitles[i - 1] = lines.get(i)[0];
                processValues(lines.get(i));
            }
        }
    }
/**
 * Creates a new visualization.
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
/**
 * Fills column of data.
 * @param line an array of data
 */
    public void processColTitles (String[] line) {
        for (int i = 1; i < line.length; i++) {
            myAllColTitles[i - 1] = line[i];
            List<Double> colValues = new ArrayList<Double>();
            myAllValuesByCol.put(line[i], colValues);
        }
    }
/**
 * Fills row of data.
 * @param line an array of data
 */
    public void processValues (String[] line) {
        List<Double> rowValues = new ArrayList<Double>();
        for (int i = 1; i < line.length; i++) {
            double val = Double.parseDouble(line[i]);
            rowValues.add(val);
            myAllValuesByCol.get(myAllColTitles[i - 1]).add(val);
        }
        myAllValuesByRow.put(line[0], rowValues);
    }
/**
 * Gets list of countries.
 * @return
 */
    public String[] getAllCountries () {
        return myAllRowTitles;
    }
/**
 * Gets list of years.
 * @return
 */
    public String[] getAllYears () {
        return myAllColTitles;
    }
}
