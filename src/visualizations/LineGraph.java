package visualizations;


import java.awt.*;
import java.util.*;
import java.util.List;
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
     * @param PREF_W width of the frame
     */
    private static final int PREF_W = 600;

    /**
     * @param PREF_H height of the frame
     */
    private static final int PREF_H = 400;

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

    public void setTitle (String selectedRowOrColTitle) {
        setVisTitle("Line Graph for " + selectedRowOrColTitle);
    }
    /**
     * Paint method
     * @param Graphics g
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (getValues().size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (getMaxValue() - getMinValue());

        ArrayList<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < getValues().size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((getMaxValue() - getValues().get(i)) * yScale + BORDER_GAP);
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
        for (int i = 0; i < getValues().size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (getValues().size() - 1) + BORDER_GAP;
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

    public void visualize() {
        JFrame frame = new JFrame(getVisTitle());
        frame.setSize(PREF_W, PREF_H);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
}

