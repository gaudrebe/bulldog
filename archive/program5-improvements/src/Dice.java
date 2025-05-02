import java.util.Random;

/**
 * Dice simulates a single die with any number of sides greater than 1.
 * It utilizes a single random number generator across all instances of
 * Dice objects in order to consolidate randomness.
 * @author Ben Gaudreau
 * @version Mar 1 2025
 */
public class Dice {
    private static final Random random = new Random();
    private final int sides;

    /**
     * Creates a new Dice with N sides.
     * @param n the number of sides on this Dice
     * @throws IllegalArgumentException if n is less than 2
     */
    public Dice(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("Dice must have at least 2 sides");
        }
        this.sides = n;
    }
    
    /**
     * Roll this Dice, returning the result.
     * @return a random integer between 1 and the number of sides on this Dice
     */
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}