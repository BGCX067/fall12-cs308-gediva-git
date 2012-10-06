package visualizations;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;
import controller.Controller;


public class LineGraph extends Visualization {
    
    private double[] myYears;
    private Controller myController;
    
    private int MAX_SCORE ;
    //private final int MAX_SCORE = 20;
    private static final int DEFAULT_VALUE=20;
    private static final int PREF_W = 1000;
    private static final int PREF_H = 800;
    private final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.blue;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 10;
    private ArrayList<Double> scores=new ArrayList<Double>();
    
  
    private void checkMaxScore(){
    if(getMyYears()==null) 
    {MAX_SCORE=DEFAULT_VALUE;}
    else 
        {MAX_SCORE = getMyYears().length;}
   
    }
    
    
    public LineGraph(String[] country) {
        String[] countrySelectedForLine = new String[] {"USA"}; // user selects country. hardcode for now
        double[] yearsToDisplayOnLine = myYears;
        // LineGraph object with data.
        String selectedVisualizatoin = "Line Graph";
        
        //LineGraph lineGraph = (LineGraph) myController.getData(
                //selectedVisualizatoin, countrySelectedForLine, yearsToDisplayOnLine);
        // map of years to respective values for given country. Plot this data.
        //HashMap<String, Double> lineValues = lineGraph.getValues(); 
       //this.scores=new ArrayList<Double>(lineValues.values());
        
        for(int i=1;i<=15;i++){
        scores.add((double) i);
        }
        
       checkMaxScore();
    }

    @Override
    public void paint(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D)g;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
       double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

       ArrayList<Point> graphPoints = new ArrayList<Point>();
       for (int i = 0; i < scores.size(); i++) {
          int x1 = (int) (i * xScale + BORDER_GAP);
          int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
          graphPoints.add(new Point(x1, y1));
       }

       // create x and y axes 
       g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
       g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

       // create hatch marks for y axis. 
       for (int i = 0; i < Y_HATCH_CNT; i++) {
          int x0 = BORDER_GAP;
          int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
          int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
          int y1 = y0;
          g2.drawLine(x0, y0, x1, y1);
       }

       // and for x axis
       for (int i = 0; i < scores.size() - 1; i++) {
          int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
          int x1 = x0;
          int y0 = getHeight() - BORDER_GAP;
          int y1 = y0 - GRAPH_POINT_WIDTH;
          g2.drawLine(x0, y0, x1, y1);
       }

       Stroke oldStroke = g2.getStroke();
       g2.setColor(GRAPH_COLOR);
       g2.setStroke(GRAPH_STROKE);
       for (int i = 0; i < graphPoints.size() - 1; i++) {
          int x1 = graphPoints.get(i).x;
          int y1 = graphPoints.get(i).y;
          int x2 = graphPoints.get(i + 1).x;
          int y2 = graphPoints.get(i + 1).y;
          g2.drawLine(x1, y1, x2, y2);         
       }

       g2.setStroke(oldStroke);      
       g2.setColor(GRAPH_POINT_COLOR);
       for (int i = 0; i < graphPoints.size(); i++) {
          int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
          int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
          int ovalW = GRAPH_POINT_WIDTH;
          int ovalH = GRAPH_POINT_WIDTH;
          g2.fillOval(x, y, ovalW, ovalH);
       }
    }

    @Override
    public Dimension getPreferredSize() {
       return new Dimension(PREF_W, PREF_H);
    }
    
    /*    
    public void addData (String country, double year, double value) {
        getValues().put(Double.toString(year), value);
    }
    */
    
    public  static void createAndShowGui() {
      
        
        String[] a=new String[]{"USA"};
        LineGraph l=new LineGraph(a);
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(l);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
     }

       

       
}
