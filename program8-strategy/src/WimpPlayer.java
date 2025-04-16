/**
 * A Player whose strategy is to always roll the die once and take the result,
 * no matter what was rolled.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
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
     * Determines whether this Player should roll again within the context of
     * the current game state.
     * @returns false, always
     */
	@Override
	public boolean rollAgain(GameStatus status) {
		return false;
	}
}