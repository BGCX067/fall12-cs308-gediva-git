package visualizations;


import java.awt.*;
import java.util.List;
import javax.swing.JFrame;
import view.ControlPanel;
import controller.Controller;

/**
 * 
 * @author Xi Du
 *
 */
@SuppressWarnings("serial")
public class BarGraph extends Visualization {

    private static final String MY_NAME = "Bar Graph";

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

    public BarGraph () {

    }

    public BarGraph (List<Double> values, String selectedRowOrColTitle, Controller contr) {
        super(values, selectedRowOrColTitle, contr);
        setVisTitle(MY_NAME + " for " + selectedRowOrColTitle);
    }


    public void setTitle (String selectedRowOrColTitle) {
        setVisTitle("Bar Graph for " + selectedRowOrColTitle);
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
        Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Book Antiqua", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
        int titleWidth = titleFontMetrics.stringWidth(MY_NAME);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(MY_NAME, p, q);
        int top = titleFontMetrics.getHeight()+10;
        int bottom = labelFontMetrics.getHeight()+10;
        if (getMaxValue() == getMinValue())
            return;
        double scale = (clientHeight - top - bottom) / (getMaxValue() - getMinValue());
        q = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        for (int j = 0; j < getValues().size(); j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (getValues().get(j) * scale);
            if (getValues().get(j) >= 0)
                valueQ += (int) ((getMaxValue() - getValues().get(j)) * scale);
            else {
                valueQ += (int) (getMaxValue() * scale);
                height = -height;
            }
            g.setColor(Color.blue);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(getController().getAllCountries()[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(getController().getAllCountries()[j], p, q);
        }
    }

    /**
     * Listening behavior for BarGraph
     * @param event ActionEvent
     * @param p Control Panel
     * @param c Controller 
     */
    public static void listen(String event, final ControlPanel p, final Controller c) {
        p.clearList();
        for (String year : c.getAllYears()) {
            p.addToList(year);
        }
        p.showMessage("Click on year to display.");
    }
}
