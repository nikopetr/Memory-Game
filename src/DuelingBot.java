import java.util.ArrayList;
import java.util.Random;
/** Represents a Dueling bot of the Duel MemoryGame. A Player not controlled by a user.
 * Pick of DuelingBot is different than Bot's, it picks a certain card only if the copy of it is opened at that moment. Otherwise the pick is random.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class DuelingBot extends Bot
{
    /** An int[] representing the generated pick of the dueling bot.
    */
    private int[] generatedPick;

    /** Creates a DuelingBot with the given type.
     * @param botType An int for the type of the bot.
     */
    public DuelingBot(int botType)
    {
        super(botType);
        generatedPick = null;
    }

    /** Selects the next card pick of the bot.Checks if the generated pick is not null and sets the x and y
    *  coordinates of the generated pick as the x and y coordinates of the selected card else get a random card
    *  from the availableCardPicks as the selected card. Returns the selected card.
    *  @param availableCardPicks An ArrayList of int[] representing the available card picks.
    *  @return An int[] representing the x and y coordinates of the selected card.
    */
    public int[] generateCardPick(ArrayList<int []> availableCardPicks)
    {
        int[] cardSelected;

        if (generatedPick != null)
        {
            cardSelected = new int [2];
            cardSelected[0] = generatedPick[0];
            cardSelected[1] = generatedPick[1];
            generatedPick = null;
        }
        else
        {
            Random randomPos = new Random();
            int index = randomPos.nextInt(availableCardPicks.size());//Generates a random pick that it is available
            cardSelected = availableCardPicks.get(index);
        }

        return cardSelected;
    }

    /** Sets the generated card pick of the bot by the card content given.
    * @param cardContent A char representing the card's content.
     */
    void setGeneratedCardPick(char cardContent)
    {
        generatedPick = getCardPos(cardContent);
    }
}
