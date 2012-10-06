package visualizations;

import view.View;
import java.awt.*;
import javax.swing.*;
import controller.Controller;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BarGraph extends Visualization {

    public void addData (String country, double year, double value) {
        getValues().put(country, value);
    }

    private ArrayList<Double> value=new ArrayList<Double>();
    private String[] countries;
    private String title;

    public BarGraph(String[] year) {
        String[] countriesToDisplayOnBar = getMyCountries();
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
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);


        String[] countryset={"Happy","Mid","sad","sad","sad","sad","sad"};
        countries=countryset;
        //=getMyCountries();
        for(int i=0;i<countryset.length;i++){
            value.add(barValues.get(countryset[i]));
        }

        title ="bla";
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (value == null || value.size() == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < value.size(); i++) {
            if (minValue > value.get(i))
                minValue = value.get(i);
            if (maxValue < value.get(i))
                maxValue = value.get(i);
        }
        Dimension dim = getSize();
        int clientWidth = dim.width;
        int clientHeight = dim.height;
        int barWidth = clientWidth / value.size();
        Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Book Antiqua", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
        int titleWidth = titleFontMetrics.stringWidth(title);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, p, q);
        int top = titleFontMetrics.getHeight()+10;
        int bottom = labelFontMetrics.getHeight()+10;
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        q = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        for (int j = 0; j < value.size(); j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (value.get(j) * scale);
            if (value.get(j) >= 0)
                valueQ += (int) ((maxValue - value.get(j)) * scale);
            else {
                valueQ += (int) (maxValue * scale);
                height = -height;
            }
            g.setColor(Color.blue);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(countries[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(countries[j], p, q);
        }
    }

    public static void createAndShowBarGui() {   
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        String[] a = {"Mood today"};
        frame.getContentPane().add(new BarGraph(a));

        WindowListener winListener = new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        };
        frame.addWindowListener(winListener);
        frame.setVisible(true);
    }

}
