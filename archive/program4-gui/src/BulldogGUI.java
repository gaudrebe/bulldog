/**
 * BulldogGUI - GUI Implementation of Bulldog
 * @author Ben Gaudreau (with assistance from ChatGPT)
 * @version Feb-21-2025
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The BulldogGUI class represents the graphical user interface for the Bulldog game.
 * It handles player initialization, game flow, and UI interactions.
 */
public class BulldogGUI {
    
    private static final int MAX_SCORE = 104;
    
    private JFrame frame;
    private JPanel gamePanel, playerPanel, infoPanel, buttonPanel;
    private JButton rollButton, endTurnButton;
    private JLabel currentPlayerLabel, turnScoreLabel;
    private JTextArea rollHistoryArea;
    private DefaultListModel<String> playersListModel;
    private JList<String> playersList;
    
    private int numPlayers;
    private List<Player> players;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private int turnScore;
    
    /**
     * Constructs a new BulldogGUI game instance and initializes the game window.
     */
    public BulldogGUI() {
        this.numPlayers = 0;
        this.players = initializePlayers();
        this.currentPlayerIndex = 0;
        this.currentPlayer = this.players.get(currentPlayerIndex);
        this.turnScore = 0;
        initializeGameWindow();
    }
    
    /**
     * Initializes the players by prompting the user for player details.
     *
     * @return A list of initialized players.
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
            String[] types = {"Human", "CPU - Wimp", "CPU - Random", "CPU - Fifteen", "CPU - Unique", "CPU - Fiver"};
            String type = (String) JOptionPane.showInputDialog(null, "Select player type:", "Player Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            
            Player p;
            switch (type) {
                case "Human":
                    p = new HumanPlayer(name, null);
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
                case "CPU - Unique":
                    p = new UniquePlayer(name);
                    break;
                case "CPU - Fiver":
                    p = new BenUniquePlayer(name);
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
        
        rollButton = new JButton("Roll Again");
        rollButton.addActionListener(e -> rollDie(false));
        endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(e -> endTurn());
        buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(rollButton);
        buttonPanel.add(endTurnButton);
        
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(infoPanel, BorderLayout.NORTH);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        playTurn();
    }
    
    /**
     * Updates the player list display.
     */
    private void updatePlayerList() {
        playersListModel.clear();
        for (Player p : players) {
            playersListModel.addElement(String.format("%s: %d%s", p.getName(), p.getScore(), p.equals(currentPlayer) ? " <" : ""));
        }
    }
    
    /**
     * Updates the game screen with the current player's details.
     */
    private void updateGameScreen() {
        currentPlayerLabel.setText(String.format("Current Player: %s", currentPlayer.getName()));
        turnScoreLabel.setText(String.format("Turn score: %d", turnScore));
    }
    
    /**
     * Plays the current player's turn.
     */
    private void playTurn() {
        updatePlayerList();
        updateGameScreen();
        if (!(currentPlayer instanceof HumanPlayer)) {
            turnScore = currentPlayer.play();
            updateGameScreen();
            rollHistoryArea.append(String.format("%s scored %d this turn.\n", currentPlayer.getName(), turnScore));
        } else {
            rollDie(true);
        }
    }
    
    /**
     * Handles dice rolling logic.
     *
     * @param firstRoll Indicates if it's the first roll of the turn.
     */
    private void rollDie(boolean firstRoll) {
        if (!(currentPlayer instanceof HumanPlayer) || (!firstRoll && turnScore == 0))
            return;
        
        int roll = (int) (Math.random() * 6 + 1);
        rollHistoryArea.append(String.format("Player %s rolled %d.\n", currentPlayer.getName(), roll));
        turnScore = (roll == 6) ? 0 : turnScore + roll;
        
        updateGameScreen();
    }
    
    /**
     * Ends the current player's turn, updates the game state, and begins the next player's turn.
     */
    private void endTurn() {
        currentPlayer.setScore(currentPlayer.getScore() + turnScore);
        updatePlayerList();
        updateGameScreen();
        if (currentPlayer.getScore() >= MAX_SCORE) {
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
     * The main method to start the game.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new BulldogGUI();
    }
}
