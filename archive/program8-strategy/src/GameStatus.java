import java.util.ArrayList;

/**
 * Encapsulates information about the current game state so that Player
 * subclasses may utilize more complex strategies.
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Apr 15 2025
 */
public class GameStatus {

	private static GameStatus instance;

	private Referee ref;
	private static final int WINNING_SCORE = 104;
	private int[] playerScores;
	private int turnRolls, turnScore, rollValue;
	
	/**
	 * Constructs a new GameStatus object with a Referee to run the game, and
	 * Players to play it.
	 * @param r the Referee instance running the game
	 * @param players an ArrayList of Players
	 */
	private GameStatus(Referee r, ArrayList<Player> players) {
		this.ref = r;
		this.playerScores = ref.getScores();
		this.turnRolls = 0;
		this.turnScore = 0;
	}
	
	/**
	 * Returns the Singleton instance of the game state without initialization.
	 * This method should only be called after initializing the object.
	 * @return the Singleton GameStatus instance, if it already exists
	 * @throws NullPointerException if the instance has not been initialized
	 * @see #getInstance(Referee r, ArrayList players)
	 */
	public static GameStatus getInstance() {
		if (instance != null) {
			return instance;
		}
		else throw new NullPointerException("GameStatus not yet set");
	}
	
	/**
	 * Returns the Singleton instance of the game state.
	 * This method will instantiate a new GameStatus object if it does not yet
	 * exist, given a Referee instance and an ArrayList of Players.
	 * @param r the Referee instance running the game
	 * @param players an ArrayList of Players
	 * @return the Singleton GameStatus instance
	 */
	public static GameStatus getInstance(Referee r, ArrayList<Player> players) {
		if (instance == null) {
			instance = new GameStatus(r, players);
		}
		return instance;
	}
	
	/**
	 * Returns the number of points needed to win in Bulldog.
	 * @return the winning score
	 */
	public static int getWinningScore() {
		return WINNING_SCORE;
	}

	/**
	 * Returns the scores of every Player as an integer array.
	 * @return an array of each Player's score.
	 */
	public int[] getPlayerScores() {
		return playerScores;
	}

	/**
	 * Returns the number of times the current Player has rolled the dice this
	 * turn.
	 * @return the number of die rolls this turn
	 */
	public int getTurnRolls() {
		return turnRolls;
	}

	/**
	 * Returns the score achieved by the current Player thus far.
	 * @return the current score for this turn
	 */
	public int getTurnScore() {
		return turnScore;
	}
	
	/**
	 * Returns the result of the most recent die roll.
	 * @return the die value
	 */
	public int getRollValue() {
		return rollValue;
	}

	/**
	 * Updates the current state of the game within the context of a new turn.
	 */
	public void updateTurn() {
		playerScores = ref.getScores();
		turnRolls = 0;
		turnScore = 0;
		rollValue = 0;
	}
	
	/**
	 * Updates the current game state within the context of a single roll.
	 * @param roll the value of the die that was rolled
	 */
	public void updateRoll(int roll) {
		turnRolls++;
		rollValue = roll;
		if (roll != 6) {
			turnScore += roll;
		}
		else {
			turnScore = 0;
		}
	}
}
