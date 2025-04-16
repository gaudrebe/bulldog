import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A mock dice that returns predetermined values from an input stream instead of generating random ones.
 * Useful for testing purposes.
 * 
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Apr 16 2025
 */
public class FakeDice extends Dice {

	InputStream stream;
	Scanner scan;

	/**
	 * Constructs a {@code FakeRandom} using a string of space-separated integers.
	 *
	 * @param n the number of sides on the dice
	 * @param s the string of space-separated roll values
	 */
	public FakeDice(int n, String s) {
		super(n);
		this.stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
		this.scan = new Scanner(stream);
	}

	/**
	 * Constructs a {@code FakeRandom} using a {@link FileInputStream} containing space-separated integers.
	 *
	 * @param n the number of sides on the dice
	 * @param f the input stream containing roll values
	 */
	public FakeDice(int n, FileInputStream f){
		super(n);
		this.stream = f;
		this.scan = new Scanner(stream);
	}

	/**
	 * Returns the next integer from the input stream. If the integer is out of bounds,
	 * it is corrected using modulo and a warning is printed.
	 *
	 * @return the roll value
	 */
	@Override
	public int roll() {
		int result = scan.nextInt();
		if (result > sides || result < 1) {
			int raw = result;
			result = (raw % sides) + 1;
			System.err.printf("WARNING: obtained value %d is greater than number of sides (%d). Returning %d.\n", raw, sides, result);
		}
		return result;
	}
}
