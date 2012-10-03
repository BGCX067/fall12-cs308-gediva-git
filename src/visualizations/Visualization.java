package visualizations;

public abstract class Visualization {
    private String myCategory;
    private double[] myYear;
    private String[] myCountries;
    
    public String getMyCategory () {
        return myCategory;
    }

    public void setMyCategory (String myCategory) {
        this.myCategory = myCategory;
    }

    public double[] getMyYear () {
        return myYear;
    }

    public void setMyYear (double[] myYear) {
        this.myYear = myYear;
    }

    public String[] getMyCountries () {
        return myCountries;
    }

    public void setMyCountries (String[] myCountries) {
        this.myCountries = myCountries;
    }
}
