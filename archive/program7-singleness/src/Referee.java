import java.util.ArrayList;

/**
 * The central class managing the flow of a game of Bulldog.
 * Only one instance of a Referee may exist at any given time.
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Apr 9 2025
 */
public class Referee {

    /** The score needed to win the game. */
    public static final int WINNING_SCORE = 104;

    private static Referee instance;
    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private boolean gameOver;

    /**
     * Constructs a referee with a list of players.
     *
     * @param playerList the list of players in the game
     * @throws IllegalArgumentException if the list is empty
     */
    private Referee(ArrayList<Player> playerList) {
        if (playerList.isEmpty())
            throw new IllegalArgumentException("Player list must not be empty.");
        this.playerList = playerList;
        this.currentPlayer = playerList.get(0);
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
                if (p.getScore() >= WINNING_SCORE) {
                    gameOver = true;
                    break;
                }
            }
        }
        System.out.printf("And the winner is... %s!\n", currentPlayer.getName());
        for (Player p : playerList)
            System.out.printf("%s: %d\n", p.getName(), p.getScore());
    }

    /**
     * Main entry point to run the game with default players.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new SevenPlayer());
        players.add(new RandomPlayer());

        Referee ref = Referee.getInstance(players);
        ref.playGame();
    }
}
