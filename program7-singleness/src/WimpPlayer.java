/**
 * A Player whose strategy is to always roll the die once and take the result,
 * no matter what was rolled.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
class WimpPlayer extends Player {
	
	/**
	 * Create a new WimpPlayer with a default name.
	 */
	public WimpPlayer() {
        this("Wimp");
    }
	
	/**
	 * Create a new WimpPlayer with a given name.
	 * @param name the name of the WimpPlayer
	 */
    public WimpPlayer(String name) {
    	super(name.isBlank() ? "Wimp" : name);
    }
    
    /**
     * This WimpPlayer plays a turn. It rolls the die exactly once.
     * @return the number of points scored by the WimpPlayer this turn
     */
    public int play() {
		int roll = rollDice();
		if (roll == 6)
			return 0;
		else
			return roll;
	}
}