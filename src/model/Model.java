package model;

import java.util.ArrayList;


public class Model {
    private FileOpener myFileOpener;

    public Model () {
        myFileOpener = new FileOpener();
    }

    public void loadFile () {
        ArrayList<String> lines = myFileOpener.readFile();
        
    }
}
