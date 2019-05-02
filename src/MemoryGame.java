import java.util.ArrayList;

/** Memory Game Interface representing the game.
 * @author Nikolas Petrou
 * @version 2.0
 */

public interface MemoryGame  {

    /** Returns the game’s board.
     * @return A Board representing the game’s board.
     */
    Board getGameBoard();

    /** Returns the game’s current player.
     * @return A Player representing the Player who is currently playing.
     */
    Player getCurrentPlayer();

    /** Returns the games'current player's index.
     * @return An integer representing the index of the Player who is currently playing.
     */
    int getCurrentPlayerIndex();

    /** Returns the game’s winning player.
     * @return A Player representing the Player who won at the game.
     */
    Player getGameWinner();

    /** Returns the cards that are available for flip.
     * @return An ArrayList of int[] representing the x and y coordinates of the cards that are available for flip.
     */
    ArrayList<int []> getAvailableCardPicks();

    /** Updates the card picks of the Players, Bots' memory and the AvailableCardPicks. Increases the current Player's number of current turn picks.
     * if the flipped cards match it removes these cards from the board, else resets the current turn picks.
     * Returns true if the the number of current turn picks is equal to copies or if the cards don't match.
     * @return A boolean representing if the board is updated.
     */
    boolean update();

    /** Flips the given card.
     * @param cardPos An array representing the x and y coordinates of the selected card.
     * @return A Boolean representing if the specified card is now flipped.
     */
    boolean flipCard(int [] cardPos);

    /** Returns if the game if finished.
     * @return A boolean representing if the game if finished.
     */
    boolean isGameFinished();

    /** Current Bot player generates and flips a card.
     */
    void botTurn();
}
