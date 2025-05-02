import javax.swing.SwingUtilities;

/**
 * Main program for Bulldog. Invokes BulldogWindow to handle GUI and logic.
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
public class Bulldog {
	public static final int WINNING_SCORE = 104;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(BulldogWindow::new);
	}

}
