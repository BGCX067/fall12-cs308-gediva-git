package view;

import java.util.Arrays;
import java.util.HashMap;
import javax.swing.text.html.HTMLDocument.Iterator;
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
        BarGraph barGraph = (BarGraph) myController.getData(
                             selectedVisualizatoin, countriesToDisplayOnBar, yearSelectedForBar);
        // map of countries to respective values for given year. Plot this data.
        HashMap<String, Double> barValues = barGraph.getValues();
        // LineGraph object with data.
        selectedVisualizatoin = "Line Graph";
        LineGraph lineGraph = (LineGraph) myController.getData(
                              selectedVisualizatoin, countrySelectedForLine, yearsToDisplayOnLine);
        // map of years to respective values for given country. Plot this data.
        HashMap<String, Double> lineValues = lineGraph.getValues();
        // do work. 
        // use selectedVisualization, myAllCountries, myAllYears to build plot area
        // for bar, use barValues and yearSelectedForBar[0] (plot label)
        // for line, use lineValues and countrySelectedForLine[0] (plot label)
        System.out.println("All countries: " + Arrays.toString(myAllCountries));
        System.out.println("All years: " + Arrays.toString(myAllYears));
        System.out.println("Build bar visualization for " + yearSelectedForBar[0] + ": " + barValues);
        System.out.println("Build line visuazation for " + countrySelectedForLine[0] + ": " + lineValues);
    }
}
