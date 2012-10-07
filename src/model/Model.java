package model;

import java.util.ArrayList;
import java.util.HashMap;
import visualizations.Visualization;


public class Model {
    private FileOpener myFileOpener;
    private String[] myAllCountries;
    private double[] myAllYears;
    private HashMap<String, ArrayList<Double>> myAllDataValues;

    public Model () {
        myFileOpener = new FileOpener();
        myAllDataValues = new HashMap<String, ArrayList<Double>>();
    }

    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        myAllCountries = new String[lines.size() - 1];
        myAllYears = new double[lines.get(0).length - 1];
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                addYears(lines.get(0));
            }
            else {
                myAllCountries[i - 1] = lines.get(i)[0];
                addValues(lines.get(i));
            }
        }
    }
    
    public Visualization updateVisualization (Visualization vis) {
        vis.clearValues();
        vis.setMyCountries(myAllCountries);
        vis.setMyYears(myAllYears);
        String visName = vis.getClass().getName();
        if (visName == "visualizations.BarGraph") {
            updateBarGraph(vis);
        }
        else if (visName == "visualizations.LineGraph") {
            updateLineGraph(vis);
        }
        return vis;
    }

    public void updateLineGraph (Visualization vis) {
        for (int i = 0; i < vis.getMyYears().length; i++) {
            double year = vis.getMyYears()[i];
            String country = vis.getMyCountries()[0];
            int yearLocation = locateYear(year);
            double dataPoint = getDataPoint(country, yearLocation);
            vis.addData(country, year, dataPoint);
        }
    }

    public void updateBarGraph (Visualization vis) {
        vis.setMyCountries(myAllCountries);
        for (int i = 0; i < vis.getMyCountries().length; i++) {
            double year = vis.getMyYears()[0];
            String country = vis.getMyCountries()[i];
            int yearLocation = locateYear(year);
            double dataPoint = getDataPoint(country, yearLocation);
            vis.addData(country, year, dataPoint);
        }
    }

    public void addYears (String[] line) {
        for (int i = 1; i < line.length; i++) {
            myAllYears[i - 1] = Double.parseDouble(line[i]);
        }
    }

    public void addValues (String[] line) {
        ArrayList<Double> dataValues = new ArrayList<Double>();
        for (int j = 1; j < line.length; j++) {
            dataValues.add(Double.parseDouble(line[j]));
        }
        myAllDataValues.put(line[0], dataValues);
    }

    public int locateYear (double year) {
        for (int i = 0; i < myAllYears.length; i++) {
            if (year == myAllYears[i]) { return i; }
        }
        return -1;
    }

    public double getDataPoint (String country, int location) {
        return myAllDataValues.get(country).get(location);
    }

    public String[] getAllCountries () {
        return myAllCountries;
    }

    public double[] getAllYears () {
        return myAllYears;
    }
}
