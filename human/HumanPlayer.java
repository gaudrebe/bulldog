/* 
 * Ben Gaudreau
 * COS 420
 * HumanPlayer: extends Player class
 *              A HumanPlayer lets the user decide when to stop rolling
 */

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		this("Human");
	}
	
	public HumanPlayer(String name) {
		super(name);
	}
	
	@Override
	public int play() {
		Scanner s = new Scanner(System.in);
		int roll;
		int total = 0;
		boolean again = true;
		do {
			roll = (int) (Math.random()*6 + 1);
			System.out.print("   Player " + getName() + " rolled " + roll );
			if (roll == 6) {
				break;
			}
			else {
				total += roll;
				System.out.print(", for a cumulative total of "
						+ total + " this turn. Roll again?(y/n) ");
				boolean validInput = false;
				while (!validInput) {
					String userInput = s.nextLine();
					if (!(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N"))) {
						System.out.print("Invalid input. Please try again.(y/n) ");
					}
					else {
						validInput = true;
						if (userInput.equalsIgnoreCase("N")) {
							again = false;
						}
					}
				}
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
