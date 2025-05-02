import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Provides the GUI and logic for running a game of Bulldog. 
 * @author Ben Gaudreau
 * @version Mar 2 2025
 */
public class BulldogWindow {

	private JFrame frame;
    private JPanel gamePanel, playerPanel, infoPanel, buttonPanel;
    private JButton nextTurnButton;
    private JLabel currentPlayerLabel, turnScoreLabel;
    private JTextArea rollHistoryArea;
    private DefaultListModel<String> playersListModel;
    private JList<String> playersList;
    
    private int numPlayers;
    private List<Player> players;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private int turnScore;
	
    public BulldogWindow() {
    	this.numPlayers = 0;
    	this.players = initializePlayers();
    	this.currentPlayerIndex = 0;
    	this.currentPlayer = players.get(currentPlayerIndex);
    	this.turnScore = 0;
    	
    	initializeGameWindow();
    }
    
    /**
     * Initializes the players by prompting the user for player details.
     * @return an ArrayList of initialized Players
     */
    private ArrayList<Player> initializePlayers() {
        while (true) {
            numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players:"));
            if (numPlayers >= 2)
                break;
        }
        ArrayList<Player> a = new ArrayList<>(numPlayers);
        
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(String.format("Enter name for Player %d:", i + 1));
            String[] types = {"Human", "CPU - Wimp", "CPU - Random", "CPU - Fifteen", "CPU - Fiver", "CPU - Lucky"};
            String type = (String) JOptionPane.showInputDialog(null, "Select player type:", "Player Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            
            Player p;
            switch (type) {
                case "Human":
                    p = new HumanPlayer(name);
                    break;
                case "CPU - Wimp":
                    p = new WimpPlayer(name);
                    break;
                case "CPU - Random":
                    p = new RandomPlayer(name);
                    break;
                case "CPU - Fifteen":
                    p = new FifteenPlayer(name);
                    break;
                case "CPU - Fiver":
                    p = new FiverPlayer(name);
                    break;
                case "CPU - Lucky":
                    p = new LuckyPlayer(name);
                    break;
                default:
                    p = null;
                    break;
            }
            a.add(p);
        }
        return a;
    }
    
    /**
     * Initializes the game window and its components.
     */
    private void initializeGameWindow() {
        frame = new JFrame("Bulldog - Now with GUI!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        
        playersListModel = new DefaultListModel<>();
        playersList = new JList<>(playersListModel);
        updatePlayerList();
        playerPanel = new JPanel(new BorderLayout());
        playerPanel.add(new JLabel("Players:"), BorderLayout.NORTH);
        playerPanel.add(new JScrollPane(playersList), BorderLayout.CENTER);
        
        frame.add(playerPanel, BorderLayout.WEST);
        
        currentPlayerLabel = new JLabel("Current Player: -");
        turnScoreLabel = new JLabel("Turn Score: -");
        rollHistoryArea = new JTextArea(16, 0);
        rollHistoryArea.setEditable(false);
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(currentPlayerLabel, BorderLayout.NORTH);
        infoPanel.add(turnScoreLabel, BorderLayout.CENTER);
        infoPanel.add(rollHistoryArea, BorderLayout.SOUTH);
        
        nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(e -> nextTurn());
        buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(nextTurnButton);
        
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(infoPanel, BorderLayout.NORTH);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        playTurn();
    }
    
    ///////////////
    // GAME LOOP //
    ///////////////
    
    /**
     * Plays the current player's turn.
     */
    private void playTurn() {
        updatePlayerList();
        updateGameScreen();
        turnScore = currentPlayer.play();
        updateGameScreen();
        rollHistoryArea.append(String.format("%s scored %d this turn.\n", currentPlayer.getName(), turnScore));
    }
    
    /**
     * Ends the current player's turn, updates the game state, and begins the next player's turn.
     */
    private void nextTurn() {
        currentPlayer.setScore(currentPlayer.getScore() + turnScore);
        updatePlayerList();
        updateGameScreen();
        if (currentPlayer.getScore() >= Bulldog.WINNING_SCORE) {
            JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " wins!");
            System.exit(0);
        }
        turnScore = 0;
        rollHistoryArea.setText("");
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        currentPlayer = players.get(currentPlayerIndex);
        playTurn();
    }
    
    /**
     * Update the player list to show current scores and the currently active player.
     */
    private void updatePlayerList() {
        playersListModel.clear();
        for (Player p : players) {
            playersListModel.addElement(String.format("%s: %d%s", p.getName(), p.getScore(), p.equals(currentPlayer) ? " <" : ""));
        }
    }
    
    /**
     * Update the game screen with the current player's details and score earned.
     */
    private void updateGameScreen() {
        currentPlayerLabel.setText(String.format("Current Player: %s", currentPlayer.getName()));
        turnScoreLabel.setText(String.format("Turn score: %d", turnScore));
    }
}
