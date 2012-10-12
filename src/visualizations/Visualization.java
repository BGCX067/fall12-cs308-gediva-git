package visualizations;

import controller.Controller;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.ControlPanel;
import static resources.Constants.CHART_WIDTH;
import static resources.Constants.CHART_HEIGHT;




/**
 * Abstract class for items that can be displayed on.
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
    /**
     * former contents refactored to setValues.
     */
    public Visualization () {
        // former contents refactored to setValues
    }
    /**
     * @param values values used in the graph
     * @param selectedRowOrColTitle row or column title
     * @param contr controller
     */
    public void setValues (List<Double> values, String selectedRowOrColTitle,
            Controller contr) {
        myValues = new ArrayList<Double>(values);
        mySelectedRowOrColTitle = selectedRowOrColTitle;
        myMinValue = Collections.min(values);
        myMaxValue = Collections.max(values);
        myController = contr;
        setVisTitle(selectedRowOrColTitle);
    }
    /**
     *
     * @return mySelectedRowOrColTitle
     */
    public String getSelectedRowOrColTitle () {
        return mySelectedRowOrColTitle;
    }
    /**
     *
     * @return myValues
     */
    public List<Double> getValues () {
        return myValues;
    }
    /**
     *
     * @return myMinValue
     */
    public double getMinValue () {
        return myMinValue;
    }
    /**
     *
     * @return myMaxValue
     */
    public double getMaxValue () {
        return myMaxValue;
    }
    /**
     *
     * @return myVisTitle
     */
    public String getVisTitle () {
        return myVisTitle;
    }
    /**
     *
     * @return myController
     */
    public Controller getController () {
        return myController;
    }
    /**
     *
     * @param visTitle string
     */
    public void setVisTitle (final String visTitle) {
        myVisTitle = visTitle;
    }

    /**
     *
     * @return if row has been inputed
     */
    public boolean isRowInput() {
        return false;
    }
    @Override
    /**
     *
     * @param g Graphics2D
     */
    public abstract void paint (Graphics g);
    /**
     *
     * @param event string
     * @param p ControlPanel
     * @param c controller
     */
    public abstract void listen (String event, ControlPanel p, Controller c);
    /**
     * visualize the graph.
     */
    public void visualize () {
        JFrame frame = new JFrame(getVisTitle());
        frame.setSize(CHART_WIDTH, CHART_HEIGHT);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
}
