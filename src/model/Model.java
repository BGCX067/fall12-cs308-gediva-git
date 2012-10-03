package model;

import java.util.ArrayList;


public class Model {
    private FileOpener myFileOpener;
    private String[] myCountryList;
    private static final int START_POSITION = 2;

    public Model () {
        myFileOpener = new FileOpener();
    }

    public void loadFile () {
        ArrayList<String[]> lines = myFileOpener.readFile();
        ArrayList<String> countryArray = new ArrayList<String>();
        for (int i = START_POSITION; i < lines.size(); i++) {
            // System.out.println(lines.get(i)[0]);
            countryArray.add(lines.get(i)[0]);
        }
        myCountryList = new String[countryArray.size()];
        countryArray.toArray(myCountryList);
    }

    public int locateCountry (String country) {
        for (int i = 0; i < myCountryList.length; i++) {
            if (country.equals(myCountryList[i])) { return i; }
        }
        return -1;
    }

    public int locateYear (int year) {
        for (int i = 0; i < myCountryList[0].length; i++) {
            if (year == myCountryList[i]) { return i; }
        }
        return -1;
    }

    public int getDataPoint (String country, int year) {

    }
}
