/**
 * A Player whose strategy is to keep rolling until scoring at least 7 points.
 * 
 * @author Ben Gaudreau
 * @version Apr 8 2025
 */
class SevenPlayer extends Player {
	
	/**
	 * Create a new SevenPlayer with a default name.
	 */
	public SevenPlayer() {
		this("Seven");
	}
	
	/**
	 * Create a new SevenPlayer with a given name.
	 * @param name the name of the SevenPlayer
	 */
	public SevenPlayer(String name) {
		super(name.isBlank() ? "Seven" : name);
	}
	
	public SevenPlayer(String name, FakeRandom rolls) {
		super(name, rolls);
	}
	
	/**
	 * This SevenPlayer plays a turn. After each roll, it will hold if its
	 * total score for the turn is at least 7. Otherwise, it rolls again.
	 * @return the number of points scored by the SevenPlayer this turn
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
		while (total < 7);
		return total;
	}
}