package visualizations;


import controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 * 
 * @author Xi Du
 *
 */
@SuppressWarnings("serial")
public class BarGraph extends Visualization {
    /**
     * @param myController
     */
    private Controller myController;

    /**
     * @param myCountries
     */
    private String[] myCountries;

    /**
     * @param value the ArrayList to store the data
     */
    private ArrayList<Double> value = new ArrayList<Double>();

    /**
     * @param countries
     */
    private String[] countries;

    /**
     * @param title the frame title
     */
    private String title;

    /**
     * @param PREF_W width of the frame
     */
    private static final int PREF_W = 1000;

    /**
     * @param PREF_H height of the frame
     */
    private static final int PREF_H = 800;

    /**
     * @param FONT_SIZE1 font size
     */
    private static final int FONT_SIZE1 = 15;

    /**
     * @param FONT_SIZE1 font size
     */
    private static final int FONT_SIZE2 = 10;

    /**
     * @param GAP the space between graph and frame boarder
     */
    private static final int GAP = 10;
    /**
     * 
     * @param country add country
     * @param year add year
     * @param value add value
     */
    public final void addData(final String country,
            final double year, final double value) {
        getValues().put(country, value);
    }

/**
     * Constructor
     */
    public BarGraph() {
        myCountries = getMyCountries();
        HashMap<String, Double> barValues = getValues();
        /*test case
        =new HashMap<String, Double>();
        barValues.put("Happy", 10.0);
        barValues.put("Mid", 5.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        */
        for (int i = 0; i < myCountries.length; i++) {
            value.add(barValues.get(myCountries[i]));
        }
        title = "Bar Graph";
    }
    /**
     * @param g Graphics
     */
    public final void paint(final Graphics g) {
    super.paintComponent(g);
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < value.size(); i++) {
    if (minValue > value.get(i)) {
        minValue = value.get(i);
    }
    if (maxValue < value.get(i)) {
        maxValue = value.get(i);
    }
    }
    Dimension dim = getSize();
    int clientWidth = dim.width;
    int clientHeight = dim.height;
    int barWidth = clientWidth / value.size();
    Font titleFont = new Font("Book Antiqua", Font.BOLD, FONT_SIZE1);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("Book Antiqua", Font.PLAIN, FONT_SIZE2);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
    int titleWidth = titleFontMetrics.stringWidth(title);
    int q = titleFontMetrics.getAscent();
    int p = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, p, q);
    int top = titleFontMetrics.getHeight() + GAP;
    int bottom = labelFontMetrics.getHeight() + GAP;
    if (maxValue == minValue) {
        return;
    }
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    q = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    for (int j = 0; j < value.size(); j++) {
    int valueP = j * barWidth + 1;
    int valueQ = top;
    int height = (int) (value.get(j) * scale);
    if (value.get(j) >= 0) {
        valueQ += (int) ((maxValue - value.get(j)) * scale);
    }
    else {
    valueQ += (int) (maxValue * scale);
    height = -height;
    }
    g.setColor(Color.blue);
    g.fillRect(valueP, valueQ, barWidth - 2, height);
    g.setColor(Color.black);
    g.drawRect(valueP, valueQ, barWidth - 2, height);
    int labelWidth = labelFontMetrics.stringWidth(countries[j]);
    p = j * barWidth + (barWidth - labelWidth) / 2;
    g.drawString(countries[j], p, q);
    }
    }

    /**
     * 
     * @param b BarGraph passed
     * @param oneyear specify which year to display
     */
   public final  void createAndShowBarGui(final BarGraph b,
           final double[] oneyear) {
    myController.getData("Bar Graph", myController.getCountries(), oneyear);
    JFrame frame = new JFrame();
    frame.setSize(PREF_W, PREF_H);
    frame.getContentPane().add(b);
    frame.setVisible(true);
    }

}
