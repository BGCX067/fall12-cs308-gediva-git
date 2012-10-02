package model;

import java.util.ArrayList;


public class Model {
    private FileOpener myFileOpener;
    private String[] myCountryList;

    public Model () {
        myFileOpener = new FileOpener();
    }

    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        ArrayList<String> readerArray=new ArrayList<String>();
        for (int i=0;i<lines.size();i++){
            System.out.println(lines.get(i)[0]);
            readerArray.add(lines.get(i)[0]);
        }
    }
}
