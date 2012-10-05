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
                myCountryList[i - 1] = lines.get(i)[0];
                addValues(lines.get(i));
            }
        }
    }

    public void addYears (String[] line) {
        for (int i = 1; i < line.length; i++) {
            myYears[i - 1] = Double.parseDouble(line[i]);
        }
    }

    public void addValues (String[] line) {
        ArrayList<Double> dataValues = new ArrayList<Double>();
        for (int j = 1; j < line.length; j++) {
            dataValues.add(Double.parseDouble(line[j]));
        }
        myDataValues.put(line[0], dataValues);
    }

    public int locateYear (double year) {
        for (int i = 0; i < myYears.length; i++) {
            if (year == myYears[i]) { return i; }
        }
        return -1;
    }

    public double getDataPoint (String country, int location) {
        return myDataValues.get(country).get(location);
    }

    public Visualization updateVisualization (Visualization vis) {
        vis.clearValues();
        String visName = vis.getClass().getName();
        if ("visualizations.BarGraph".equals(visName)) {
            for (int i = 0; i < vis.getMyCountries().length; i++) {
                double year = vis.getMyYears()[0];
                String country = vis.getMyCountries()[i];
                int yearLocation = locateYear(year);
                double dataPoint = getDataPoint(country, yearLocation);
                vis.addData(country, year, dataPoint);
            }
        }
        else if ("visualizations.LineGraph".equals(visName)) {
            for (int i = 0; i < vis.getMyYears().length; i++) {
                double year = vis.getMyYears()[i];
                String country = vis.getMyCountries()[0];
                int yearLocation = locateYear(year);
                double dataPoint = getDataPoint(country, yearLocation);
                vis.addData(country, year, dataPoint);
            }
        }
        return vis;
    }

    public String[] getCountries () {
        return myCountryList;
    }

    public double[] getYears () {
        return myYears;
    }
}
