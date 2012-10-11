package visualizations;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;
import model.Factory;
import view.ControlPanel;
import controller.Controller;
import static resources.Constants.*;

/**
 * 
 * @author Xi Du
 *
 */
@SuppressWarnings("serial")
public class LineChart extends Visualization {

    /**
     * Initiallizes Line Graph
     */
    public LineChart () {

    }

    @Override
    /**
     * Paint method
     * @param Graphics g
     */
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * GAP_BETWEEN_CHART_AND_FRAME) / (getValues().size() - 1);
        double yScale = ((double) getHeight() - 2 * GAP_BETWEEN_CHART_AND_FRAME) / (getMaxValue() - getMinValue());

        ArrayList<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < getValues().size(); i++) {
            int x1 = (int) (i * xScale + GAP_BETWEEN_CHART_AND_FRAME);
            int y1 = (int) ((getMaxValue() - getValues().get(i)) * yScale + GAP_BETWEEN_CHART_AND_FRAME);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes 
        g2.drawLine(GAP_BETWEEN_CHART_AND_FRAME, getHeight() - GAP_BETWEEN_CHART_AND_FRAME, GAP_BETWEEN_CHART_AND_FRAME, GAP_BETWEEN_CHART_AND_FRAME);
        g2.drawLine(GAP_BETWEEN_CHART_AND_FRAME, getHeight() - GAP_BETWEEN_CHART_AND_FRAME, getWidth() - GAP_BETWEEN_CHART_AND_FRAME, getHeight() - GAP_BETWEEN_CHART_AND_FRAME);

        // create hatch marks for y axis. 
        for (int i = 0; i < Y_AXIS_NUBMER_OF_MARKS; i++) {
            int x0 = GAP_BETWEEN_CHART_AND_FRAME;
            int x1 = LINE_POINT_DIAMETER + GAP_BETWEEN_CHART_AND_FRAME;
            int y0 = getHeight() - (((i + 1) * (getHeight() - GAP_BETWEEN_CHART_AND_FRAME * 2)) / Y_AXIS_NUBMER_OF_MARKS + GAP_BETWEEN_CHART_AND_FRAME);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < getValues().size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - GAP_BETWEEN_CHART_AND_FRAME * 2) / (getValues().size() - 1) + GAP_BETWEEN_CHART_AND_FRAME;
            int x1 = x0;
            int y0 = getHeight() - GAP_BETWEEN_CHART_AND_FRAME;
            int y1 = y0 - LINE_POINT_DIAMETER;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(LINE_COLOR);
        g2.setStroke(LINE_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(LINE_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - LINE_POINT_DIAMETER / 2;
            int y = graphPoints.get(i).y - LINE_POINT_DIAMETER / 2;;
            int ovalW = LINE_POINT_DIAMETER;
            int ovalH = LINE_POINT_DIAMETER;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    /**
     * Listening behavior of a Line Graph
     * @param event Action Event
     * @param p Control Panel
     * @param c Controller
     */
    public void listen(String event, final ControlPanel p, final Controller c) {
        p.clearList();
        for (String country : c.getAllRowTitles()) {
            p.addToList(country);
        }
        p.showMessage("Click on year to display.");
    }
}

