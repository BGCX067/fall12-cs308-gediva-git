package visualizations;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFrame;
import controller.Controller;

/**
 * 
 * @author Xi Du
 *
 */
@SuppressWarnings("serial")
public class LineGraph extends Visualization {

/**
 * @param country add country
 * @param year add year
 * @param value add associated values
 */
    public void addData (String country, double year, double value) {
        getValues().put(Double.toString(year), value);
    }

    /**
     * @param myController
     */
    private Controller myController;

    /**
     *@param maxValue max value used in the paint method
     */
    private double maxValue;
    /**
     *@param minValue min value used in the paint method
     */
    private double minValue;

    /**
     * @param PREF_W width of the frame
     */
    private static final int PREF_W = 1000;

    /**
     * @param PREF_H height of the frame
     */
    private static final int PREF_H = 800;

    /**
     * @param BORDER_GAP gap between graph and frame boarder
     */
    private static final int BORDER_GAP = 30;

    /**
     * @param GRAPH_COLOR line color
     */
    private static final Color GRAPH_COLOR = Color.blue;

    /**
     * @param GRAPH_POINT_COLOR point color
     */
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);

    /**
     * @param GRAPH_STROKE graph stroke
     */
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);

    /**
     * @param GRAPH_POINT_WIDTH point width
     */
    private static final int GRAPH_POINT_WIDTH = 12;

    /**
     * @param Y_HATCH_CNT
     */
    private static final int Y_HATCH_CNT = 10;
    
/**
 * @param scores
 */
    private ArrayList<Double> scores;

/**
 * find the max and min values
 */
    private void checkValues(){
        for(int i=0;i<scores.size();i++){
            if (minValue > scores.get(i))
                minValue = scores.get(i);
            if (maxValue < scores.get(i))
                maxValue = scores.get(i);
        }

    }

    /**
     * constructor
     */
    public LineGraph(Controller c) {
        super(c);
        myController = c;
        c.getData(this);
        HashMap<String, Double> lineValues = getValues();
        scores = new ArrayList<Double>(lineValues.values());



    }
    /**
     * Paint method
     * @param Graphics g
     */
    @Override
    public void paint(Graphics g) {
        checkValues();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxValue - minValue);

        ArrayList<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((maxValue - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes 
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis. 
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    /**
     * Returns preferred size
     */
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    /**
     * @param country specify which country to display
     */
    public  void createAndShowLineGui(String[] country) {
        setMyCountries(country);
        JFrame frame = new JFrame("Line Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
     }
}

