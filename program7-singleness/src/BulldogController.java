import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * The BulldogController class manages the game flow and GUI interactions
 * for the Bulldog game.
 * 
 * @author Ben Gaudreau (with assistance from ChatGPT-4)
 * @version Mar 25 2025
 */
public class BulldogController {
    private BulldogModel playerModel;
    private BulldogViewer scoreViewer;
    private JFrame frame;
    private JPanel gamePanel, playerPanel, infoPanel, buttonPanel;
    private JButton nextTurnButton;
    private JLabel currentPlayerLabel, turnScoreLabel;
    private JTextArea rollHistoryArea;
    private DefaultListModel<String> playersListModel;
    private JList<String> playersList;
    
    private static final int WINNING_SCORE = 104;
    
    private int currentPlayerIndex;
    private int turnScore;

    /**
     * Constructs a BulldogController, initializing the game model,
     * viewer, GUI components, and starting the game loop.
     */
    public BulldogController() {
        this.playerModel = new BulldogModel(initializePlayers());
        this.scoreViewer = new BulldogViewer();
        this.currentPlayerIndex = 0;
        this.turnScore = 0;

        playerModel.addFollower(scoreViewer);
        initializeGameWindow();
        playTurn();
    }

    /**
     * Initializes players by prompting the user for input.
     * @return A list of Player objects.
     */
    private ArrayList<Player> initializePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players:"));
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(String.format("Enter name for Player %d:", i + 1));
            String[] types = {"Human", "CPU - Wimp", "CPU - Random", "CPU - Fifteen", "CPU - Fiver", "CPU - Lucky"};
            String type = (String) JOptionPane.showInputDialog(null, "Select player type:", "Player Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            players.add(createPlayer(type, name));
        }
        return players;
    }

    /**
     * Creates a player based on the selected type.
     * @param type The type of player.
     * @param name The name of the player.
     * @return A Player object.
     */
    private Player createPlayer(String type, String name) {
        switch (type) {
            case "Human": return new HumanPlayer(name);
            case "CPU - Wimp": return new WimpPlayer(name);
            case "CPU - Random": return new RandomPlayer(name);
            case "CPU - Fifteen": return new FifteenPlayer(name);
            case "CPU - Fiver": return new FiverPlayer(name);
            case "CPU - Lucky": return new LuckyPlayer(name);
            default: return null;
        }
    }

    /**
     * Initializes the main game window and GUI components.
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
    }

    /**
     * Plays the current player's turn and updates the UI.
     */
    public void playTurn() {
        Player currentPlayer = playerModel.getPlayer(currentPlayerIndex);
        turnScore = currentPlayer.play();
        updateGameScreen(currentPlayer, turnScore);
    }

    /**
     * Ends the current turn, updates scores, checks for a winner, and starts the next turn.
     */
    public void nextTurn() {
        Player currentPlayer = playerModel.getPlayer(currentPlayerIndex);
        playerModel.setPlayerScore(currentPlayerIndex, currentPlayer.getScore() + turnScore);
        updatePlayerList();

        if (currentPlayer.getScore() >= WINNING_SCORE) {
            JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " wins!");
            System.exit(0);
        }

        turnScore = 0;
        rollHistoryArea.setText("");
        currentPlayerIndex = (currentPlayerIndex + 1) % playerModel.getListSize();
        playTurn();
    }

    /**
     * Updates the player list display.
     */
    private void updatePlayerList() {
        playersListModel.clear();
        for (int i = 0; i < playerModel.getListSize(); i++) {
            Player p = playerModel.getPlayer(i);
            playersListModel.addElement(String.format("%s: %d", p.getName(), p.getScore()));
        }
    }

    /**
     * Updates the game screen with the current player's information.
     * @param currentPlayer The player currently taking their turn.
     * @param turnScore The score earned in the current turn.
     */
    private void updateGameScreen(Player currentPlayer, int turnScore) {
        currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
        turnScoreLabel.setText("Turn score: " + turnScore);
        rollHistoryArea.append(String.format("%s scored %d this turn.\n", currentPlayer.getName(), turnScore));
    }

    /**
     * Main method to start the game.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new BulldogController();
    }
}