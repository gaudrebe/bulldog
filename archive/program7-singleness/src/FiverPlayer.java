/**
 * A Player whose strategy is to roll the die 5 times each turn.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
class FiverPlayer extends Player {
	
	/**
	 * Create a new FiverPlayer with a default name.
	 */
	public FiverPlayer() {
		this("Fiver");
	}
	
	/**
	 * Create a new FiverPlayer with a given name.
	 * @param name the name of this FiverPlayer
	 */
	public FiverPlayer(String name) {
		super(name.isBlank() ? "Fiver" : name);
	}
	
	/**
	 * This FiverPlayer plays a turn. It rolls the die five times.
	 * @return the number of points scored by the FiverPlayer this turn
	 */
	public int play() {
		int roll;
		int rollCount = 0;
		int total = 0;
		
		do {
			roll = rollDice();
			rollCount++;
			if (roll == 6)
				return 0;
			else 
				total += roll;
		}
		while (rollCount < 5);
		return total;
	}

}