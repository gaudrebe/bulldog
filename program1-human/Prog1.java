/* 
git  * Ben Gaudreau
 * COS 420
 * Prog1: main method to run Bulldog
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Prog1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Starting a new game of Bulldog!\n");
		
		// Initialize variables relating to the number of players.
		int numPlayers = 0;
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Enter the number of players: ");
			numPlayers = s.nextInt();
			if (numPlayers >= 2) {
				validInput = true;
			}
			else {
				System.out.print("Invalid input. ");
			}
		}
		
		// Create an empty ArrayList and populate it with Players based on user input.
		// The user is also allowed to give a name to each Player if they wish.
		ArrayList<Player> players = new ArrayList<Player>(numPlayers);
		for (int i=0; i < numPlayers; i++) {
			int playerType = 0;
			String playerName;
			System.out.printf(" Player %d: Select player type (1-5)\n", i+1);
			System.out.print("  1 - Human\n"
						   + "  2 - Wimp\n"
						   + "  3 - Random\n"
						   + "  4 - Fifteen\n"
						   + "  5 - Unique\n");
			validInput = false;
			while (!validInput) {
				playerType = s.nextInt();
				if (playerType <= 5 && playerType >= 1) {
					validInput = true;
				}
				else {
					System.out.print("Invalid input. Enter player type (1-5) ");
				}
			}
			System.out.printf("Player %d: Enter name (leave blank for a default name) ", i+1);
			s.nextLine();				// skip newline character from previous entry
			playerName = s.nextLine();
			switch (playerType) {
			case 1:
				if (playerName.isBlank())
					players.add(new HumanPlayer());
				else
					players.add(new HumanPlayer(playerName));
				break;
			case 2:
				if (playerName.isBlank())
					players.add(new WimpPlayer());
				else
					players.add(new WimpPlayer(playerName));
				break;
			case 3:
				if (playerName.isBlank())
					players.add(new RandomPlayer());
				else
					players.add(new RandomPlayer(playerName));
				break;
			case 4:
				if (playerName.isBlank())
					players.add(new FifteenPlayer());
				else
					players.add(new FifteenPlayer(playerName));
				break;
			case 5:
				if (playerName.isBlank())
					players.add(new UniquePlayer());
				else
					players.add(new UniquePlayer(playerName));
				break;
			}
		}
		System.out.println();
		
		// Initialize the game state.
		int roundCount = 1;
		boolean gameActive = true;
		Player winner = null;
		while (gameActive) {
			// Each round, each Players calls their play() method once, adding the result to their score.
			System.out.printf(" ROUND %d\n", roundCount);
			for (int i=0; i < numPlayers; i++) {
				Player currentPlayer = players.get(i);
				int currentScore = currentPlayer.getScore();
				int roundScore = currentPlayer.play();
				int totalScore = currentScore + roundScore;
				currentPlayer.setScore(totalScore);
				if (totalScore >= 104) {
					// The game ends the moment any Player has a score of at least 104.
					gameActive = false;
					winner = currentPlayer;
					System.out.println();
					break;
				}
				System.out.println();
				
			}
			// At the end of each round (and the game), each Player's score is displayed.
			for (Player player : players) {
				System.out.printf("  Player %s score: %d\n", player.getName(), player.getScore());
			}
			System.out.println();
			roundCount++;
		}
		System.out.printf(" PLAYER %s WINS!", winner.getName());
		s.close();
	}

}
