package visualizations;

//import constants
import static resources.Constants.BAR_BORDER_COLOR;
import static resources.Constants.BAR_BUTTON_ONCLICK_MESSAGE;
import static resources.Constants.BAR_FILL_COLOR;
import static resources.Constants.CHART_LABEL_FONT;
import static resources.Constants.CHART_TITLE_FONT;
import static resources.Constants.BAR_VIS_TITLE;


import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import controller.Controller;
import view.ControlPanel;


/**
 *
 * @author  Xi Du,Sam Rang, Volodymyr, Howard
 *
 */
@SuppressWarnings("serial")
public class BarChart extends Visualization {

/**
 * Empty construct for initialization.
 */
    public BarChart() {
    }

    @Override
    /**
     * @param g Graphics
     */
    public final void paint(final Graphics2D g) {
        super.paintComponent(g);
        if (getValues() == null || getValues().size() == 0) { return; }
        final Dimension dim = getSize();
        final int clientWidth = dim.width;
        final int clientHeight = dim.height;
        final int barWidth = clientWidth / getValues().size();
        final FontMetrics titleFontMetrics = g.getFontMetrics(CHART_TITLE_FONT);
        final FontMetrics labelFontMetrics = g.getFontMetrics(CHART_LABEL_FONT);
        final int titleWidth = titleFontMetrics.stringWidth(BAR_VIS_TITLE);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        g.setFont(CHART_TITLE_FONT);
        g.drawString(BAR_VIS_TITLE, p, q);
        final int top = titleFontMetrics.getHeight();
        final int bottom = labelFontMetrics.getHeight();
        if (getMaxValue() == getMinValue()) { return; }
        final double scale = (clientHeight - top - bottom)
                / (getMaxValue() - getMinValue());
        q = clientHeight - labelFontMetrics.getDescent();
        g.setFont(CHART_LABEL_FONT);
        for (int j = 0; j < getValues().size(); j++) {
            final int valueP = j * barWidth + 1;
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
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(BAR_BORDER_COLOR);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
            final int labelWidth =
                    labelFontMetrics.stringWidth(
                            getController().getAllRowTitles()[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;

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
* @param p Control Panel
* @param c Controller
*/
    @Override
<<<<<<< HEAD
    public final void listen(final String event,
            final ControlPanel p, final Controller c) {
=======
    public void listen(String event, ControlPanel p, Controller c) {
>>>>>>> 3d32fe4103b3781ae5e64f3858a6f982e13002ac
        p.clearList();
        for (String year : c.getAllColTitles()) {
            p.addToList(year);
        }
        p.showMessage(BAR_BUTTON_ONCLICK_MESSAGE);
    }
}
