package visualizations;

import view.View;
import java.awt.*;
import javax.swing.*;
import controller.Controller;
import java.awt.event.*;
import java.util.HashMap;

public class BarGraph extends Visualization {
    private Controller myController;
    private String[] myCountries;

    public void addData (String country, double year, double value) {
        getValues().put(country, value);
    }

    private Double[] value;
    private String[] languages;
    private String title;

    public BarGraph(String t) {
        String[] countriesToDisplayOnBar = myCountries;
        double[] yearSelectedForBar = new double[] {2006}; // user selects year. hardcode for now
        // BarGraph object with data.
        String selectedVisualizatoin = "Bar Graph";
        //BarGraph barGraph = (BarGraph) myController.getData(
                //selectedVisualizatoin, countriesToDisplayOnBar, yearSelectedForBar);
        // map of countries to respective values for given year. Plot this data.
        HashMap<String, Double> barValues=new HashMap<String, Double>();
        //= barGraph.getValues();
        barValues.put("Happy", 10.0);
        barValues.put("Mid", 5.0);
        barValues.put("sad", 1.0);
        
       
    languages = new String(barValues.keySet().toArray());
    value = barValues.values().toArray(value);
    title = t;
    }
    public void paint(Graphics g) {
    super.paintComponent(g);
    if (value == null || value.length == 0)
    return;
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < value.length; i++) {
    if (minValue > value[i])
    minValue = value[i];
    if (maxValue < value[i])
    maxValue = value[i];
    }
    Dimension dim = getSize();
    int clientWidth = dim.width;
    int clientHeight = dim.height;
    int barWidth = clientWidth / value.length;
    Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("Book Antiqua", Font.PLAIN, 10);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
    int titleWidth = titleFontMetrics.stringWidth(title);
    int q = titleFontMetrics.getAscent();
    int p = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, p, q);
    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue)
    return;
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    q = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    for (int j = 0; j < value.length; j++) {
    int valueP = j * barWidth + 1;
    int valueQ = top;
    int height = (int) (value[j] * scale);
    if (value[j] >= 0)
    valueQ += (int) ((maxValue - value[j]) * scale);
    else {
    valueQ += (int) (maxValue * scale);
    height = -height;
    }
    g.setColor(Color.blue);
    g.fillRect(valueP, valueQ, barWidth - 2, height);
    g.setColor(Color.black);
    g.drawRect(valueP, valueQ, barWidth - 2, height);
    int labelWidth = labelFontMetrics.stringWidth(languages[j]);
    p = j * barWidth + (barWidth - labelWidth) / 2;
    g.drawString(languages[j], p, q);
    }
    }
   public static void createAndShowBarGui() {
       
       
       
    JFrame frame = new JFrame();
    frame.setSize(1000, 800);
    
    frame.getContentPane().add(new BarGraph(
      "Mood today"));

    WindowListener winListener = new WindowAdapter() {
    public void windowClosing(WindowEvent event) {
    System.exit(0);
    }
    };
    frame.addWindowListener(winListener);
    frame.setVisible(true);
    }
   
}
