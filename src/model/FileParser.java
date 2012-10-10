package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FileParser {
    List<String[]> lines = new ArrayList<String[]>();
    private String[] myAllRowTitles;
    private String[] myAllColTitles;
    private HashMap<String, List<Double>> myAllValuesByRow;
    private HashMap<String, List<Double>> myAllValuesByCol;

    public FileParser () {
        myAllValuesByRow = new HashMap<String, List<Double>>();
        myAllValuesByCol = new HashMap<String, List<Double>>();
    }

    public void parse (String[] line) {
        lines.add(line);

    }

    public void fillData () {
        myAllColTitles = new String[lines.get(0).length - 1];
        myAllRowTitles = new String[lines.size() - 1];
        processColTitles(lines.get(0));
        for (int i = 0; i < lines.size(); i++) {
            if (i != 0) {
                myAllRowTitles[i - 1] = lines.get(i)[0];
                processValues(lines.get(i));
            }
        }
        System.out.println(myAllValuesByRow);
        System.out.println(myAllValuesByCol);
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
            double val = Double.parseDouble(line[i]);
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
    public String[] getAllCountries () {
        return myAllRowTitles;
    }

    /**
     * Gets list of years.
     * 
     * @return
     */
    public String[] getAllYears () {
        return myAllColTitles;
    }

    public HashMap<String, List<Double>> getValuesByRow () {
        return myAllValuesByRow;
    }

    public HashMap<String, List<Double>> getValuesByCol () {
        return myAllValuesByCol;
    }
}
