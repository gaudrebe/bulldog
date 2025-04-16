/**
 * A Player whose strategy is to keep rolling until scoring at least 15 points.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
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

	/**
	 * Determines whether this Player should roll again within the context of
     * the current game state.
     * @returns true if fewer than 15 points have been scored this turn, false
     * otherwise
	 */
	@Override
	public boolean rollAgain(GameStatus status) {
		return (status.getTurnScore() < 15);
	}
}