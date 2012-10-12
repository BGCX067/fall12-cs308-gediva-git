package visualizations;
import controller.Controller;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import view.ControlPanel;

//import constants
import static resources.Constants.GAP_BETWEEN_CHART_AND_FRAME;
import static resources.Constants.Y_AXIS_NUBMER_OF_MARKS;
import static resources.Constants.LINE_POINT_DIAMETER;
import static resources.Constants.LINE_COLOR;
import static resources.Constants.LINE_STROKE;
import static resources.Constants.LINE_POINT_COLOR;
import static resources.Constants.LINE_BUTTON_ONCLICK_MESSAGE;




/**
 *
 * @author Xi Du,Sam Rang, Volodymyr, Howard
 *
 */
@SuppressWarnings("serial")
public class LineChart extends Visualization {
    /**
     * @param myXScale
     */
    private final double myXScale =
            ((double) getWidth() - 2 * GAP_BETWEEN_CHART_AND_FRAME)
            / (getValues().size() - 1);
    /**
     * @param myYScale
     */
    private final double myYScale =
            ((double) getHeight() - 2 * GAP_BETWEEN_CHART_AND_FRAME)
            / (getMaxValue() - getMinValue());

    /**
     * Initializes Line Graph.
     */
    public LineChart() {
        System.out.println(getValues());
    }

    @Override
    /**
     * Paint method
     * @param Graphics g
     */
    public void paint(final Graphics2D g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        final ArrayList<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < getValues().size(); i++) {
            final int x1 = (int) (i * myXScale + GAP_BETWEEN_CHART_AND_FRAME);
            final int y1 =
                    (int) ((getMaxValue() - getValues().get(i)) * myYScale
                            + GAP_BETWEEN_CHART_AND_FRAME);
            graphPoints.add(new Point(x1, y1));
        }
        // create x and y axes
        createXYaxis(g2);
        // create hatch marks for y axis.
        drawMarkforY(g2);
        // and for x axis
        drawMarkforX(g2);

        final Stroke oldStroke = g2.getStroke();
        g2.setColor(LINE_COLOR);
        g2.setStroke(LINE_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            final int x1 = graphPoints.get(i).x;
            final int y1 = graphPoints.get(i).y;
            final int x2 = graphPoints.get(i + 1).x;
            final int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(LINE_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            final int x = graphPoints.get(i).x - LINE_POINT_DIAMETER / 2;
            final int y = graphPoints.get(i).y - LINE_POINT_DIAMETER / 2;
            g2.fillOval(x, y, LINE_POINT_DIAMETER, LINE_POINT_DIAMETER);
        }
    }
    /**
     * draw mark for X.
     * @param g2
     */
    private void drawMarkforX (final Graphics2D g2) {
        for (int i = 0; i < getValues().size() - 1; i++) {
            final int x0 =
                    (i + 1) * (getWidth() - GAP_BETWEEN_CHART_AND_FRAME * 2)
                    / (getValues().size() - 1) + GAP_BETWEEN_CHART_AND_FRAME;
            final int x1 = x0;
            final int y0 = getHeight() - GAP_BETWEEN_CHART_AND_FRAME;
            final int y1 = y0 - LINE_POINT_DIAMETER;
            g2.drawLine(x0, y0, x1, y1);
        }
    }
/**
 * draw mark for Y.
 * @param g2
 */
    private void drawMarkforY (final Graphics2D g2) {
        for (int i = 0; i < Y_AXIS_NUBMER_OF_MARKS; i++) {
            final int x0 = GAP_BETWEEN_CHART_AND_FRAME;
            final int x1 = LINE_POINT_DIAMETER + GAP_BETWEEN_CHART_AND_FRAME;
            final int y0 =
                    getHeight() - (((i + 1) * (getHeight()
                            - GAP_BETWEEN_CHART_AND_FRAME * 2))
                            / Y_AXIS_NUBMER_OF_MARKS
                            + GAP_BETWEEN_CHART_AND_FRAME);
            final int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }
    }
/**
 * create X and Y axis.
 * @param g2
 */
    private void createXYaxis (final Graphics2D g2) {
        g2.drawLine(GAP_BETWEEN_CHART_AND_FRAME, getHeight()
                - GAP_BETWEEN_CHART_AND_FRAME,
                    GAP_BETWEEN_CHART_AND_FRAME, GAP_BETWEEN_CHART_AND_FRAME);
        g2.drawLine(GAP_BETWEEN_CHART_AND_FRAME, getHeight()
                - GAP_BETWEEN_CHART_AND_FRAME,
                    getWidth() - GAP_BETWEEN_CHART_AND_FRAME, getHeight()
                    - GAP_BETWEEN_CHART_AND_FRAME);
    }

    /**
     * Listening behavior of a Line Graph.
     * @param event Action Event
     * @param p Control Panel
     * @param c Controller
     */
    @Override

    public void listen (String event, ControlPanel p, Controller c) {
        p.clearList();
        for (String country : c.getAllRowTitles()) {
            p.addToList(country);
        }
        p.showMessage(LINE_BUTTON_ONCLICK_MESSAGE);
    }

    @Override
    public boolean isRowInput () {
        return true;
    }

}
