package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class FileOpener {
    private static final String[] DELIMITERS = new String[] {",","\\t"};
    //private static final String DELIMITER = ",";

    public File chooseFile () {
        JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
        int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    public ArrayList<String[]> readFile () {
        File chosenFile = chooseFile();
        ArrayList<String[]> lines = new ArrayList<String[]>();
        try {
            FileReader reader = new FileReader(chosenFile);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                for(int i = 0; i<DELIMITERS.length; i++) {
                    String[] lineArray = line.split(DELIMITERS[i]);
                    if (lineArray.length > 1) {
                        lines.add(lineArray);
                        break;
                    }
                }                
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
