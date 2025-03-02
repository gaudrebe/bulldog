/**
 * Template for a player of Bulldog. All subclasses of Player define their own
 * strategies for playing.
 * @author Ben Gaudreau
 * @version Mar 1 2025
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
        this.die = new Dice(SIX_SIDED_DIE);
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
    	System.out.printf("%s rolled a %d.\n", name, roll);
    	return roll;
    }

    /**
     * This Player plays a turn. The strategy is determined by the Player's type.
     * @return the number of points scored by the Player on this turn
     */
    public abstract int play();
}