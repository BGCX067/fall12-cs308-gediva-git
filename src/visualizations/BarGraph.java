package visualizations;


import java.awt.*;
import javax.swing.*;
import controller.Controller;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class BarGraph extends Visualization {
    private Controller myController;
    private String[] myCountries;

    public void addData (String country, double year, double value) {
        getValues().put(country, value);
    }

    private ArrayList<Double> value=new ArrayList<Double>();
    private String[] countries;
    private String title;

    public BarGraph() {
        myCountries=getMyCountries(); 
        HashMap<String, Double> barValues= getValues();
        
        /*test case
        =new HashMap<String, Double>();
        barValues.put("Happy", 10.0);
        barValues.put("Mid", 5.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        barValues.put("sad", 1.0);
        */
        
        
        //String[] countryset={"Happy","Mid","sad","sad","sad","sad","sad"};
        //countries=countryset;
        //=getMyCountries();
        for(int i=0;i<myCountries.length;i++){
            value.add(barValues.get(myCountries[i]));
        }
    
        title = "Bar Graph";
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
    
   public  void createAndShowBarGui(BarGraph b,double[] oneyear) { 
       
       myController.getData("Bar Graph", myController.getCountries(), oneyear);
    JFrame frame = new JFrame();
    frame.setSize(1000, 800);
    frame.getContentPane().add(b);
    frame.setVisible(true);
    }
   
}
