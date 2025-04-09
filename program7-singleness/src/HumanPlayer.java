import javax.swing.JOptionPane;

/**
 * A Player whose strategy is dependent on user input.
 * 
 * @author Ben Gaudreau
 * @version Mar 2 2025
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
	 * This HumanPlayer plays a turn. After each roll, the user decides
	 * whether to roll again or hold.
	 */
	public int play() {
		int roll;
		int total = 0;
		boolean again;
		do {
			roll = rollDice();
			if (roll == 6) {
				JOptionPane.showMessageDialog(null, "You rolled a 6! No points scored this turn...");
				return 0;
			}
			else {
				total += roll;
				again = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, String.format("You rolled a %d. Roll again?", roll), "", JOptionPane.YES_NO_OPTION));
			}
		}
		while (again);
		return total;
	}	
}