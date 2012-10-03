package view;

import java.util.Arrays;
import java.util.HashMap;
import visualizations.BarGraph;
import visualizations.LineGraph;
import controller.Controller;

/**
 * Displays different visualizations on a GUI.
 * @author Sam Rang and Xi Du
 *
 */
public class View {
    Controller myController;
    String[] myAllCountries;
    double[] myAllYears;
    
    public View () {
        myController = new Controller();
    }
    
    public void run(){
        // prompts user to select file with data
        myController.loadFile();
        // list of countries
        myAllCountries = myController.getCountries();
        // list of years
        myAllYears = myController.getYears();
        // for now visualize bar for all countries and 1 year 
        // (i.e. user can't select countries). Can extend later if needed
        String[] countriesToDisplayOnBar = myAllCountries;
        double[] yearSelectedForBar = new double[] {2006};  // user selects year. hardcode for now
        // for now visualize line for all years and 1 country 
        // (i.e. user can't select years). Can extend later if needed
        String[] countrySelectedForLine = new String[] {"USA"}; // user selects country. hardcode for now
        double[] yearsToDisplayOnLine = myAllYears;
        // BarGraph object with data.
        String selectedVisualizatoin = "Bar Graph";
        BarGraph myBarGraph = (BarGraph) myController.getData(
                             selectedVisualizatoin, countriesToDisplayOnBar, yearSelectedForBar);
        // map of countries to respective values for given year. Plot this data.
        HashMap<String, Double> myBarValues = myBarGraph.getValues();
        // LineGraph object with data.
        selectedVisualizatoin = "Line Graph";
        LineGraph myLineGraph = (LineGraph) myController.getData(
                              selectedVisualizatoin, countrySelectedForLine, yearsToDisplayOnLine);
        // map of years to respective values for given country. Plot this data.
        HashMap<String, Double> myLineValues = myLineGraph.getValues();
        // do work. 
        // use selectedVisualization, myAllCountries, myAllYears to build plot area
        // for bar, use myBarValues and yearSelectedForBar (plot label)
        // for line, use myLineValues and countrySelectedForLine (plot label)
        System.out.println(Arrays.toString(myAllCountries));
        System.out.println(Arrays.toString(myAllYears));
    }
}
