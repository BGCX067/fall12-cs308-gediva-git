package resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Stroke;
import java.util.ResourceBundle;


public class Constants {
private static ResourceBundle myResources = ResourceBundle.getBundle("resources.default");
// Used throughout
public static final String LINE_VIS_TITLE = myResources.getString("LineChart");
public static final String BAR_VIS_TITLE = myResources.getString("BarChart");
// Input Reader
public static final String INPUT_DELIMITERS = ",|\\t";  
// Control Panel
public static final Dimension SELECTION_LIST_SIZE = new Dimension(75, 150);
public static final Dimension MESSAGE_DISPLAY_SIZE = new Dimension(6, 30);
// Visualizations
public static final int CHART_FONT_SIZE1 = 15;
public static final int CHART_FONT_SIZE2 = 10;
public static final int GAP_BETWEEN_CHART_AND_FRAME = 20;
public static final int Y_AXIS_NUBMER_OF_MARKS = 10;
public static final Font CHART_TITLE_FONT = new Font("Book Antiqua", Font.BOLD, 15);
public static final Font CHART_LABEL_FONT = new Font("Book Antiqua", Font.PLAIN, 10);
// Line
public static final Color LINE_COLOR = Color.blue;
public static final Color LINE_POINT_COLOR = new Color(150, 50, 50, 180);
public static final Stroke LINE_STROKE = new BasicStroke(1f);
public static final int LINE_POINT_DIAMETER = 8;
// Bar
public static final Color BAR_FILL_COLOR = Color.blue;
public static final Color BAR_BORDER_COLOR = Color.black;

}
