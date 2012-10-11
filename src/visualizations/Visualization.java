package visualizations;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.ControlPanel;
import controller.Controller;




/**
 * Abstract class for items that can be displayed on
 * the View
 * @author Sam Rang, Xi Du
 */
@SuppressWarnings("serial")
public abstract class Visualization extends JPanel {
    private String mySelectedRowOrColTitle;
    private String myVisTitle;
    private List<Double> myValues;
    private double myMaxValue;
    private double myMinValue;
    private Controller myController;
    /**
     * @param PREF_W width of the frame
     */
    private static final int PREF_W = 600;

    /**
     * @param PREF_H height of the frame
     */
    private static final int PREF_H = 400;

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
        setVisTitle(selectedRowOrColTitle);
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
    
    public abstract void listen(String event, final ControlPanel p, final Controller c);
    
    public void visualize() {
        JFrame frame = new JFrame(getVisTitle());
        frame.setSize(PREF_W, PREF_H);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
}
