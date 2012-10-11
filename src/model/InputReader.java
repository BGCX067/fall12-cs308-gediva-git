package model;

import static resources.Constants.INPUT_DELIMITERS;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;


/**
 * Opens a file and stores in an ArrayList of lines.
 * 
 * @author Howard, Volodymyr
 * 
 */
public class InputReader {
    private InputParser myFileParser;

    /**
     * Allows user to choose a file.
     * 
     * @return
     */
    public File chooseFile () {
        File returnFile = null;
        final JFileChooser chooser =
                new JFileChooser(System.getProperties().getProperty("user.dir"));
        final int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            returnFile = chooser.getSelectedFile();
        }
        return returnFile;
    }

    public InputParser getFileParser () {
        return myFileParser;
    }

    /**
     * Reads a file and returns an arrayList of string arrays.
     * 
     * @return
     */
    public boolean readFile () {
        myFileParser = new InputParser();
        boolean inputIsValid = true;
        final File chosenFile = chooseFile();
        try {
            final Scanner scanner = new Scanner(chosenFile);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final Scanner lineScanner = new Scanner(line).useDelimiter(INPUT_DELIMITERS);
                final ArrayList<String> lineArray = new ArrayList<String>();
                while (lineScanner.hasNext()) {
                    lineArray.add(lineScanner.next());
                }
                lineScanner.close();
                myFileParser.parse(lineArray.toArray(new String[lineArray.size()]));
            }
            scanner.close();
        }
        catch (final IOException e) {
            inputIsValid = false;
        }
        catch (final NullPointerException e) {
            inputIsValid = false;
        }
        return inputIsValid;
    }
}
