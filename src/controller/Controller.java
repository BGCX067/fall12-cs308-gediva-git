package controller;

import java.util.HashMap;
import visualizations.BarGraph;
import visualizations.LineGraph;
import visualizations.Visualization;
import model.Model;


public class Controller {
    Model myModel;
    HashMap<String, Visualization> myNameMap;

    public Controller () {
        myModel = new Model();
        myNameMap = new HashMap<String, Visualization>();
        generateMap();
    }

    public void loadFile () {
        myModel.loadFile();
    }
    
    public void generateMap(){
        myNameMap.put("Bar Graph", new BarGraph());
        myNameMap.put("Line Graph", new LineGraph());
    }
    
    public Visualization getData(String[] countries, double[] years, String visType){
        myNameMap.get(visType).setMyCountries(countries);
        myNameMap.get(visType).setMyYear(years);
        myModel.updateVisualization(myNameMap.get(visType));
        return myNameMap.get(visType);
    }
    
    public double[] getYears() {
        return myModel.getYears();
    }
    
    public String[] getCountries() {
        return myModel.getCountries();
    }
}
