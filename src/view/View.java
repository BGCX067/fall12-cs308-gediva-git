package view;

import java.util.Arrays;
import controller.Controller;

/**
 * Displays different visualizations on a GUI.
 * @author Sam Rang and Xi Du
 *
 */
public class View {
    Controller myController;
    String[] myCountries;
    double[] myYears;
    
    public View () {
        myController = new Controller();

        myCountries=myController.getCountries();
        myYears=myController.getYears();
    }
    
    public void run(){
        myController.loadFile();
        //DO WORK
    }
}
