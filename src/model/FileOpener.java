package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class FileOpener {

    public File chooseFile () {
        JFileChooser chooser = new JFileChooser();
        return chooser.getSelectedFile();
    }

    public ArrayList<String> readFile () {
        File chosenFile = chooseFile();
        ArrayList<String> lines = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(chosenFile);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
