/**
 * A Player whose strategy is to flip a coin after each roll to decide whether
 * to roll again or hold.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
 */
class RandomPlayer extends Player {
	private static final int COIN_SIDES = 2;
	
	private RandomDice coin;
	
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
		coin = new RandomDice(COIN_SIDES);
	}

	/**
	 * Determines whether this Player should roll again within the context of
     * the current game state.
     * @returns true with a 50% chance, false otherwise
	 */
	@Override
	public boolean rollAgain(GameStatus status) {
		return (coin.roll() == COIN_SIDES);
	}
}