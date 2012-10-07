package view;

import controller.Controller;
import java.awt.AWTEvent;
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
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import visualizations.BarGraph;
import visualizations.LineGraph;


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
    private double[] myYears;
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
        getContentPane().add(makeDisplay(), BorderLayout.SOUTH);
        getContentPane().add(makeVisualizerChoice(), BorderLayout.NORTH);
        getContentPane().add(makeDataChoice(), BorderLayout.CENTER);
        makeMenus();
        pack();
        setVisible(true);
    }

    private JComponent makeDataChoice () {
        JPanel panel = new JPanel();
        panel.add(makeButton("BarGraph"));
        panel.add(makeButton("LineGraph"));
        panel.add(makeList(myListModel));
        return panel;
    }

    private JScrollPane makeList (DefaultListModel model) {
        JList list = new JList(model);
        list.addListSelectionListener(myListSelectionListener);
        JScrollPane port = new JScrollPane(list,
                VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        port.setPreferredSize(LIST_SIZE);
        return port;
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
                        run();
                        myListModel.clear();
                        for (double y : myYears) {
                            myListModel.addElement(y);
                        }
                    }
                    else {
                        showMessage("Load Data First!");
                    }
                }
                else if (LINE.equals(ea)) {
                    if (myDataLoaded) {
                        run();
                        myGraphType = LINE;
                        myListModel.clear();
                        for (String s : myCountries) {
                            myListModel.addElement(s);
                        }
                    }
                    else {
                        showMessage("Load Data First!");
                    }

                }
                else if ("Load".equals(ea)) {
                    myDataLoaded = true;
                    myController.loadFile();
                }
                else if ("Clear".equals(ea)) {
                    myDataLoaded = false;
                    myTextArea.setText("");
                    myListModel.clear();
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
        showMessage("Making a Bar");
        double[] year = new double[] {(Double) myListModel.get(index)};
        BarGraph bar = new BarGraph(myController);
        bar.createAndShowBarGui(year);
    }

    private JComponent makeButton (String buttonName) {
        JButton button = new JButton(myResources.getString(buttonName));
        button.addActionListener(myActionListener);
        return button;
    }

    private JComponent makeDisplay () {
        myTextArea = new JTextArea(3, FIELD_SIZE);
        return new JScrollPane(myTextArea);
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

    private void makeLine(int index) {
        showMessage("Making a line");
        String[] country = new String[] {(String) myListModel.get(index)};
        LineGraph line = new LineGraph(myController);
        line.createAndShowLineGui(country);
    }

    private JComponent makeVisualizerChoice () {
        JPanel panel = new JPanel();
        panel.add(makeButton("LoadCommand"));
        panel.add(makeButton("ClearCommand"));
        return panel;
    }

    private void makeMenus () {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        setJMenuBar(bar);
    }


    /**
     * 
     */
    public void run () {
        // list of countries
        myCountries = myController.getCountries();
        // list of years
        myYears = myController.getYears();
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
