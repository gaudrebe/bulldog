/*
 * Ben Gaudreau
 * COS 420
 * RandomPlayer: extends Player class
 * 				 A RandomPlayer has a 50% chance to stop after each roll
 */

public class RandomPlayer extends Player {
	
	public RandomPlayer() {
		this("Random");
	}
	
	public RandomPlayer(String name) {
		super(name);
	}
	
	@Override
	public int play() {
		int roll;
		int total = 0;
		boolean again;
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
				again = (Math.random() > 0.5 ? true : false);
			}
		}
		while (again == true);
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
