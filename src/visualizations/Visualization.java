package visualizations;

public abstract class Visualization {
    private String myCategory;
    
    public String getMyCategory () {
        return myCategory;
    }

    public void setMyCategory (String myCategory) {
        this.myCategory = myCategory;
    }
}
