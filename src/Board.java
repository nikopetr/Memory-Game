import javax.swing.*;
/** Board Interface representing the board of the game
 * @author Nikolas Petrou
 * @version 2.0
 */
public interface Board {

    /** Flips a card at position (i, j).
    * @param i An integer representing the x-position of the selected card.
    * @param j An integer representing the y-position of the selected card.
    * @return A boolean representing if the card got flipped.
    */
    boolean flipCard(int i, int j);

    /** Checks if the flipped cards' content matches, if the ArrayList flippedCards is empty returns false.
     *  @return A boolean representing if the flipped cards match.
    */
    boolean doFlippedCardsMatch();

    /** Returns the char content of the card at position (i, j).
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return A character that represents the card content at position (i, j).
    */
    char getCardContent(int i, int j);

    /** Returns the printing icon of the card at position (i, j).
     *@param i An integer representing the x-position of the selected card.
     *@param j An integer representing the y-position of the selected card.
     *@return An Icon that represents the card's icon at position (i, j).
     */
    Icon getCardIconFromBoard(int i, int j);

    /**Removes the matching flipped cards and returns the char content of these(removed) cards.
     * @return A character that represents the content of the removed cards.
     */
    char removeCardsFromBoard();

    /** Returns the total amount of cards found.
     *  @return An integer that represents the amount of cards found.
    */
    int getCardsFound();

    /** Returns the number of the columns of the board.
     *  @return An integer that represents the amount of rows the board has.
    */
    int getRows();

    /** Returns the number of the columns of the board.
     *  @return An integer that represents the amount of columns the board has.
    */
    int getCol();

}