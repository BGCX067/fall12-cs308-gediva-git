package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;


/**
 * Opens a file and stores in an ArrayList of lines.
 * 
 * @author Howard
 * 
 */
public class FileOpener {
    private FileParser myFileParser;
    private static final String DELIMITER = ",|\\t";     // set based on view input

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

    public FileParser getFileParser () {
        return myFileParser;
    }

    /**
     * Reads a file and returns an arrayList of string arrays.
     * 
     * @return
     */
    public boolean readFile () {
        myFileParser = new FileParser();
        boolean inputIsValid = true;
        File chosenFile = chooseFile();
        try {
            Scanner scanner = new Scanner(chosenFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter(DELIMITER);
                ArrayList<String> lineArray = new ArrayList<String>();
                while (lineScanner.hasNext()) {
                    lineArray.add(lineScanner.next());
                }
                lineScanner.close();
                myFileParser.parse(lineArray.toArray(new String[lineArray.size()]));
            }
            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            inputIsValid = false;
        }
        return inputIsValid;
    }
}
