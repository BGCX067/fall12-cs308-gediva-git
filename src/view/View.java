package view;

import controller.Controller;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
public class View extends JFrame {
    private static final int FIELD_SIZE = 30;
    private ActionListener myActionListener;
    private ResourceBundle myResources;
    private JTextArea myTextArea;
    private JFileChooser myChooser;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;
    private Controller myController;
    private String[] myCountries;
    private double[] myYears;

    /**
     * Sets up the View, determining the title, prompts the user
     * for a file, defines the layout etc.
     * 
     * @param layout Specifies the labels based on a resource file.
     */
    public View(String layout) {
        setTitle("Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myChooser = new JFileChooser(System.getProperties().getProperty(
                "user.dir"));
        myResources = ResourceBundle.getBundle("resources." + layout);
        createListeners();
        getContentPane().add(makeInput(), BorderLayout.NORTH);
        getContentPane().add(makeDisplay(), BorderLayout.CENTER);
        getContentPane().add(makeVisualizerChoice(), BorderLayout.EAST);
        makeMenus();
        myController = new Controller();
        pack();
        setVisible(true);
    }

    private void createListeners () {
        // listener for "high-level" events, i.e., those made
        // up of a sequence of low-level events, like a button
        // press (mouse down and up within a button object)
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String ea = e.getActionCommand();
                if ("Bar Graph".equals(ea)) {
                    run();
                    makeBar();
                }
                else if ("Line Graph".equals(ea)) {
                    run();
                    makeLine();
                }
                else if("Load".equals(ea)) {
                    myController.loadFile();
                }
                echo("action", e);
            }
        };
        // listener for low-level keyboard events
        myKeyListener = new KeyListener() {
            @Override
            public void keyPressed (KeyEvent e) {
                echo("pressed", e);
            }
            @Override
            public void keyReleased (KeyEvent e) {
                echo("released", e);
            }
            @Override
            public void keyTyped (KeyEvent e) {
                echo("typed", e);
            }
        };
        // listener for low-level mouse events
        myMouseListener = new MouseListener() {
            @Override
            public void mouseClicked (MouseEvent e) {
                echo("clicked", e);
            }
            @Override
            public void mouseEntered (MouseEvent e) {
            }
            @Override
            public void mouseExited (MouseEvent e) {
            }
            @Override
            public void mousePressed (MouseEvent e) {
            }
            @Override
            public void mouseReleased (MouseEvent e) {
            }
        };
        // listener for low-level mouse movement events
        myMouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged (MouseEvent e) {
            }
            @Override
            public void mouseMoved (MouseEvent e) {
            }
        };
        // listener for low-level focus events, i.e., the mouse
        // entering/leaving a component so you can type in it
        myFocusListener = new FocusListener() {
            @Override
            public void focusGained (FocusEvent e) {
                echo("gained", e);
            }
            @Override
            public void focusLost (FocusEvent e) {
                echo("lost", e);
            }
        };
    }

    /**
     * Echo key presses by showing important attributes
     */
    private void echo (String s, KeyEvent e) {
        showMessage(s + " char:" + e.getKeyChar() + " mod: " +
                KeyEvent.getKeyModifiersText(e.getModifiers()) + " mod: " +
                KeyEvent.getKeyText(e.getKeyCode()));
    }

    /**
     * Echo action events including time event occurs
     */
    private void echo (String s, ActionEvent e) {
        showMessage(s + " = " + e.getActionCommand() + " " + e.getWhen());
    }

    /**
     * Echo mouse events (enter, leave, etc., including position and buttons)
     */
    private void echo (String s, MouseEvent e) {
        showMessage(s + " x = " + e.getX() + " y = " + e.getY() + " mod: " +
                MouseEvent.getMouseModifiersText(e.getModifiers()) + " button: " +
                e.getButton() + " clicks " + e.getClickCount());
    }

    /**
     * Echo other events (e.g., Focus)
     */
    private void echo (String s, AWTEvent e) {
        showMessage(s + " " + e);
    }

    /**
     * Echo data read from reader to display
     */
    private void echo (Reader r) {
        try {
            String s = "";
            BufferedReader input = new BufferedReader(r);
            while (true) {
                String line = input.readLine();
                if (line != null) {
                    s += line + "\n";
                }
                else {
                    break;
                }
            }
            showMessage(s);
        }
        catch (IOException e) {
            showError(e.toString());
        }
    }

    /**
     * Echo display to writer
     */
    private void echo (Writer w) {
        PrintWriter output = new PrintWriter(w);
        output.println(myTextArea.getText());
        output.flush();
        output.close();
    }

    private void makeBar() {
        showMessage("made a new bar");
        // for now visualize bar for all countries and 1 year 
        // (i.e. user can't select countries). Can extend later if needed
        String[] countriesToDisplayOnBar = myCountries;
        double[] yearSelectedForBar = new double[] {2006}; // user selects year. hardcode for now
        // BarGraph object with data.
        String selectedVisualizatoin = "Bar Graph";
        BarGraph barGraph = (BarGraph) myController.getData(
                selectedVisualizatoin, countriesToDisplayOnBar, yearSelectedForBar);
        // map of countries to respective values for given year. Plot this data.
        HashMap<String, Double> barValues = barGraph.getValues();
        showMessage("Build bar visualization for " + yearSelectedForBar[0] + ": " + barValues);
    }

    private JComponent makeButton (String buttonName) {
        JButton button = new JButton(myResources.getString(buttonName));
        button.addKeyListener(myKeyListener);
        button.addMouseListener(myMouseListener);
        button.addActionListener(myActionListener);
        return button;
    }

    private JComponent makeClear () {
        JButton result = new JButton(myResources.getString("ClearCommand"));
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myTextArea.setText("");
            }
        });
        return result;
    }

    private JComponent makeDisplay () {
        myTextArea = new JTextArea(FIELD_SIZE, FIELD_SIZE);
        myTextArea.addMouseListener(myMouseListener);
        myTextArea.addMouseMotionListener(myMouseMotionListener);
        return new JScrollPane(myTextArea);
    }

    private JMenu makeFileMenu () {
        JMenu fileMenu = new JMenu(myResources.getString("FileMenu"));
        fileMenu.add(new AbstractAction(myResources.getString("OpenCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                myController.loadFile();
            }
        });
        fileMenu.add(new AbstractAction(myResources.getString("SaveCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    echo(new FileWriter("demo.out"));
                }
                catch (IOException io) {
                    showError(io.toString());
                }
            }
        });
        fileMenu.add(new AbstractAction(myResources.getString("LoadCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                showMessage("well that was fun");
            }
        });
        return fileMenu;
    }

    private JComponent makeInput () {
        JPanel panel = new JPanel();
        panel.add(makeTextField());
        panel.add(makeButton("LoadCommand"));
        panel.add(makeButton("ActionCommand"));
        panel.add(makeClear());
        return panel;
    }

    private void makeLine() {
        showMessage("Make a line");
        // for now visualize line for all years and 1 country 
        // (i.e. user can't select years). Can extend later if needed
        String[] countrySelectedForLine = new String[] {"USA"}; // user selects country. hardcode for now
        double[] yearsToDisplayOnLine = myYears;
        // LineGraph object with data.
        String selectedVisualizatoin = "Line Graph";
        LineGraph lineGraph = (LineGraph) myController.getData(
                selectedVisualizatoin, countrySelectedForLine, yearsToDisplayOnLine);
        // map of years to respective values for given country. Plot this data.
        HashMap<String, Double> lineValues = lineGraph.getValues();
        showMessage("Build line visuazation for " + countrySelectedForLine[0] + ": " + lineValues);
    }

    private JComponent makeTextField () {
        JTextField text = new JTextField(FIELD_SIZE);
        text.addKeyListener(myKeyListener);
        text.addFocusListener(myFocusListener);
        text.addActionListener(myActionListener);
        return text;
    }


    private JComponent makeVisualizerChoice () {
        JPanel panel = new JPanel();
        panel.add(makeButton("BarGraph"));
        panel.add(makeButton("LineGraph"));
        return panel;
    }

    private void makeMenus () {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        setJMenuBar(bar);
    }


    /**
     * main behavior (probably).
     */
    public void run () {
        // list of countries
        myCountries = myController.getCountries();
        // list of years
        myYears = myController.getYears();
        // do work. 
        // use selectedVisualization, myAllCountries, myAllYears to build plot area
        // for bar, use barValues and yearSelectedForBar[0] (plot label)
        // for line, use lineValues and countrySelectedForLine[0] (plot label)
        showMessage("All countries: " + Arrays.toString(myCountries));
        showMessage("All years: " + Arrays.toString(myYears));
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
