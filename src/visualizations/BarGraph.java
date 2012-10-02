package visualizations;

public class BarGraph extends Visualization {
    private int myYear;
    private String[] myCountries;

    public BarGraph () {

    }

    public int getMyYear () {
        return myYear;
    }

    public void setMyYear (int myYear) {
        this.myYear = myYear;
    }

    public String getMyCategory () {
        return myCategory;
    }

    public void setMyCategory (String myCategory) {
        this.myCategory = myCategory;
    }

    public String[] getMyCountries () {
        return myCountries;
    }

    public void setMyCountries (String[] myCountries) {
        this.myCountries = myCountries;
    }

}
