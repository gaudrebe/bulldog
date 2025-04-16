/**
 * A Player whose strategy is to keep rolling until scoring at least 7 points.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
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
	
	public SevenPlayer(String name, FakeDice rolls) {
		super(name, rolls);
	}

	/**
	 * Determines whether this Player should roll again within the context of
     * the current game state.
     * @returns true if fewer than 7 points have been scored this turn, false
     * otherwise
	 */
	@Override
	public boolean rollAgain(GameStatus status) {
		return (status.getTurnScore() < 7);
	}
}