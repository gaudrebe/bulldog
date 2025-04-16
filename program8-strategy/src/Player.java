/**
 * Template for a player of Bulldog. All subclasses of Player define their own
 * strategies for playing.
 * 
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Apr 15 2025
 */
public abstract class Player {
	private static final int SIX_SIDED_DIE = 6;
	
    private String name;
    private int score;
    private Dice die;
    
    /**
     * Create a new Player object.
     * @param name the name of the Player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.die = new RandomDice(SIX_SIDED_DIE);
    }
    
    /**
     * Create a new Player object with a predetermined die for testing.
     * @param name the name of the Player
     * @param rolls a FakeRandom object to be used as the Player's die
     */
    public Player(String name, FakeDice rolls) {
    	this.name = name;
    	this.score = 0;
    	this.die = rolls;
    }
    
    /**
     * Return the Player's name.
     * @return the name of the Player
     */
    public String getName() {
        return name;
    }

    /**
     * Return the Player's score.
     * @return the score of the Player
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Change the Player's current score.
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * Roll the Player's die and print the result to the console.
     * @return the result of the die roll
     */
    public int rollDice() {
    	int roll = this.die.roll();
//    	System.out.printf("%s rolled a %d.\n", name, roll);
    	return roll;
    }

    /**
     * This Player plays a turn. The strategy is determined by the Player's 
     * implementation of the {@code rollAgain()} method.
     * @return the number of points scored by the Player on this turn
     */
    public int play() {
    	GameStatus status = GameStatus.getInstance();
    	int turnScore = 0;
    	do {
    		int roll = die.roll();
    		System.out.printf("Player %s rolled a %d.\n", name, roll);
    		status.updateRoll(roll);
    		if (roll == 6) {
    			turnScore = 0;
    			break;
    		}
    		turnScore += roll;
    	}
    	while (rollAgain(status));
    	System.out.printf("Player %s scored %d points this turn.\n", name, status.getTurnScore());
    	status.updateTurn();
    	return turnScore;
    }
    
    /**
     * Determines whether this Player should roll again within the context of
     * the current game state.
     * @param status relevant information regarding the game and the current
     * turn.
     * @return true if this Player should roll again, false otherwise.
     */
    public abstract boolean rollAgain(GameStatus status);
}