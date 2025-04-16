/**
 * A Player whose strategy is to roll until hitting a "lucky" score. A lucky
 * score is any multiple of seven.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
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
	 * Determines whether this Player should roll again within the context of
     * the current game state.
     * @returns true if the current turn score is not a multiple of 7, false
     * otherwise
	 */
	@Override
	public boolean rollAgain(GameStatus status) {
		return ((status.getTurnScore() % 7) != 0);
	}
}