import javax.swing.*;

/** Represents a card of the game visually by using JButton so the users can click on the card to flip it.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class CardButton
{
    /** Represents the coordinates of the selected card.
     */
    private int[] cardPos;

    /** JButton that represents the card visually
     */
    private JButton cardButton;

    /** Creates a CardButton with the i and j given coordinates.
     * @param i An integer representing the x-position of the selected card.
     * @param j An integer representing the y-position of the selected card.
     */
    public CardButton(int i, int j)
    {
        cardButton = new JButton();
        cardPos = new int [2];
        cardPos[0] = i;
        cardPos[1] = j;
    }

    /** Returns the coordinates of the specific card.
     * @return An array of integers representing the coordinates of the specific card.
     */
    public int[] getCardPos()
    {
        return cardPos;
    }

    /** Returns the JButton of the specific card.
     * @return A JButton of the specific card.
     */
    JButton getCardButton()
    {
        return cardButton;
    }
}
