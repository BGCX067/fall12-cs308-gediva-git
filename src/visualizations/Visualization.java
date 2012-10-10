package visualizations;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import controller.Controller;



@SuppressWarnings("serial")
public abstract class Visualization extends JPanel {
    private String mySelectedRowOrColTitle;
    private String myVisTitle;
    private List<Double> myValues;
    private double myMaxValue;
    private double myMinValue;
    private Controller myController;

    public Visualization () {
        //former contents refactored to setValues
    }

    public void setValues (List<Double> values, String selectedRowOrColTitle, Controller contr) {
        myValues = new ArrayList<Double>(values);
        mySelectedRowOrColTitle = selectedRowOrColTitle;
        myMinValue = Collections.min(values);
        myMaxValue = Collections.max(values);
        //why does this need a controller?
        myController = contr;
    }
    
    public String getSelectedRowOrColTitle() {
        return mySelectedRowOrColTitle;
    }
    
    public List<Double> getValues() {
        return myValues;
    }
    
    public double getMinValue() {
        return myMinValue;
    }
    
    public double getMaxValue() {
        return myMaxValue;
    }
    
    public String getVisTitle() {
        return myVisTitle;
    }
    
    public Controller getController() {
        return myController;
    }
    
    public void setVisTitle(String visTitle) {
        myVisTitle = visTitle;
    }
    
    public abstract void paint (Graphics g);
    public abstract void visualize();
}
