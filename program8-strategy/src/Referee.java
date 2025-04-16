import java.util.ArrayList;

/**
 * The central class managing the flow of a game of Bulldog.
 * Only one instance of a Referee may exist at any given time.
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Apr 15 2025
 */
public class Referee {

    private static Referee instance;
    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private boolean gameOver;

    /**
     * Constructs a referee with a list of players.
     *
     * @param players the list of players in the game
     * @throws IllegalArgumentException if the list is empty
     */
    private Referee(ArrayList<Player> players) {
        if (players.isEmpty())
            throw new IllegalArgumentException("Player list must not be empty.");
        this.playerList = players;
        GameStatus.getInstance(this, playerList);
        this.currentPlayer = players.get(0);
        this.gameOver = false;
    }

    /**
     * Returns the singleton instance of the referee.
     *
     * @param playerList the list of players to initialize the game
     * @return the single {@code Referee} instance
     */
    public static Referee getInstance(ArrayList<Player> playerList) {
        if (instance == null) {
            instance = new Referee(playerList);
        }
        return instance;
    }

    /**
     * Plays the game by allowing each player to take turns until someone wins.
     * Announces the winner and final scores.
     */
    public void playGame() {
        while (!gameOver) {
            for (Player p : playerList) {
                currentPlayer = p;
                int turnScore = p.play();
                p.setScore(p.getScore() + turnScore);
                if (p.getScore() >= GameStatus.getWinningScore()) {
                    gameOver = true;
                    break;
                }
            }
            System.out.printf("\n");
            System.out.printf("Scores:\n");
            for (Player p : playerList) {
            	System.out.printf("%s: %d\n", p.getName(), p.getScore());
            }
            System.out.printf("\n");
        }
        System.out.printf("And the winner is... %s!\n", currentPlayer.getName());
        for (Player p : playerList)
            System.out.printf("%s: %d\n", p.getName(), p.getScore());
    }
    
    /**
     * Returns an array containing each player's score.
     * @return an array containing each Player's score
     */
    public int[] getScores() {
    	int[] scores = new int[playerList.size()];
    	for (int i = 0; i < scores.length; i++) {
    		scores[i] = playerList.get(i).getScore();
    	}
    	return scores;
    }

    /**
     * Main entry point to run the game with default players.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new SevenPlayer());
        players.add(new HumanPlayer());

        Referee ref = Referee.getInstance(players);
        ref.playGame();
    }
}
