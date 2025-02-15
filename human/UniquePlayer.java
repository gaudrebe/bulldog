/*
 * Ben Gaudreau
 * COS 420
 * UniquePlayer: extends Player class
 * 				 My UniquePlayer always rolls the dice 5 times
 */

public class UniquePlayer extends Player {
	
	public UniquePlayer() {
		this("Unique");
	}
	
	public UniquePlayer(String name) {
		super(name);
	}
	
	@Override
	public int play() {
		int roll = 0;
		int total = 0;
		for (int i=0; i < 5; i++) {
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
