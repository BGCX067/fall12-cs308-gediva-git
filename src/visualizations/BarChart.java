package visualizations;

import controller.Controller;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import view.ControlPanel;

//import constants
import static resources.Constants.BAR_BORDER_COLOR;
import static resources.Constants.BAR_BUTTON_ONCLICK_MESSAGE;
import static resources.Constants.BAR_FILL_COLOR;
import static resources.Constants.CHART_LABEL_FONT;
import static resources.Constants.CHART_TITLE_FONT;
import static resources.Constants.BAR_VIS_TITLE;

/**
 *
 * @author  Xi Du,Sam Rang, Volodymyr, Howard
 *
 */
@SuppressWarnings("serial")
public class BarChart extends Visualization {
    /**
     * @param myDim
     */
    private final Dimension myDim = getSize();
/**
 * @param myClientWidth
 */
    private final  int myClientWidth = myDim.width;
        /**
         * @param myClientHeight
         */
    private final  int myClientHeight = myDim.height;
/**
         * @param myBarWidth
         */
    private final int myBarWidth = myClientWidth / getValues().size();
/**
 * Empty construct for initialization.
 */
    public BarChart() {
        System.out.println(getValues());
    }

    @Override
    /**
     * @param g Graphics
     */
    public final void paint(final Graphics2D g) {
        super.paintComponent(g);
        if (getValues() == null || getValues().size() == 0) { return; }
        final FontMetrics titleFontMetrics = g.getFontMetrics(CHART_TITLE_FONT);
        final FontMetrics labelFontMetrics = g.getFontMetrics(CHART_LABEL_FONT);
        final int titleWidth = titleFontMetrics.stringWidth(BAR_VIS_TITLE);
        int q = titleFontMetrics.getAscent();
        int p = (myClientWidth - titleWidth) / 2;
        g.setFont(CHART_TITLE_FONT);
        g.drawString(BAR_VIS_TITLE, p, q);
        final int top = titleFontMetrics.getHeight();
        final int bottom = labelFontMetrics.getHeight();
        if (getMaxValue() == getMinValue()) { return; }
        final double scale = (myClientHeight - top - bottom)
                / (getMaxValue() - getMinValue());
        q = myClientHeight - labelFontMetrics.getDescent();
        g.setFont(CHART_LABEL_FONT);
        drawComponent(g, labelFontMetrics, q, top, scale);
    }
/**
 * Draw bar chart component.
 * @param g
 * @param labelFontMetrics
 * @param q
 * @param top
 * @param scale
 */
    private void drawComponent (final Graphics2D g,
            final FontMetrics labelFontMetrics, int q, final int top,
            final double scale) {
        int p;
        for (int j = 0; j < getValues().size(); j++) {
            final int valueP = j * myBarWidth + 1;
            int valueQ = top;
            int height = (int) (getValues().get(j) * scale);
            if (getValues().get(j) >= 0) {
                valueQ += (int) ((getMaxValue() - getValues().get(j)) * scale);
            }
            else {
                valueQ += (int) (getMaxValue() * scale);
                height = -height;
            }
            g.setColor(BAR_FILL_COLOR);
            g.fillRect(valueP, valueQ, myBarWidth - 2, height);
            g.setColor(BAR_BORDER_COLOR);
            g.drawRect(valueP, valueQ, myBarWidth - 2, height);
            final int labelWidth =
                    labelFontMetrics.stringWidth(
                            getController().getAllRowTitles()[j]);
            p = j * myBarWidth + (myBarWidth - labelWidth) / 2;
            //convert the double value to String
            String s = getValues().get(j) + "";
            //draw the y axis value on top of the bar
            g.drawString(s, p, valueQ);
            g.drawString(getController().getAllRowTitles()[j], p, q);
        }
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
