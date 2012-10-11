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
    public static final String LINE_VIS_TITLE = myResources.getString("LineChartTitle");
    public static final String BAR_VIS_TITLE = myResources.getString("BarChartTitle");
    // Input Reader
    public static final String INPUT_DELIMITERS = ",|\\t";
    // Input Parser
    public static final int DEFAULT_INPUT_VALUE = 0;
    // Control Panel
    public static final String CONTROL_PANEL_TITLE = myResources.getString("ControlPanelTitle");
    public static final String DATA_LOADED_MESSAGE = myResources.getString("DataLoadedMessage");
    public static final String INVALID_INPUT_MESSAGE = myResources.getString("InvalidInputMessage");
    public static final String DATA_CLEARED_MESSAGE = myResources.getString("DataClearedMessage");
    public static final String CHART_CREATION_MESSAGE = myResources.getString("ChartCreationMessage");
    public static final String ERROR_DIALOG_TITLE = myResources.getString("ErrorDialogTitle");
    public static final String FILE_MENU_TEXT = myResources.getString("FileMenuText");
    public static final String LOAD_BUTTON_TEXT = myResources.getString("LoadButtonText");
    public static final String CLEAR_BUTTON_TEXT = myResources.getString("ClearButtonText");
    public static final Dimension SELECTION_LIST_SIZE = new Dimension(75, 150);
    public static final Dimension MESSAGE_DISPLAY_SIZE = new Dimension(6, 30);
    // Visualizations
    public static final int CHART_WIDTH = 600;
    public static final int CHART_HEIGHT = 400;
    public static final int CHART_FONT_SIZE1 = 15;
    public static final int CHART_FONT_SIZE2 = 10;
    public static final int GAP_BETWEEN_CHART_AND_FRAME = 20;
    public static final int Y_AXIS_NUBMER_OF_MARKS = 10;
    public static final Font CHART_TITLE_FONT = new Font("Book Antiqua", Font.BOLD, 15);
    public static final Font CHART_LABEL_FONT = new Font("Book Antiqua", Font.PLAIN, 10);
    // Line
    public static final Color LINE_COLOR = Color.blue;
    public static final Color LINE_POINT_COLOR = Color.red;
    public static final Stroke LINE_STROKE = new BasicStroke(1f);
    public static final int LINE_POINT_DIAMETER = 8;
    public static final String LINE_BUTTON_ONCLICK_MESSAGE = myResources.getString("LineButtonOnClickMessage");
    // Bar
    public static final Color BAR_FILL_COLOR = Color.blue;
    public static final Color BAR_BORDER_COLOR = Color.black;
    public static final String BAR_BUTTON_ONCLICK_MESSAGE = myResources.getString("BarButtonOnClickMessage");

}
