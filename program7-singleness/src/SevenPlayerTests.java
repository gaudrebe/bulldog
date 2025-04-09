import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link SevenPlayer} class, using a {@code FakeRandom} to simulate
 * specific sequences of dice rolls.
 * 
 * @author Ben Gaudreau
 * @version Apr 9 2025
 */
class SevenPlayerTests {

	/**
	 * Tests that should score 0 points in one turn.
	 */
	@Test
	void testZero() {
		SevenPlayer test1 = new SevenPlayer("Zeros-1", new FakeRandom(6, "6"));				// on the first roll
		SevenPlayer test2 = new SevenPlayer("Zeros-2", new FakeRandom(6, "3 6"));			// on the second roll
		SevenPlayer test3 = new SevenPlayer("Zeros-3", new FakeRandom(6, "1 1 1 1 1 1 6"));	// on the last possible roll

		assertEquals(test1.play(), 0);
		assertEquals(test2.play(), 0);
		assertEquals(test3.play(), 0);
	}

	/**
	 * Tests edge cases for lowest and highest possible scores in one turn.
	 */
	@Test
	void testBounds() {
		SevenPlayer test1 = new SevenPlayer("Bounds-1", new FakeRandom(6, "1 1 1 1 1 1 1"));	// minimum score in a turn
		SevenPlayer test2 = new SevenPlayer("Bounds-2", new FakeRandom(6, "3 3 5"));			// maximum score in a turn

		assertEquals(test1.play(), 7);
		assertEquals(test2.play(), 11);
	}

	/**
	 * Tests mid-range scores that are neither minimal nor maximal.
	 */
	@Test
	void testMidrange() {
		SevenPlayer test1 = new SevenPlayer("Midrange-1", new FakeRandom(6, "2 1 3 2"));	// score of 8
		SevenPlayer test2 = new SevenPlayer("Midrange-2", new FakeRandom(6, "4 5"));		// score of 9
		SevenPlayer test3 = new SevenPlayer("Midrange-3", new FakeRandom(6, "3 2 5"));		// score of 10

		assertEquals(test1.play(), 8);
		assertEquals(test2.play(), 9);
		assertEquals(test3.play(), 10);
	}
}
