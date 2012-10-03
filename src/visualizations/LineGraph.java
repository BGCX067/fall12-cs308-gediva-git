package visualizations;

public class LineGraph extends Visualization {
    private int[] myYears;
    private String myCountry;

    public LineGraph () {

    }

    public String getMyCountry () {
        return myCountry;
    }

    public void setMyCountry (String myCountry) {
        this.myCountry = myCountry;
    }

    public int[] getMyYears () {
        return myYears;
    }

    public void setMyYears (int[] myYears) {
        this.myYears = myYears;
    }

}
