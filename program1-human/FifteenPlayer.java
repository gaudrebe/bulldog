/*
 * Ben Gaudreau
 * COS 420
 * FifteenPlayer: extends Player class
 * 				  A FifteenPlayer rolls until scoring at least 15 points
 */

public class FifteenPlayer extends Player {
	
	public FifteenPlayer() {
		this("Fifteen");
	}
	
	public FifteenPlayer(String name) {
		super(name);
	}
	
	@Override
	public int play() {
		int roll;
		int total = 0;
		do {
			roll = (int) (Math.random()*6 + 1);
			System.out.print("   Player " + getName() + " rolled " + roll );
			if (roll == 6) {
				break;
			}
			else {
				total += roll;
				System.out.println(", for a cumulative total of "
						+ total + " this turn.");
			}
		}
		while (total < 15);
		if (roll != 6) {
			System.out.println("   Player " + getName() + " chose not to continue, scoring "
					+ total + " for the turn.");
		}
		else {
			total = 0;
			System.out.println(" and scored 0 for the turn.");
		}
		return total;
	}

}
