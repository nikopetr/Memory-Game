import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
/** Represents and implements a dueling board with mixed cards for the memory game.
 * Returns the printing Icon of a card in the board of each player. Flips, checks and removes cards from board(each player's board).
 * @author Nikolas Petrou
 * @version 2.0
 */
public class DuelingBoard implements Board{

    private static final int ROWS = 4;
    private static final int COL = 6;

    /** A list by arrays of card representing all the cards for the game.
     */
    private ArrayList<Card [][]> gameCards;

    /** Representing the current's array index of gameCards(0 - first array, 1 - second array)
     */
    private int currentBoardIndex ;

    /** Representing the most recent card's content.
     */
    private char mostRecentCardContent ;

    /** List for the cards that are currently flipped (faced-up).
     */
    private ArrayList<Card> flippedCards;

    /** Representing the total amount of cards found.
     */
    private int cardsFound;

    /** Returns the current array of gameCards.
     * @return An int representing the current array of gameCards.
     */
    int getCurrentBoardIndex()
    {
        return currentBoardIndex;
    }

    /**Creates a dueling board from 2 arrays of cards filled by shuffled cards of the game.
     * Creates a list(gameCards) and puts the 2 arrays of the board.
     */
    public DuelingBoard()
    {
        Card [][] gameCardsA = new Card[ROWS][COL];
        Card [][] gameCardsB = new Card[ROWS][COL];

        flippedCards = new ArrayList<>();
        currentBoardIndex = 0;
        cardsFound = 0;
        currentBoardIndex = 0;

        ArrayList<Character> boardContent = new ArrayList<>();
        for (char c = 'A'; boardContent.size() < (ROWS * COL); c++)
            boardContent.add(c);

        Collections.shuffle(boardContent);

        int index = 0;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COL; j++)
            {
                gameCardsA[i][j] = new Card(boardContent.get(index++));
            }

        Collections.shuffle(boardContent);
        index = 0;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COL; j++)
            {
                gameCardsB[i][j] = new Card(boardContent.get(index++));
            }

        gameCards = new ArrayList<>();
        gameCards.add(gameCardsA);
        gameCards.add(gameCardsB);
    }

    /** Changes the current array of the board by changing the current array's index of gameCards.
     */
    void changeBoard()
    {
        currentBoardIndex = (currentBoardIndex + 1 ) % 2;
    }

    /** Flips a card at position (i, j) of the current array of gameCards and adding it to the flippedCards ArrayList.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return A boolean representing if the card got flipped.
     */
    public boolean flipCard(int i, int j)
    {
        if (!flippedCards.contains(gameCards.get(currentBoardIndex)[i][j]) && !gameCards.get(currentBoardIndex)[i][j].isTaken())
        {
            flippedCards.add(gameCards.get(currentBoardIndex)[i][j]);
            mostRecentCardContent = gameCards.get(currentBoardIndex)[i][j].getContent();
            return true;
        }
        else
            return false;
    }

    char getMostRecentCardContent()
    {
        return mostRecentCardContent;
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

    /** Returns the char content of the current array's card at position (i, j).
     * @return A character that represents the card content at position (i, j).
     */
    public char getCardContent(int i, int j)
    {
        return (gameCards.get(currentBoardIndex)[i][j].getContent());
    }

    /** Returns the char content of the card at position (i, j) from the first array of the gameCards.
     * @return A character that represents the card content at position (i, j), from the first array of the gameCards.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     */
    char getCardContentBoard1(int i, int j)
    {
        return (gameCards.get(0)[i][j].getContent());
    }

    /** Returns the printing char content of the card at position (i, j) from the second array of the gameCards.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return A character that represents the card content at position (i, j), from the second array of the gameCards.
     */
    char getCardContentBoard2(int i, int j)
    {
        return (gameCards.get(1)[i][j].getContent());
    }


    /** Sets all the cards that are currently flipped to taken and updates the number of total cards found.
     *  Removes the cards from the flippedCards list.
     * @return A character that represents the content of the removed cards.
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
        return ROWS;
    }

    /** Returns the number of the columns of the board.
     * @return An integer that represents the amount of columns the board has.
     */
    public int getCol()
    {
        return COL;
    }

    /** Returns the printing icon of the card at position (i, j) from the first array of the gameCards or null if the card
     *  is taken.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return An Icon that represents the card's Icon at position (i, j) from the first array of the gameCards.
     */
    public Icon getCardIconFromBoard(int i, int j)
    {
        if (gameCards.get(0)[i][j].isTaken())
            return null;
        else if (flippedCards.contains(gameCards.get(0)[i][j]))
            return gameCards.get(0)[i][j].getCurrentIcon();

        else
            return (Card.getCardBackIcon());
    }

    /** Returns the printing icon of the card at position (i, j) from the second array of the gameCards or null if the
     * card is taken.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     * @return An Icon that represents the card's Icon at position (i, j) from the second array of the gameCards.
     */
    Icon getCardIconFromBoard2(int i, int j)
    {
        if (gameCards.get(1)[i][j].isTaken())
            return null;
        else if (flippedCards.contains(gameCards.get(1)[i][j]))
            return gameCards.get(1)[i][j].getCurrentIcon();

        else
            return (Card.getCardBackIcon());
    }
}

