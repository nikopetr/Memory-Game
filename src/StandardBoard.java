import javax.swing.*;
import java.util.*;
/** Represents and implements a standard board with mixed cards for the memory game.
 * Returns the printing Icon of a card. Flips, checks and removes cards from board.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class StandardBoard implements Board
{
    /** Represents the cards for the game.
     */
    private Card[][] gameCards;

    /** List for the cards that are currently flipped (faced-up).
     */
    private ArrayList<Card> flippedCards;

    /** Represents the rows and columns of the board.
     */
    private int rows, col;

    /** Represents the total amount of found cards.
     */
    private int cardsFound;

    /** Creates a board and shuffles the cards of the game.
     * @param rows An integer representing the rows of the board.
     * @param col An integer representing the total columns of the board.
     * @param copies An integer representing the total copies of each card.
     */
    public StandardBoard(int rows, int col, int copies)
    {
        gameCards = new Card[rows][col];
        flippedCards = new ArrayList<>();
        this.rows = rows;
        this.col = col;
        cardsFound = 0;

        ArrayList<Character> boardContent = new ArrayList<>();
        for (char c = 'A'; boardContent.size() < (rows * col); c++)
        {
            for (int i = 0; i < copies; i++)
                boardContent.add(c);
        }

        Collections.shuffle(boardContent);

        int index = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < col; j++)
            {
                gameCards[i][j] = new Card(boardContent.get(index++));
            }
    }

    /** Flips a card at position (i, j) and adding it to the flippedCards ArrayList.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return A boolean representing if the card got flipped.
     */
    public boolean flipCard(int i, int j)
    {
        if (!flippedCards.contains(gameCards[i][j]) && !gameCards[i][j].isTaken())
        {
            flippedCards.add(gameCards[i][j]);
            return true;
        }
        else
            return false;
    }

    /** Checks if the flipped cards' content matches, if the ArrayList flippedCards is empty returns false.
     *  @return A boolean representing if the flipped cards match.
     */
    public boolean doFlippedCardsMatch()
    {
        char cardContent;
        if (! flippedCards.isEmpty())
            cardContent = flippedCards.get(0).getContent();
        else
            return false;

        for (Card aCard : flippedCards)
        {
            if (cardContent != aCard.getContent())
            {
                flippedCards.clear();
                return false;
            }
        }
        return true;
    }

    /** Returns the char content of the card at position (i, j).
     * @return A character that represents the card content at position (i, j).
     */
    public char getCardContent(int i, int j)
    {
        return (gameCards[i][j].getContent());
    }

    /** Sets all the cards that are currently flipped to taken and updates the number of total cards found.
     *  Removes the cards from the flippedCards ArrayList and returns the char content of these(removed) cards.
     *  @return A character that represents the content of the removed cards.
     */
    public char removeCardsFromBoard()
    {
        for (Card i : flippedCards)
        {
            i.setTaken(true);
            cardsFound++;
        }
        char cardContentRemoved = flippedCards.get(0).getContent();
        flippedCards.clear();
        return cardContentRemoved;
    }

    /** Returns the total amount of cards found.
     * @return An integer that represents the amount of cards found.
     */
    public int getCardsFound()
    {
        return cardsFound;
    }

    /** Returns the number of the rows of the board.
     * @return An integer that represents the amount of rows the board has.
     */
    public int getRows()
    {
        return rows;
    }
    /** Returns the number of the columns of the board.
     * @return An integer that represents the amount of columns the board has.
     */
    public int getCol()
    {
        return col;
    }

    /** Returns the printing icon of the card at position (i, j) or null if the card is taken.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return An Icon that represents the card's Icon at position (i, j).
     */
    public Icon getCardIconFromBoard(int i, int j)
    {
        if (gameCards[i][j].isTaken())
            return null;
        else if (flippedCards.contains(gameCards[i][j]))
            return gameCards[i][j].getCurrentIcon();

        else
            return (Card.getCardBackIcon());
    }
}