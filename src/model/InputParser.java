package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import resources.Constants;


/**
 * Parses input lines.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class InputParser {
    private List<String[]> myInputLines;
    private String[] myAllRowTitles;
    private String[] myAllColTitles;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;

    /**
     * Constructs a new parser.
     */
    public InputParser () {
        myAllValuesByRow = new HashMap<String, List<Double>>();
        myAllValuesByCol = new HashMap<String, List<Double>>();
        myAllRowTitles = new String[0];
        myAllColTitles = new String[0];
        myInputLines = new ArrayList<String[]>();
    }

    /**
     * creates a backup copy to use in case of input failure
     * @param other to copy
     */
    public InputParser (InputParser other) {
        myAllValuesByRow = new HashMap<String, List<Double>>(other.myAllValuesByRow);
        myAllValuesByCol = new HashMap<String, List<Double>>(other.myAllValuesByCol);
        myAllRowTitles = Arrays.copyOf(other.myAllRowTitles, other.myAllRowTitles.length);
        myAllColTitles = Arrays.copyOf(other.myAllColTitles, other.myAllColTitles.length);
        myInputLines = new ArrayList<String[]>(other.myInputLines);
    }

    /**
     * Adds the given line to the list of lines.
     * 
     * @param line an array of words in a line
     */
    public void parse (String[] line) {
        myInputLines.add(line);
    }

    /**
     * Fills in data by columns and rows
     * 
     * @return
     */
    public boolean fillData () {
        if (!inputIsValid()) { return false; }
        myAllColTitles = new String[myInputLines.get(0).length - 1];
        myAllRowTitles = new String[myInputLines.size() - 1];
        processColTitles(myInputLines.get(0));
        for (int i = 0; i < myInputLines.size(); i++) {
            if (i != 0) {
                myAllRowTitles[i - 1] = myInputLines.get(i)[0];
                processValues(myInputLines.get(i));
            }
        }
        return true;
    }

    private boolean inputIsValid () {
        boolean valid = false;
        int numOfLines = myInputLines.size();
        if (numOfLines > 1) {
            int sizeOfFirstLine = myInputLines.get(0).length;
            if (sizeOfFirstLine > 1) {
                valid = true;
                for (String[] line : myInputLines) {
                    if (line.length != sizeOfFirstLine) {
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    /**
     * Fills column of data.
     * 
     * @param line an array of data
     */
    public void processColTitles (final String[] line) {
        for (int i = 1; i < line.length; i++) {
            myAllColTitles[i - 1] = line[i];
            List<Double> colValues = new ArrayList<Double>();
            myAllValuesByCol.put(line[i], colValues);
        }
    }

    /**
     * Fills row of data.
     * 
     * @param line an array of data
     */
    public void processValues (final String[] line) {
        List<Double> rowValues = new ArrayList<Double>();
        for (int i = 1; i < line.length; i++) {
            double val;
            try {
                val = Double.parseDouble(line[i]);
            }
            catch (NumberFormatException e) {
                val = Constants.DEFAULT_INPUT_VALUE;
            }
            rowValues.add(val);
            myAllValuesByCol.get(myAllColTitles[i - 1]).add(val);
        }
        myAllValuesByRow.put(line[0], rowValues);
    }

    /**
     * Gets list of countries.
     * 
     * @return
     */
    public String[] getAllRowTitles () {
        return myAllRowTitles;
    }

    /**
     * Gets list of years.
     * 
     * @return
     */
    public String[] getAllColTitles () {
        return myAllColTitles;
    }
/**
 * Gets values by rows.
 * @return
 */
    public HashMap<String, List<Double>> getValuesByRow () {
        return myAllValuesByRow;
    }
/**
 * Gets values by columns.
 * @return
 */
    public HashMap<String, List<Double>> getValuesByCol () {
        return myAllValuesByCol;
    }

    /**
     * Flushes previously read input lines
     */
    public void clear () {
        myInputLines = new ArrayList<String[]>();
    }
}
