import javax.swing.*;
import java.net.URL;

/** Represents a card.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Card
{
    /** Representing the content of the card.
     */
    private char content;

    /** Gets the card’s back icon.
     * @return An ImageIcon representing the card’s back icon.
     */
    static ImageIcon getCardBackIcon()
    {
        return cardBackIcon;
    }

    /** Representing the card's back icon.
     */
    private static ImageIcon cardBackIcon;  // same for all the cards of the game.

    /** Representing the card's back icon.
     */
    private ImageIcon frontIcon;

    /** Representing if the card is taken.
     */
    private Boolean taken;

    /** Gets the card’s front icon.
     * @return An ImageIcon representing the card’s front icon.
     */
    ImageIcon getCurrentIcon()
    {
            return frontIcon;
    }

    /** Sets the card's back ImageIcon.
     */
    private void setCardBack()
    {
        String imgName = "images/cardBack.jpg";
        URL imageURL = getClass().getResource(imgName);
        if (imageURL != null)
        {
            cardBackIcon = new ImageIcon(imageURL);
        }
    }

    /** Creates a card with back ImageIcon and a specified front Icon by taking the content given.
     * @param content The card’s content.
     */
    public Card(char content)
    {
        setCardBack();
        taken = false;
        this.content = content;

        String imgName = "images/"+ content +".png";
        URL imageURL = getClass().getResource(imgName); //Opening the image saved with the name of the specific content.
        if (imageURL != null)
        {
            frontIcon = new ImageIcon(imageURL);
        }
    }

    /** Gets the card’s content.
     * @return A char representing the card’s content.
     */
    public char getContent()
    {
        return content;
    }

    /** Sets the card's content.
     * @param content The card’s content.
     */
    public void setContent(char content)
    {
        this.content = content;
    }

    /** Returns if the card is taken.
     * @return A boolean representing if the card is taken.
     */
    boolean isTaken()
    {
        return taken;
    }

    /** Sets the card's status(if is taken).
     * @param taken If the card is taken.
     */
    void setTaken(boolean taken)
    {
        this.taken = taken;
    }
}