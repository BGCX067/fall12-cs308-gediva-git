package visualizations;


import controller.Controller;
import java.awt.*;
import java.util.*;
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
     * @param value the ArrayList to store the data
     */
    private ArrayList<Double> value=new ArrayList<Double>();
    private String title;
    /**
     * Constructor
     */
    public BarGraph(Controller c) {
        super(c);
        myController = c;
        c.getData(this);
        HashMap<String, Double> barValues = getValues();
        for(int i=0;i<getMyCountries().length;i++){
            value.add(barValues.get(getMyCountries()[i]));
        }
        title = "Bar Graph";
    }

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
     * @param g Graphics
     */
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (value == null || value.size() == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < value.size(); i++) {
            if (minValue > value.get(i))
                minValue = value.get(i);
            if (maxValue < value.get(i))
                maxValue = value.get(i);
        }
        Dimension dim = getSize();
        int clientWidth = dim.width;
        int clientHeight = dim.height;
        int barWidth = clientWidth / value.size();
        Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Book Antiqua", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
        int titleWidth = titleFontMetrics.stringWidth(title);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, p, q);
        int top = titleFontMetrics.getHeight()+10;
        int bottom = labelFontMetrics.getHeight()+10;
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        q = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        for (int j = 0; j < value.size(); j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (value.get(j) * scale);
            if (value.get(j) >= 0)
                valueQ += (int) ((maxValue - value.get(j)) * scale);
            else {
                valueQ += (int) (maxValue * scale);
                height = -height;
            }
            g.setColor(Color.blue);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(getMyCountries()[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(getMyCountries()[j], p, q);
        }
    }

    /**
     * 
     * @param b BarGraph passed
     * @param oneyear specify which year to display
     */
    public void createAndShowBarGui(double[] oneyear) { 
        setMyYears(oneyear);
        myController.getData(this);
        //        String[] s=new String[]{};
        //        myController.getData("Bar Graph", myCountries, oneyear);
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
}
