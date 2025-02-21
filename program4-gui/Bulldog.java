// AUTHOR'S NOTE: DOUBLE SLASHES ON THE FAR RIGHT INDICATE HUMAN-EDITED LINES ------------------------------------>	//

/**
 * Bulldog Dice Game Implementation
 * Author: ChatGPT
 * Date: 2025-01-30
 * 
 * This program simulates the Bulldog dice game, where players take turns rolling a six-sided die.
 * Different player types have different rolling strategies.
 */

import java.util.*;

/**
 * Abstract class representing a player in the Bulldog game.
 */
abstract class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Abstract method defining a player's turn.
     * @return the points earned in the turn.
     */
    public abstract int play();
}

/**
 * Human-controlled player. The player chooses whether to continue rolling.
 */
class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer() {
        super("Human");
    }

    public HumanPlayer(String name, Scanner scanner) {
        super(name.isEmpty() ? "Human" : name);
        this.scanner = scanner;
    }

    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.print(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(" and scored 0 points this turn.");
                return 0;
            }
            turnScore += roll;
            System.out.println(", turn score: " + turnScore);
            System.out.print("Continue rolling? (y/n): ");
            if (!scanner.next().equalsIgnoreCase("y")) {
                System.out.println(getName() + " stopped rolling and scored " + turnScore + " points this turn.");	//
                return turnScore;
            }
        }
    }
}

/**
 * A player who decides to roll randomly.
 */
class RandomPlayer extends Player {
    public RandomPlayer() {
        super("Random");
    }

    public RandomPlayer(String name) {
        super(name.isEmpty() ? "Random" : name);
    }

    public int play() {
        int turnScore = 0;
        Random random = new Random();
        do {
            int roll = random.nextInt(6) + 1;
            System.out.print(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(" and scored 0 points this turn.");
                return 0;
            }
            turnScore += roll;
            System.out.println(", turn score: " + turnScore);
        } while (random.nextBoolean());
        System.out.println(getName() + " stopped rolling and scored " + turnScore + " points this turn.");			//
        return turnScore;
    }
}

/**
 * A player who always rolls until reaching 15 points.
 */
class FifteenPlayer extends Player {
    public FifteenPlayer() {
        super("Fifteen");
    }

    public FifteenPlayer(String name) {
        super(name.isEmpty() ? "Fifteen" : name);
    }

    public int play() {
        int turnScore = 0;
        while (turnScore < 15) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.print(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(" and scored 0 points this turn.");
                return 0;
            }
            turnScore += roll;
            System.out.println(", turn score: " + turnScore);
        }
        System.out.println(getName() + " stopped rolling and scored " + turnScore + " points this turn.");			//
        return turnScore;
    }
}

/**
 * A unique player with a quirky rolling strategy.
 */
class UniquePlayer extends Player {
    public UniquePlayer() {
        super("Unique");
    }

    public UniquePlayer(String name) {
        super(name.isEmpty() ? "Unique" : name);
    }

    public int play() {
        int turnScore = 0;
        int rolls = 0;
        Random random = new Random();
        boolean isFeelingLucky = random.nextBoolean();
        
        while (rolls < 2 || (isFeelingLucky && turnScore % 7 != 0)) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.print(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(" and scored 0 points this turn.");
                return 0;
            }
            turnScore += roll;
            rolls++;
            System.out.println(", turn score: " + turnScore);
            isFeelingLucky = random.nextBoolean();
        }
        System.out.println(getName() + " stopped rolling and scored " + turnScore + " points this turn.");			//
        return turnScore;
    }
}

/**
 * Ben's UniquePlayer from Program 1.
 * It rolls 5 times each turn.
 */
class BenUniquePlayer extends Player {																				//
																													//
	public BenUniquePlayer() {																						//
		super("Fiver");																							//
	}																												//
																													//
	public BenUniquePlayer(String name) {																			//
		super(name.isEmpty() ? "Fiver" : name);																	//
	}																												//
																													//
	@Override																										//
	public int play() {																								//
		int turnScore = 0;																							//
		for (int i=0; i < 5; i++) {																					//
			int roll = (int) (Math.random() * 6 + 1);																//
            System.out.print(getName() + " rolled " + roll);														//
            if (roll == 6) {																						//
                System.out.println(" and scored 0 points this turn.");												//
                return 0;																							//
            }																										//
            turnScore += roll;																						//
            System.out.println(", turn score: " + turnScore);														//
		}																											//
		System.out.println(getName() + " stopped rolling and scored " + turnScore + " points this turn.");			//
		return turnScore;
	}

}

/**
 * A player who always rolls exactly once.
 */
class WimpPlayer extends Player {
    public WimpPlayer() {
        super("Wimp");
    }

    public WimpPlayer(String name) {
        super(name.isEmpty() ? "Wimp" : name);
    }

    public int play() {
        int roll = (int) (Math.random() * 6 + 1);
        System.out.print(getName() + " rolled " + roll);
        if (roll == 6) {
            System.out.println(" and scored 0 points this turn.");
            return 0;
        }
        System.out.println(", turn score: " + roll);
        System.out.println(getName() + " stopped rolling and scored " + roll + " points this turn.");				//
        return roll;
    }
}

public class Bulldog {																								//
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        int numPlayers;
        
        do {
            System.out.print("Enter number of players (at least 2): ");
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        } while (numPlayers < 2);

        for (int i = 0; i < numPlayers; i++) {
            char type;
            
            while (true) {
                System.out.print("Choose player type (H/R/F/U/W/B): ");
                type = scanner.next().charAt(0);
                scanner.nextLine();
                
                if (type == 'H' || type == 'R' || type == 'F' || type == 'U' || type == 'W' || type == 'B') {	//
                    break;
                }
                System.out.println("Invalid choice. Please select a valid player type.");
            }
            
            System.out.print("Enter player name (leave blank for default): ");
            String name = scanner.nextLine();
            
            switch (type) {
                case 'H': players.add(new HumanPlayer(name, scanner)); break;
                case 'R': players.add(new RandomPlayer(name)); break;
                case 'F': players.add(new FifteenPlayer(name)); break;
                case 'U': players.add(new UniquePlayer(name)); break;
                case 'W': players.add(new WimpPlayer(name)); break;
                case 'B': players.add(new BenUniquePlayer(name)); break;											//
            }
        }
        
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn!");
                int turnScore = player.play();
                player.setScore(player.getScore() + turnScore);
                System.out.println("Scores after this turn:");
                for (Player p : players) {
                    System.out.println(p.getName() + ": " + p.getScore());
                }
                if (player.getScore() >= 104) {
                    System.out.println(player.getName() + " wins!");
                    gameWon = true;
                    break;
                }
            }
        }
        scanner.close();
    }
}
