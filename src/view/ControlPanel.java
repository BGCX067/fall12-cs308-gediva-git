package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Factory;
import static resources.Constants.*;


/**
 * Acts as the View, interfaces with the user.
 * 
 * @author Sam Rang, Volodymyr
 * 
 */
@SuppressWarnings("serial")
public class ControlPanel extends JFrame implements ScrollPaneConstants {
    private JTextArea myTextArea;
    private DefaultListModel myListModel;
    private JList mySelectionList;
    private ActionListener myActionListener;
    private boolean myDataIsLoaded;
    private final Controller myController;
    private String myChartType;
    private ListSelectionListener myListSelectionListener;

    /**
     * Used to determine interaction with user and
     * passes that onto the controller to determine
     * what the model does.
     */
    public ControlPanel () {
        myController = new Controller();
        myDataIsLoaded = false;
        createListeners(this);
        setTitle(CONTROL_PANEL_TITLE);
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
     * 
     * @param add Item to be added
     */
    public void addToList (String add) {
        myListModel.addElement(add);
    }

    private void addMessageDisplay () {
        myTextArea = new JTextArea(MESSAGE_DISPLAY_SIZE.width, MESSAGE_DISPLAY_SIZE.height);
        getContentPane().add(new JScrollPane(myTextArea), BorderLayout.SOUTH);
    }

    private void addFileControlButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton(LOAD_BUTTON_TEXT));
        panel.add(makeButton(CLEAR_BUTTON_TEXT));
        getContentPane().add(panel, BorderLayout.NORTH);
    }

    private void addVisualizationButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton(BAR_VIS_TITLE));
        panel.add(makeButton(LINE_VIS_TITLE));
        panel.add(makeList(myListModel));
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    /**
     * @param cp for the static methods determining bar/line
     *        behavior the control panel (this) needs to be passed.
     */
    private void createListeners (final ControlPanel cp) {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String ea = e.getActionCommand();
                if (LOAD_BUTTON_TEXT.equals(ea)) {
                    load();
                }
                else if (CLEAR_BUTTON_TEXT.equals(ea)) {
                    clear();
                }
                else if (BAR_VIS_TITLE.equals(ea) && myDataIsLoaded) {
                    Factory.VISUALIZATIONS.get(BAR_VIS_TITLE).listen(ea, cp, myController);
                    myChartType = BAR_VIS_TITLE;
                }
                else if (LINE_VIS_TITLE.equals(ea) && myDataIsLoaded) {
                    Factory.VISUALIZATIONS.get(LINE_VIS_TITLE).listen(ea, cp, myController);
                    myChartType = LINE_VIS_TITLE;
                }
            }
        };
        // listener for JList events
        myListSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && mySelectionList.getSelectedIndex() >= 0) {
                    String item =
                            (String) myListModel.get(mySelectionList.getSelectedIndex());
                    myController.setVisualization(myChartType, item);
                    showMessage(CHART_CREATION_MESSAGE + myChartType);
                }
            }
        };
    }

    private JScrollPane makeList (DefaultListModel model) {
        mySelectionList = new JList(model);
        mySelectionList.addListSelectionListener(myListSelectionListener);
        JScrollPane port =
                new JScrollPane(mySelectionList, VERTICAL_SCROLLBAR_AS_NEEDED,
                                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        port.setPreferredSize(SELECTION_LIST_SIZE);
        return port;
    }

    private JComponent makeButton (String buttonName) {
        JButton button = new JButton(buttonName);
        button.addActionListener(myActionListener);
        return button;
    }

    private void addMenu () {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        setJMenuBar(bar);
    }

    private JMenu makeFileMenu () {
        JMenu fileMenu = new JMenu(FILE_MENU_TEXT);
        fileMenu.add(new AbstractAction(LOAD_BUTTON_TEXT) {
            @Override
            public void actionPerformed (ActionEvent e) {
                load();
            }
        });
        fileMenu.add(new AbstractAction(CLEAR_BUTTON_TEXT) {
            @Override
            public void actionPerformed (ActionEvent e) {
                clear();
            }
        });
        return fileMenu;
    }

    /**
     * Prints the error on the screen.
     * 
     * @param message Error message
     */
    public void showError (String message) {
        JOptionPane.showMessageDialog(this, message, ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Prints a general message on the GUI
     * 
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
        myDataIsLoaded = false;
        myTextArea.setText("");
        myListModel.clear();
        showMessage(DATA_CLEARED_MESSAGE);
    }

    /**
     * Loads data into the model.
     */
    public void load () {
        if (myController.loadFile()) {
            myDataIsLoaded = true;
            showMessage(DATA_LOADED_MESSAGE);
        }
        else {
            showError(INVALID_INPUT_MESSAGE);
        }
    }

}
