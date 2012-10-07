package controller;

import java.util.HashMap;
import model.Model;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;


public class Controller {
    private Model myModel;
    private HashMap<String, Visualization> myNameMap;
    private BarGraph bar;
    private LineGraph line;

    public Controller () {
        myModel = new Model();
        myNameMap = new HashMap<String, Visualization>();
        generateMap();
    }

    public void loadFile () {
        myModel.loadFile();
    }

    public void generateMap () {
        myNameMap.put("Bar Graph", bar);
        myNameMap.put("Line Graph", line);
    }

    public void getData (Visualization requestedVis) {//String visType, String[] countries, double[] years) {
//        Visualization requestedVis =  myNameMap.get(visType);
//        if(visType.equals("Bar Graph")) {
//            requestedVis = new BarGraph(this);
//        }
//        if(visType.equals("Line Graph")) {
//            requestedVis = new LineGraph(this);
//        }
//        requestedVis.setMyCountries(countries);
//        requestedVis.setMyYears(years);
        myModel.updateVisualization(requestedVis);
    }

    public double[] getYears () {
        return myModel.getYears();
    }

    public String[] getCountries () {
        return myModel.getCountries();
    }
}
