import javax.swing.JOptionPane;

/**
 * A Player whose strategy is dependent on user input.
 * 
 * @author Ben Gaudreau
 * @version Apr 15 2025
 */
class HumanPlayer extends Player {
    
    /**
     * Create a new HumanPlayer with a default name.
     */
    public HumanPlayer() {
		this("Human");
	}
	
    /**
     * Create a new HumanPlayer with a given name.
     * @param name the name of the HumanPlayer
     */
	public HumanPlayer(String name) {
		super(name.isBlank() ? "Human" : name);
    }
	
	/**
	 * Determines whether this Player should roll again within the context of
     * the current game state.
	 * @return true if the user selects the "Yes" prompt, false otherwise
	 */
	@Override
	public boolean rollAgain(GameStatus status) {
		return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
																		String.format("You rolled a %d, for a score of %d after %d roll(s). Roll again?",
																					   status.getRollValue(), status.getTurnScore(), status.getTurnRolls()),
																		"",
																		JOptionPane.YES_NO_OPTION));
	}	
}