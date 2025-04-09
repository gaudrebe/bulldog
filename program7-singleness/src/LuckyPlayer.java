/**
 * A Player whose strategy is to roll until hitting a "lucky" score. A lucky
 * score is any multiple of seven.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
class LuckyPlayer extends Player {
	
	/**
	 * Create a new FifteenPlayer with a default name.
	 */
	public LuckyPlayer() {
		this("Lucky");
	}
	
	/**
	 * Create a new LuckyPlayer with a given name.
	 * @param name the name of the LuckyPlayer
	 */
	public LuckyPlayer(String name) {
		super(name.isBlank() ? "Lucky" : name);
	}
	
	/**
	 * This LuckyPlayer plays a turn. After each roll, it will hold if its
	 * total score for the turn is a multiple of seven. Otherwise, it rolls
	 * again.
	 * @return the number of points scored by the LuckyPlayer this turn
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
		while (total % 7 != 0);
		return total;
	}
}