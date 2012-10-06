package controller;

import java.util.HashMap;
import model.Model;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;


public class Controller {
    private Model myModel;
    private HashMap<String, Visualization> myNameMap;

    public Controller () {
        myModel = new Model();
        myNameMap = new HashMap<String, Visualization>();
        generateMap();
    }

    public void loadFile () {
        myModel.loadFile();
    }

    public void generateMap () {
        myNameMap.put("Bar Graph", new BarGraph(""));
        myNameMap.put("Line Graph", new LineGraph(""));
    }

    public Visualization getData (String visType, String[] countries, double[] years) {
        Visualization requestedVis =  myNameMap.get(visType);
        requestedVis.setMyCountries(countries);
        requestedVis.setMyYears(years);
        myModel.updateVisualization(requestedVis);
        return requestedVis;
    }

    public double[] getYears () {
        return myModel.getYears();
    }

    public String[] getCountries () {
        return myModel.getCountries();
    }
}
