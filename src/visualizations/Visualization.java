package visualizations;


import static resources.Constants.CHART_WIDTH;
import static resources.Constants.CHART_HEIGHT;
import java.awt.Graphics2D;
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
 * 
 * @author Sam Rang, Xi Du, Volodymyr, Howard
 */
@SuppressWarnings("serial")
public abstract class Visualization extends JPanel {
    private String mySelectedRowOrColTitle;
    private String myVisTitle;
    private List<Double> myValues;
    private double myMaxValue;
    private double myMinValue;
    private Controller myController;

    public Visualization () {
        // former contents refactored to setValues
    }

    public void setValues (List<Double> values, String selectedRowOrColTitle,
                           Controller contr) {
        myValues = new ArrayList<Double>(values);
        mySelectedRowOrColTitle = selectedRowOrColTitle;
        myMinValue = Collections.min(values);
        
        myMaxValue = Collections.max(values);
        
        myController = contr;
        setVisTitle(selectedRowOrColTitle);
    }

    public String getSelectedRowOrColTitle () {
        return mySelectedRowOrColTitle;
    }

    public List<Double> getValues () {
        return myValues;
    }

    public double getMinValue () {
        return myMinValue;
    }

    public double getMaxValue () {
        return myMaxValue;
    }

    public String getVisTitle () {
        return myVisTitle;
    }

    public Controller getController () {
        return myController;
    }

    public void setVisTitle (final String visTitle) {
        myVisTitle = visTitle;
    }


    public boolean isRowInput(){
        return false;
    }
    
    public abstract void paint (Graphics2D g);



    public abstract void listen (String event, ControlPanel p, Controller c);

    public void visualize (Visualization vis) {
        final JFrame frame = new JFrame(getVisTitle());
        frame.setSize(CHART_WIDTH, CHART_HEIGHT);
        frame.getContentPane().add(vis);
        frame.setVisible(true);
    }
}
