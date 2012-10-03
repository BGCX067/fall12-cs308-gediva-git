package model;

import java.util.ArrayList;
import java.util.HashMap;
import visualizations.BarGraph;
import visualizations.Visualization;


public class Model {
    private FileOpener myFileOpener;
    private String[] myCountryList;
    private double[] myYears;
    private HashMap<String, ArrayList<Double>> myDataValues;

    public Model () {
        myFileOpener = new FileOpener();
    }

    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        myCountryList = new String[lines.size() - 1];
        myYears = new double[lines.get(0).length - 1];
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                addYears(lines.get(0));
            }
            myCountryList[i] = lines.get(i)[0];
            convertAdd(lines.get(i));
        }
    }

    public void addYears (String[] line) {
        for (int i = 1; i < line.length; i++) {
            myYears[i - 1] = Double.parseDouble(line[i]);
        }
    }

    public void convertAdd (String[] line) {
        ArrayList<Double> dataValues = new ArrayList<Double>();
        for (int j = 1; j < line.length; j++) {
            dataValues.add(Double.parseDouble(line[j]));
        }
        myDataValues.put(line[0], dataValues);
    }

    public double locateYear (double year) {
        for (int i = 0; i < myYears.length; i++) {
            if (year == myYears[i]) { return i; }
        }
        return -1;
    }

    public double getDataPoint (String country, int location) {
        return myDataValues.get(country).get(location);
    }
    
    
    public Visualization updateVisualization(Visualization vis){
        vis.clearValues();
        //for bar graphs
        for (int i=0;i<vis.getMyCountries().length;i++){
            double dataPoint=0;
            vis.addData(vis.getMyCountries()[i],vis.getMyYear()[0],dataPoint);
        }
        //for line graphs
        
        return vis;
    }
}
