import java.util.ArrayList;

/**
 * The PlayerModel class manages a list of players and their scores.
 * It also allows ScoreViewer objects to follow and receive updates when scores change.
 * 
 * @author Ben Gaudreau (with assistance from ChatGPT 4)
 * @version Mar 24 2025
 */
public class BulldogModel {
    private ArrayList<Player> playerList;
    private ArrayList<BulldogViewer> followerList;

    /**
     * Constructs a PlayerModel with an initial list of players.
     * 
     * @param players the list of players to initialize the model with
     */
    public BulldogModel(ArrayList<Player> players) {
        playerList = players;
        followerList = new ArrayList<BulldogViewer>();
    }
    
    /**
     * Constructs a PlayerModel with an empty list of players.
     */
    public BulldogModel() {
        this(new ArrayList<Player>());
    }

    /**
     * Adds a player to the model.
     * 
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }
    
    /**
     * Retrieves a player at a specific index.
     * 
     * @param index the index of the player
     * @return the player at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Player getPlayer(int index) {
        if (index >= 0 && index < playerList.size()) {
            return playerList.get(index);
        } else {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, playerList.size()));
        }
    }

    /**
     * Retrieves the name of the player at a specific index.
     * 
     * @param index the index of the player
     * @return the name of the player
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getPlayerName(int index) {
        if (index >= 0 && index < playerList.size()) {
            return playerList.get(index).getName();
        } else {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, playerList.size()));
        }
    }

    /**
     * Retrieves the score of the player at a specific index.
     * 
     * @param index the index of the player
     * @return the score of the player
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public int getPlayerScore(int index) {
        if (index >= 0 && index < playerList.size()) {
            return playerList.get(index).getScore();
        } else {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, playerList.size()));
        }
    }

    /**
     * Updates the score of a player at a specific index and notifies followers.
     * 
     * @param index the index of the player
     * @param newScore the new score to set
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setPlayerScore(int index, int newScore) {
        if (index >= 0 && index < playerList.size()) {
            playerList.get(index).setScore(newScore);
            notifyFollowers();
        } else {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, playerList.size()));
        }
    }
    
    /**
     * Retrieves the number of players in the model.
     * 
     * @return the size of the player list
     */
    public int getListSize() {
        return playerList.size();
    }
    
    /**
     * Adds a ScoreViewer as a follower to receive updates.
     * 
     * @param sv the ScoreViewer to add
     */
    public void addFollower(BulldogViewer sv) {
        followerList.add(sv);
    }
    
    /**
     * Notifies all followers about score updates.
     */
    public void notifyFollowers() {
        for (BulldogViewer sv : followerList) {
            sv.update(this);
        }
    }
}