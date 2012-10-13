package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import resources.Constants;


/**
 * Opens a file and stores in an ArrayList of lines.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class InputReader {
    private InputParser myInputParser;

    /**
     * Creates a new input reader.
     */
    public InputReader () {
        myInputParser = new InputParser();
    }
    /**
     * Allows user to choose a file.
     * 
     * @return
     */
    public File chooseFile () {
        File returnFile = null;
        JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
        int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            returnFile = chooser.getSelectedFile();
        }
        return returnFile;
    }
/**
 * Gets the file parser.
 * @return
 */
    public InputParser getInputParser () {
        return myInputParser;
    }

    /**
     * Reads a file and returns an arrayList of string arrays.
     * 
     * @return
     */
    public boolean readFile () {
        myInputParser.clear();
        boolean inputIsValid = true;
        File chosenFile = chooseFile();
        try {
            Scanner scanner = new Scanner(chosenFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(Constants.INPUT_DELIMITERS);
                ArrayList<String> lineArray = new ArrayList<String>();
                while (lineScanner.hasNext()) {
                    lineArray.add(lineScanner.next());
                }
                lineScanner.close();
                myInputParser.parse(lineArray.toArray(new String[lineArray.size()]));
            }
            scanner.close();
        }
        catch (IOException e) {
            inputIsValid = false;
        }
        catch (NullPointerException e) {
            inputIsValid = false;
        }
        return inputIsValid;
    }

    /**
     * Restores parser data from backup in case of failed input
     * @param backupParser backup to restore from
     */
    public void setParser (InputParser backupParser) {
        myInputParser = backupParser;
    }
}
