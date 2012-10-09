package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;


/**
 * Opens a file and stores in an ArrayList of lines.
 * 
 * @author Howard
 * 
 */
public class FileOpener {
    private FileParser myFileParser = new FileParser();
    private static final String DELIMITER = ",";     // set based on view input

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
    public void readFile () {
        File chosenFile = chooseFile();
        try {
            FileReader reader = new FileReader(chosenFile);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(DELIMITER);
                myFileParser.parse(lineArray);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
