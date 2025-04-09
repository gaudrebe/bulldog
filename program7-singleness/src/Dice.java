import java.util.Random;

/**
 * Dice simulates a single die with any number of sides greater than 1.
 * It utilizes a single random number generator across all instances of
 * Dice objects in order to consolidate randomness.
 * @author Ben Gaudreau
 * @version Apr 9 2025
 */
public class Dice extends RandomDice {
    private static final Random random = new Random();

    /**
     * Creates a new Dice with N sides.
     * @param n the number of sides on this Dice
     */
    public Dice(int n) {
        super(n);
    }
    
    /**
     * Roll this Dice, returning the result.
     * @return a random integer between 1 and the number of sides on this Dice
     */
    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}