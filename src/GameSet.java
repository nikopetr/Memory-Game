import java.util.ArrayList;

/** Represents a set that contains a player and the available card picks of his array of cards for the memory game duel.
 *  @author Nikolas Petrou
 *  @version 2.0
 */
public class GameSet {

    /** An ArrayList of int[] representing the x and y coordinates of the cards that are available for flip.
    */
    private ArrayList<int []> availableCardPicks;

    /** Represents a player of the memory game duel.
     */
    private Player gamePlayer;

    /** Creates a GameSet with a specified player and an ArrayList of int[] for the available card picks of the
     * player's array.
     * @param availableCardPicks An ArrayList of int[] representing the x and y coordinates of the cards that are
     * available for flip.
     * @param gamePlayer A player representing a specified player of the memory game duel.
     */
    public GameSet(ArrayList<int[]> availableCardPicks, Player gamePlayer)
    {
        this.availableCardPicks = availableCardPicks;
        this.gamePlayer = gamePlayer;
    }

    /** Returns the available card picks.
    *  @return An ArrayList of int[] representing the x and y coordinates of the cards that are available for flip.
    */
    public ArrayList<int[]> getAvailableCardPicks() {
        return availableCardPicks;
    }

    /** Returns a specified player of the memory game duel.
    * @return A Player representing a specified player of the memory duel.
     */
    Player getGamePlayer() {
        return gamePlayer;
    }
}
