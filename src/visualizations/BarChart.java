package visualizations;

import controller.Controller;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.ControlPanel;

//import constants
import static resources.Constants.GAP_BETWEEN_CHART_AND_FRAME;
import static resources.Constants.TEXT_COLOR;
import static resources.Constants.BAR_BUTTON_ONCLICK_MESSAGE;
import static resources.Constants.BAR_FILL_COLOR;
import static resources.Constants.CHART_LABEL_FONT;

/**
 *
 * @author  Xi Du, Sam Rang, Volodymyr, Howard
 *
 */
@SuppressWarnings("serial")
public class BarChart extends Visualization {
    private int myFrameWidth;
    private int myFrameHeight;
    private int myBarWidth;
    /**
     * Empty constructor for initialization.
     */
    public BarChart() {
    }

    @Override
    /**
     * @param g Graphics
     */
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (getValues() == null || getValues().size() == 0) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        initializeDimensions();
        FontMetrics labelFont = g.getFontMetrics(CHART_LABEL_FONT);
        int xLabelHeight = labelFont.getHeight();
        double maxBarHeightScale = (myFrameHeight - GAP_BETWEEN_CHART_AND_FRAME - xLabelHeight) /
                (getMaxValue() - getMinValue());
        g.setFont(CHART_LABEL_FONT);
        drawComponent(g2, labelFont, maxBarHeightScale);
    }

    private void initializeDimensions () {
        myFrameWidth = getSize().width;
        myFrameHeight = getSize().height;
        myBarWidth = myFrameWidth / getValues().size();
    }

    /**
     * Draw bar chart component.
     * @param g
     * @param labelFont
     * @param maBarHeightScale
     */
    private void drawComponent (Graphics2D g, FontMetrics labelFont, double maBarHeightScale) {
        int labelHeight = labelFont.getHeight();
        for (int dataPointIndex = 0; dataPointIndex < getValues().size(); dataPointIndex++) {
            int barTopLeftX = dataPointIndex * myBarWidth + 1;
            int barTopLeftY = labelHeight;
            int currentBarHeight = (int) (getValues().get(dataPointIndex) * maBarHeightScale);
            barTopLeftY += (int) Math.abs(
                        (getMaxValue() - getValues().get(dataPointIndex)) * maBarHeightScale);
            g.setColor(BAR_FILL_COLOR);
            g.fillRect(barTopLeftX, barTopLeftY, myBarWidth - 1, currentBarHeight);
            drawLabels(g, dataPointIndex, barTopLeftY, barTopLeftX);
        }
    }

    private void drawLabels (Graphics2D g, int dataPointIndex, 
            int barTopLeftY, int barTopLeftX) {
        g.setColor(TEXT_COLOR);
        String valueLabel = getValues().get(dataPointIndex).toString();
        String xAxisLabel = getController().getAllRowTitles()[dataPointIndex];
        g.drawString(valueLabel, barTopLeftX, barTopLeftY - 1);
        g.drawString(xAxisLabel, barTopLeftX, myFrameHeight - 1);
    }
    /**
     * @return true if row input
     */
    public boolean isRowInput() {
        return false;
    }

    /**
     * Listening behavior for BarGraph.
     * @param event ActionEvent
     * @param p Control Panel p
     * @param c Controller
     */
    @Override

    public void listen(String event, ControlPanel p, Controller c) {
        p.clearList();
        for (String year : c.getAllColTitles()) {
            p.addToList(year);
        }
        p.showMessage(BAR_BUTTON_ONCLICK_MESSAGE);
    }

}
