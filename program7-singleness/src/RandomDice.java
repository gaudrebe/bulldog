/**
 * Abstract class representing a dice with a specified number of sides.
 * Subclasses must implement the {@code roll()} method to define how the dice rolls.
 * 
 * @author Ben Gaudreau
 * @version Apr 9 2025
 */
public abstract class RandomDice {
	
    /** Number of sides on the dice. */
	protected int sides;

	/**
	 * Constructs a dice with the given number of sides.
	 * 
	 * @param n the number of sides; must be at least 2
	 * @throws IllegalArgumentException if {@code n} is less than 2
	 */
	public RandomDice(int n) {
		if (n < 2) {
            throw new IllegalArgumentException("Dice must have at least 2 sides");
        }
		this.sides = n;
	}

	/**
	 * Rolls the dice.
	 * 
	 * @return an integer result from the roll
	 */
	public abstract int roll();
}
