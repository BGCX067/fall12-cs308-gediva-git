import view.View;


/**
 * Runs the program.
 * 
 * @author Howard
 * 
 */
public final class Main {
    private Main () {
        // doesn't do anything
    }

    /**
     * Runs the program.
     * 
     * @param args program arguments
     */
    public static void main (String[] args) {
        String layout = (args.length > 0) ? args[0] : "default";
        new View(layout);
    }
}
