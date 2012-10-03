import model.Model;
import view.View;


public class Main {

    public static void main (String[] args) {
        String layout = (args.length > 0) ? args[0] : "default";
        View view = new View(layout);
    }
}
