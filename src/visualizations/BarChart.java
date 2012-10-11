package visualizations;


import java.awt.*;
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
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (getValues() == null || getValues().size() == 0) {
            return;
        }
        Dimension dim = getSize();
        int clientWidth = dim.width;
        int clientHeight = dim.height;
        int barWidth = clientWidth / getValues().size();
        FontMetrics titleFontMetrics = g.getFontMetrics(CHART_TITLE_FONT);
        FontMetrics labelFontMetrics = g.getFontMetrics(CHART_LABEL_FONT);
        int titleWidth = titleFontMetrics.stringWidth(BAR_VIS_TITLE);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        g.setFont(CHART_TITLE_FONT);
        g.drawString(BAR_VIS_TITLE, p, q);
        int top = titleFontMetrics.getHeight()+10;
        int bottom = labelFontMetrics.getHeight()+10;
        if (getMaxValue() == getMinValue()) {
            return;
        }
        double scale = (clientHeight - top - bottom) / (getMaxValue() - getMinValue());
        q = clientHeight - labelFontMetrics.getDescent();
        g.setFont(CHART_LABEL_FONT);
        for (int j = 0; j < getValues().size(); j++) {
            int valueP = j * barWidth + 1;
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
            int labelWidth = labelFontMetrics.stringWidth(getController().getAllRowTitles()[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(getController().getAllRowTitles()[j], p, q);
        }
    }

    /**
     * Listening behavior for BarGraph
     * @param event ActionEvent
     * @param p Control Panel
     * @param c Controller 
     */
    public void listen(String event, final ControlPanel p, final Controller c) {
        p.clearList();
        for (String year : c.getAllColTitles()) {
            p.addToList(year);
        }
        p.showMessage("Click on year to display.");
    }
}
