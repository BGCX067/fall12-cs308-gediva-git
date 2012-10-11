package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Parses input lines.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class InputParser {
    private final static int DEFAULT_VALUE = 0;
    private List<String[]> myInputLines = new ArrayList<String[]>();
    private String[] myAllRowTitles;
    private String[] myAllColTitles;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;

    public InputParser () {
        myAllValuesByRow = new HashMap<String, List<Double>>();
        myAllValuesByCol = new HashMap<String, List<Double>>();
    }

    public void parse (String[] line) {
        myInputLines.add(line);
    }

    public boolean fillData () {
        if(!inputIsValid()) {
            return false;
        }
        myAllColTitles = new String[myInputLines.get(0).length - 1];
        myAllRowTitles = new String[myInputLines.size() - 1];
        processColTitles(myInputLines.get(0));
        for (int i = 0; i < myInputLines.size(); i++) {
            if (i != 0) {
                myAllRowTitles[i - 1] = myInputLines.get(i)[0];
                processValues(myInputLines.get(i));
            }
        }
        System.out.println(myAllValuesByRow);
        System.out.println(myAllValuesByCol);
        return true;
    }

    private boolean inputIsValid () {
        boolean valid = false;
        int numOfLines = myInputLines.size();
        if(numOfLines > 1) {
            int sizeOfFirstLine = myInputLines.get(0).length;
            if (sizeOfFirstLine > 1) {
                valid = true;
                for (String [] line : myInputLines) {
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
    public void processColTitles (String[] line) {
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
    public void processValues (String[] line) {
        List<Double> rowValues = new ArrayList<Double>();
        for (int i = 1; i < line.length; i++) {
            double val;
            try {
                val = Double.parseDouble(line[i]);
            }
            catch (NumberFormatException e) {
                val = DEFAULT_VALUE;
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

    public HashMap<String, List<Double>> getValuesByRow () {
        return myAllValuesByRow;
    }

    public HashMap<String, List<Double>> getValuesByCol () {
        return myAllValuesByCol;
    }
}
