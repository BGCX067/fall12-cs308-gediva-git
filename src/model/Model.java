package model;

import java.util.ArrayList;
import java.util.HashMap;
import visualizations.Visualization;


public class Model {
    private FileOpener myFileOpener;
    private String[] myCountryList;
    private double[] myYears;
    private HashMap<String, ArrayList<Double>> myDataValues;

    public Model () {
        myFileOpener = new FileOpener();
        myDataValues = new HashMap<String, ArrayList<Double>>();
    }

    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        myCountryList = new String[lines.size() - 1];
        myYears = new double[lines.get(0).length - 1];
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                addYears(lines.get(0));
            }
            else {
                addCountries(lines, i);
                addValues(lines.get(i));
            }
        }
    }

    private void addCountries (ArrayList<String[]> lines, int lineNumber) {
        myCountryList[lineNumber - 1] = lines.get(lineNumber)[0];
    }

    private void addYears (String[] line) {
        for (int i = 1; i < line.length; i++) {
            myYears[i - 1] = Double.parseDouble(line[i]);
        }
    }

    private void addValues (String[] line) {
        ArrayList<Double> dataValues = new ArrayList<Double>();
        for (int j = 1; j < line.length; j++) {
            dataValues.add(Double.parseDouble(line[j]));
        }
        myDataValues.put(line[0], dataValues);
    }

    private int locateYear (double year) {
        for (int i = 0; i < myYears.length; i++) {
            if (year == myYears[i]) { return i; }
        }
        return -1;
    }

    private double getDataPoint (String country, int location) {
        return myDataValues.get(country).get(location);
    }

    public Visualization updateVisualization (Visualization vis, String[] countries, double[] years) {
        vis.setMyCountries(countries);
        vis.setMyYears(years);
        vis.clearValues();
        String visName = vis.getClass().getName();
        if ("visualizations.BarGraph".equals(visName)) {
            updateBarGraph(vis);
        }
        else if ("visualizations.LineGraph".equals(visName)) {
            updateLineGraph(vis);
        }
        return vis;
    }

    private void updateLineGraph (Visualization vis) {
        for (int i = 0; i < vis.getMyYears().length; i++) {
            double year = vis.getMyYears()[i];
            String country = vis.getMyCountries()[0];
            int yearLocation = locateYear(year);
            double dataPoint = getDataPoint(country, yearLocation);
            vis.addData(country, year, dataPoint);
        }
    }

    private void updateBarGraph (Visualization vis) {
        for (int i = 0; i < vis.getMyCountries().length; i++) {
            double year = vis.getMyYears()[0];
            String country = vis.getMyCountries()[i];
            int yearLocation = locateYear(year);
            double dataPoint = getDataPoint(country, yearLocation);
            vis.addData(country, year, dataPoint);
        }
    }

    public String[] getCountries () {
        return myCountryList;
    }

    public double[] getYears () {
        return myYears;
    }
}
