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
    /**
     * @param CHART_WIDTH the width of the chart
     */
    public static int CHART_WIDTH = 600;
    /**
     * @param CHART_HEIGHT the height of the chart
     */
    public static final int CHART_HEIGHT = 400;
    /**
     * @param CHART_FONT_SIZE1 the font size used in LineGraph
     */
    public static final int CHART_FONT_SIZE1 = 15;
    /**
     * @param CHART_FONT_SIZE2 the font size used in LineGraph
     */
    public static final int CHART_FONT_SIZE2 = 10;
    /**
     * @param GAP_BETWEEN_CHART_AND_FRAME the gap used in the chart
     */
    public static final int GAP_BETWEEN_CHART_AND_FRAME = 20;
    /**
     * @param Y_AXIS_NUBMER_OF_MARKS
     */
    public static final int Y_AXIS_NUBMER_OF_MARKS = 10;
    /**
     * @param CHART_TITLE_FONT title font size
     */
    public static final Font CHART_TITLE_FONT =
            new Font("Book Antiqua", Font.BOLD, 15);
    /**
     * @param CHART_LABEL_FONT label font size
     */
    public static final Font CHART_LABEL_FONT =
            new Font("Book Antiqua", Font.PLAIN, 10);
    // Line
    /**
     * @param LINE_COLOR line color
     */
    public static final Color LINE_COLOR = Color.blue;
    /**
     * @param LINE_POINT_COLOR point color
     */
    public static final Color LINE_POINT_COLOR = Color.red;
    /**
     * @param LINE_STROKE
     */
    public static final Stroke LINE_STROKE = new BasicStroke(1f);
    /**
     * @param LINE_POINT_DIAMETER
     */
    public static final int LINE_POINT_DIAMETER = 8;
    /**
     * @param LINE_BUTTON_ONCLICK_MESSAGE message when clicking
     */
    public static final String LINE_BUTTON_ONCLICK_MESSAGE
    = myResources.getString("LineButtonOnClickMessage");

    // Bar
    /**
     * @param BAR_FILL_COLOR color used in bar graph
     */
    public static final Color BAR_FILL_COLOR = Color.blue;
    /**
     * @param BAR_BORDER_COLOR border color used in bar graph
     */
    public static final Color BAR_BORDER_COLOR = Color.black;
    /**
     * @param BAR_BUTTON_ONCLICK_MESSAGE message when clicking
     */
    public static final String BAR_BUTTON_ONCLICK_MESSAGE
    = myResources.getString("BarButtonOnClickMessage");

}
