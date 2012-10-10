package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.Constants;
import controller.Controller;
import visualizations.BarGraph;
import visualizations.LineGraph;


/**
 * Acts as the View, interfaces with the user.
 * @author Sam Rang
 * 
 */
@SuppressWarnings("serial")
public class ControlPanel extends JFrame implements ScrollPaneConstants {
    private static final String CLEAR_COMMAND = "ClearCommand";
    private static final String LOAD_COMMAND = "LoadCommand";
    private static final String LINE = Constants.LINE_GRAPH;
    private static final String BAR = Constants.BAR_GRAPH;
    private static final String[] DELIMITER_LIST = {"Tab", "Space", "Comma"};
    private static final Dimension LIST_SIZE = new Dimension(75, 150);
    private static final Dimension FIELD_SIZE = new Dimension(3, 30);
    private ResourceBundle myResources;
    private JTextArea myTextArea;
    private DefaultListModel myListModel;
    private JList myJList;
    private ActionListener myActionListener;
    private boolean myDataLoaded;
    private Controller myController;
    private String myGraphType;
    private ListSelectionListener myListSelectionListener;

    /**
     * Used to determine interaction with user and
     * passes that onto the controller to determine
     * what the model does.
     * @param layout Preferred resource document
     */
    public ControlPanel (String layout) {
        myController = new Controller();
        myResources = ResourceBundle.getBundle("resources." + layout);
        myDataLoaded = false;
        createListeners(this);
        setTitle("Control Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myListModel = new DefaultListModel();
        addMessageDisplay();
        addFileControlButtons();
        addVisualizationButtons();
        addMenu();
        pack();
        setVisible(true);
    }

    /**
     * Add an item to the list of options for graphing
     * @param add Item to be added
     */
    public void addToList(String add) {
        myListModel.addElement(add);
    }

    private void addDelimiterList () {
    }

    private void addMessageDisplay () {
        myTextArea = new JTextArea(FIELD_SIZE.height, FIELD_SIZE.width);
        getContentPane().add(new JScrollPane(myTextArea), BorderLayout.SOUTH);
    }

    private void addFileControlButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton(LOAD_COMMAND));
        panel.add(makeButton(CLEAR_COMMAND));
        getContentPane().add(panel, BorderLayout.NORTH);
    }

    private void addVisualizationButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton("BarGraph"));
        panel.add(makeButton("LineGraph"));
        panel.add(makeList(myListModel));
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    /** 
     * @param cp for the static methods determining bar/line
     * behavior the control panel (this) needs to be passed.
     */
    private void createListeners (final ControlPanel cp) {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String ea = e.getActionCommand();
                if ("Load".equals(ea)) {
                    load();
                }
                else if ("Clear".equals(ea)) {
                    clear();
                }
                else if (BAR.equals(ea) && myDataLoaded) {
                    BarGraph.listen(ea, cp, myController);
                    myGraphType = BAR;
                }
                else if (LINE.equals(ea) && myDataLoaded) {
                    LineGraph.listen(ea, cp, myController);
                    myGraphType = LINE;
                }
            }
        };
        //listener for JList events
        myListSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && myJList.getSelectedIndex() >= 0) {
                    String item = (String) myListModel.get(myJList.getSelectedIndex());
                    myController.setVisualization(myGraphType, item);
                    showMessage("Making a " + myGraphType);
                }
            }
        };
    }

    private JScrollPane makeList (DefaultListModel model) {
        myJList = new JList(model);
        myJList.addListSelectionListener(myListSelectionListener);
        JScrollPane port = new JScrollPane(myJList,
                VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        port.setPreferredSize(LIST_SIZE);
        return port;
    }

    private JComponent makeButton (String buttonName) {
        JButton button = new JButton(myResources.getString(buttonName));
        button.addActionListener(myActionListener);
        return button;
    }

    private void addMenu () {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        setJMenuBar(bar);
    }

    private JMenu makeFileMenu () {
        JMenu fileMenu = new JMenu(myResources.getString("FileMenu"));
        fileMenu.add(new AbstractAction(myResources.getString(LOAD_COMMAND)) {
            @Override
            public void actionPerformed (ActionEvent e) {
                load();
            }
        });
        fileMenu.add(new AbstractAction(myResources.getString(CLEAR_COMMAND)) {
            @Override
            public void actionPerformed (ActionEvent e) {
                clear();
            }
        });
        return fileMenu;
    }

    /**
     * Prints the error on the screen.
     * @param message Error message
     */
    public void showError (String message) {
        JOptionPane.showMessageDialog(
                this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Prints a general message on the GUI
     * @param message Printed message
     */
    public void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
    }

    /**
     * Clears the list of graph options with out
     * deleting the data.
     */
    public void clearList () {
        myListModel.clear();
    }

    /**
     * Resets the board.
     */
    public void clear () {
        myDataLoaded = false;
        myTextArea.setText("");
        myListModel.clear();
        showMessage("Data cleared.");
    }

    /**
     * Loads data into the model.
     */
    public void load () {
        myDataLoaded = true;
        myController.loadFile();
        showMessage("Data loaded. Select visualization.");
    }

}
