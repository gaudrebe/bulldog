/**
 * The ScoreViewer class observes and displays scores from a PlayerModel.
 * 
 * @author Ben Gaudreau (with assistance from ChatGPT 4)
 * @version Mar 24 2025
 */
public class BulldogViewer {
    /**
     * Updates the viewer with the latest scores from the PlayerModel.
     * 
     * @param model the PlayerModel whose scores have been updated
     */
    public void update(BulldogModel m) {
        System.out.println("Scores have been updated!");
        displayScores(m);
    }

    /**
     * Displays the current scores of all players.
     * 
     * @param m the PlayerModel to display scores from
     */
    public void displayScores(BulldogModel m) {
        System.out.println("Current Scores:");
        for (int i = 0; i < m.getListSize(); i++) {
            System.out.println(m.getPlayerName(i) + ": " + m.getPlayerScore(i));
        }
    }
}
