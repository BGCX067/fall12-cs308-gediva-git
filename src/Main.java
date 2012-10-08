import view.View;

/**
 * Runs the program.
 * @author Howard
 *
 */
public class Main {
/**
 * Runs the program.
 * @param args program arguments
 */
    public static void main (String[] args) {
        String layout = (args.length > 0) ? args[0] : "default";
        View view = new View(layout);
    }
}
