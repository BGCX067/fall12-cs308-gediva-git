package visualizations;

import static resources.Constants.*;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import view.ControlPanel;


/**
 * 
 * @author Xi Du
 * 
 */
@SuppressWarnings("serial")
public class BarChart extends Visualization {
    /**
     * Empty construct for initialization
     */
    public BarChart () {
    }

    @Override
    /**
     * @param g Graphics
     */
    public void paint (final Graphics g) {
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
        final int top = titleFontMetrics.getHeight() + 10;
        final int bottom = labelFontMetrics.getHeight() + 10;
        if (getMaxValue() == getMinValue()) { return; }
        final double scale = (clientHeight - top - bottom) / (getMaxValue() - getMinValue());
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
                    labelFontMetrics.stringWidth(getController().getAllRowTitles()[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(getController().getAllRowTitles()[j], p, q);
        }
    }

    /**
     * Listening behavior for BarGraph
     * 
     * @param event ActionEvent
     * @param p Control Panel
     * @param c Controller
     */
    @Override
    public void listen (final String event, final ControlPanel p, final Controller c) {
        p.clearList();
        for (final String year : c.getAllColTitles()) {
            p.addToList(year);
        }
        p.showMessage(BAR_BUTTON_ONCLICK_MESSAGE);
    }
}
