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
    }

    public void loadFile () {
        myModel.loadFile();
    }
    
    public void generateMap(){
        myNameMap.put("Bar Graph", new BarGraph());
        myNameMap.put("Line Graph", new LineGraph());
    }
}
