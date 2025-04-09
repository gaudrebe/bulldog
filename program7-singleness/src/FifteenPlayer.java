/**
 * A Player whose strategy is to keep rolling until scoring at least 15 points.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
class FifteenPlayer extends Player {
	
	/**
	 * Create a new FifteenPlayer with a default name.
	 */
	public FifteenPlayer() {
		this("Fifteen");
	}
	
	/**
	 * Create a new FifteenPlayer with a given name.
	 * @param name the name of the FifteenPlayer
	 */
	public FifteenPlayer(String name) {
		super(name.isBlank() ? "Fifteen" : name);
	}
	
	/**
	 * This FifteenPlayer plays a turn. After each roll, it will hold if its
	 * total score for the turn is at least 15. Otherwise, it rolls again.
	 * @return the number of points scored by the FifteenPlayer this turn
	 */
	public int play() {
		int roll;
		int total = 0;
		do {
			roll = rollDice();
			if (roll == 6)
				return 0;
			else
				total += roll;
		}
		while (total < 15);
		return total;
	}
}