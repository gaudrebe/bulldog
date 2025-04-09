/**
 * A Player whose strategy is to flip a coin after each roll to decide whether
 * to roll again or hold.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
class RandomPlayer extends Player {
	private static final int COIN_SIDES = 2;
	
	private Dice coin;
	
	/**
	 * Create a new RandomPlayer with a default name.
	 */
	public RandomPlayer() {
		this("Random");
	}
	
	/**
	 * Create a new RandomPlayer with a given name.
	 * @param name the name of the RandomPlayer
	 */
	public RandomPlayer(String name) {
		super(name.isBlank() ? "Random" : name);
		coin = new Dice(COIN_SIDES);
	}
	
	/**
	 * This RandomPlayer plays a turn. After each roll, it has a 50% chance to
	 * roll again. Failing that, it holds.
	 * @return the number of points scored by the RandomPlayer this turn
	 */
	public int play() {
		int roll;
		int total = 0;
		boolean again;
		do {
			roll = rollDice();
			if (roll == 6)
				return 0;
			else {
				total += roll;
				again = (coin.roll() == COIN_SIDES);
			}
		}
		while (again);
		return total;
	}
}