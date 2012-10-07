package view;

import controller.Controller;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.*;
import visualizations.*;


/**
 * Displays different visualizations on a GUI.
 * 
 * @author Sam Rang and Xi Du 
 * With help from Robert Duvall's EventDemo.java code
 * 
 */
@SuppressWarnings("serial")
public class View extends JFrame implements ScrollPaneConstants {
    private static final String LINE = "Line Graph";
    private static final String BAR = "Bar Graph";
    private static final int FIELD_SIZE = 30;
    private static final Dimension LIST_SIZE = new Dimension(75, 150);
    private ActionListener myActionListener;
    private ListSelectionListener myListSelectionListener;
    private ResourceBundle myResources;
    private JTextArea myTextArea;
    private DefaultListModel myListModel;
    private JList myJList;
    private Controller myController;
    private String[] myCountries;
    private String[] myYears;
    private String myGraphType;
    private boolean myDataLoaded;

    /**
     * Sets up the View, determining the title, prompts the user
     * for a file, defines the layout etc.
     * 
     * @param layout Specifies the labels based on a resource file.
     */
    public View(String layout) {
        myDataLoaded = false;
        setTitle("Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myResources = ResourceBundle.getBundle("resources." + layout);
        createListeners();
        myController = new Controller();
        myListModel = new DefaultListModel();
        addMessageDisplay();
        addFileControlButtons();
        addVisualizationButtons();
        addMenu();
        pack();
        setVisible(true);
    }

    /**
     * Creates many subclasses
     */
    private void createListeners () {
        // listener for "high-level" events, i.e., those made
        // up of a sequence of low-level events, like a button
        // press (mouse down and up within a button object)
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String ea = e.getActionCommand();
                if (BAR.equals(ea)) {
                    if (myDataLoaded) {
                        myGraphType = BAR;
                        myYears = myController.getAllYears();
                        myListModel.clear();
                        for (String y : myYears) {
                            myListModel.addElement(y);
                        }
                        showMessage("Click on year to display.");
                    }
                    else {
                        showMessage("Load Data First!");
                    }
                }
                else if (LINE.equals(ea)) {
                    if (myDataLoaded) {
                        myCountries = myController.getAllCountries();
                        myGraphType = LINE;
                        myListModel.clear();
                        for (String s : myCountries) {
                            myListModel.addElement(s);
                        }
                        showMessage("Click on country to display.");
                    }
                    else {
                        showMessage("Load Data First!");
                    }
                }
                else if ("Load".equals(ea)) {
                    myDataLoaded = true;
                    myController.loadFile();
                    showMessage("Data loaded. Select visualization.");
                }
                else if ("Clear".equals(ea)) {
                    myDataLoaded = false;
                    myTextArea.setText("");
                    myListModel.clear();
                    showMessage("Data cleared.");
                }
            }
        };
        //listener for JList events
        myListSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if(LINE.equals(myGraphType)){
                        makeLine(e.getLastIndex());
                    }
                    else if (BAR.equals(myGraphType)) {
                        makeBar(e.getLastIndex());
                    }
                }
            }
        };
    }

    private void makeBar(int index) {
        showMessage("Making a bar");
        String year = (String) myListModel.get(index);
        BarGraph bar = (BarGraph) myController.createVisualization(BAR, year);
        bar.visualize();
    }

    private void makeLine(int index) {
        showMessage("Making a line");
        String country = (String) myListModel.get(index);
        LineGraph line = (LineGraph) myController.createVisualization(LINE, country);
        line.visualize();
    }

    private void addMessageDisplay () {
        myTextArea = new JTextArea(3, FIELD_SIZE);
        getContentPane().add(new JScrollPane(myTextArea), BorderLayout.SOUTH);
    }
    
    private void addFileControlButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton("LoadCommand"));
        panel.add(makeButton("ClearCommand"));
        getContentPane().add(panel, BorderLayout.NORTH);
    }
    
    private void addVisualizationButtons () {
        JPanel panel = new JPanel();
        panel.add(makeButton("BarGraph"));
        panel.add(makeButton("LineGraph"));
        panel.add(makeList(myListModel));
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    private JScrollPane makeList (DefaultListModel model) {
        JList list = new JList(model);
        list.addListSelectionListener(myListSelectionListener);
        JScrollPane port = new JScrollPane(list,
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
        fileMenu.add(new AbstractAction(myResources.getString("LoadCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                myDataLoaded = true;
                myController.loadFile();
            }
        });
        fileMenu.add(new AbstractAction(myResources.getString("ClearCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                myTextArea.setText("");
                myListModel.clear();
            }
        });
        return fileMenu;
    }

    private void showError (String message) {
        JOptionPane.showMessageDialog(
                this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
    }
}
