import java.util.*;
/** Represents a bot for the MemoryGame. A Player not controlled by a user.
 *  There are three types of Bots that have different memory level,
 *  botType 1 remembers nothing, botType 2 remembers half of the cards and botType 3 remembers every card opened.
 *  The Bot is coded in a way to always open non-discovered cards in order to have increased chances for a match and to be more challenging to the the Users.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Bot extends Player {

    /** Represents the copies of the cards of the memory game.
     */
    private static int copies ;

    /** Represents the type of the bot, how strong it's memory is.
     */
    private int botType;

    /** A HashMap of integers, representing the memory of the bot.
     */
    private HashMap<int[], Character> boardMap;

    /** An ArrayList representing the x and y coordinates of the flipped cards.
     */
    private ArrayList <int []> flippedCardsPos;

    /** Creates a bot by calling the constructor of the super class Player and the given type of the bot.
     * @param botType An int for the type of the bot.
     */
    public Bot(int botType) {
        super();
        this.botType = botType;
        boardMap = new HashMap<>();
        flippedCardsPos = new ArrayList<>();
    }

    /** Returns the x and y coordinates of the flipped cards.
    *  @return An ArrayList representing the x and y coordinates of the flipped cards.
     */
    ArrayList<int[]> getFlippedCardsPos()
    {
        return flippedCardsPos;
    }

    /** Sets the copies of the cards of the game.
     * @param aCopiesVal An int representing the copies of the cards for this game.
    */
    static void setCopies(int aCopiesVal)
    {
        copies = aCopiesVal;
    }

    /** Returns if a card at position cardSelected is flipped.
     * @param cardSelected An int[] representing the x and y coordinates of the card.
     * @return  A boolean representing if the card is flipped.
     */
    private boolean isFlipped (int [] cardSelected)
    {
        for (int[] cardPos : flippedCardsPos)
            if (cardPos[0] == cardSelected[0] && cardPos[1] == cardSelected[1])
                return true;

        return false;
    }

    /** Returns if a card at position cardSelected is in the boardMap of the bot.
     * @param cardSelected An int[] representing the x and y coordinates of the card.
     * @return A boolean representing if the card is in the boardMap.
     */
    private boolean isNotMapped(int [] cardSelected)
    {
        for (int[] cardPos : boardMap.keySet())
            if (cardPos[0] == cardSelected[0] && cardPos[1] == cardSelected[1])
                return false;

        return true;
    }

    /** Returns a card's x any y coordinates or null if the card is not in the boardMap.
     * @param cardContent A char representing the card content of the card.
     * @return An int[] representing the card's x and y coordinates.
     */
    int[] getCardPos (char cardContent)
    {
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if(entry.getValue() == cardContent)
                return entry.getKey();

        return null;
    }

    /** Clearing the ArrayList with the flipped cards.
     */
    void removeFromFlipped()
    {
        flippedCardsPos.clear();
    }


    /** Selects the next card pick of the bot.Traverse the boardMap of the bot,checks if flippedCardsPos is empty or if the flipped card's
    * content is the same with the map's content then the selected card is the current key of the boardMap and add it to flippedCardPos else
    * get a random card from the availableCardPicks as the selected card until the selected card if flipped or the boardMap contains
    * the coordinates of the selected card. Returns the selected card.
    * @param availableCardPicks An ArrayList of integers representing the x and y coordinates of the cards that are available for flip.
    * @return An int[] representing the x and y coordinates of the selected card.
    */
    public int[] generateCardPick(ArrayList <int []> availableCardPicks)
    {
        int[] cardSelected;
       for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
       {
           Character cardContent = entry.getValue();

           if (Collections.frequency(boardMap.values(), cardContent) == copies && !isFlipped(entry.getKey())) //If the card with the specific content is known for all it's copies.
           {
               if (flippedCardsPos.isEmpty())
               {
                   cardSelected = entry.getKey();
                   flippedCardsPos.add(cardSelected);
                   return cardSelected;
               }

               else if (cardContent == boardMap.get(flippedCardsPos.get(0))) // if the flipped card's content is same with the map's content.
               {
                   cardSelected = entry.getKey();
                   flippedCardsPos.add(cardSelected);
                   return cardSelected;
               }
           }
       }

      do {
          Random randomPos = new Random();
          int index = randomPos.nextInt(availableCardPicks.size());
          cardSelected = availableCardPicks.get(index);
      }while (isFlipped(cardSelected) || !(isNotMapped(cardSelected))); //Bot's card pick is clever not to pick card that already knows where it is. Making it for users very hard to beat.


       flippedCardsPos.add(cardSelected);
       return cardSelected;
    }

    /** Removes the cards with the given card content from the boardMap of the bot.
     * @param cardContent A char representing the card's content
     */
    void clearCardsFromMemory(char cardContent)
    {
        boardMap.entrySet().removeIf(entry -> entry.getValue() == cardContent);
    }

    /** Updates the boardMap of the bot by checking it's type (1-remembers nothing,2-remember half cards opened
    * 3-remembers all cards opened) and taking a card's x and y coordinates and it's card content.
    * @param cardPos An int[] representing the card's x and y coordinates.
    * @param cardContent A char representing the card's content.
     */
    void updateBoardMap(int [] cardPos, char cardContent)
    {
        switch (botType)
        {
            case 1:  // GoldFish - remembers nothing
            {
                break;
            }

            case 2:// Kangaroo - remembers half cards opened
            {
                Random randomPos = new Random();
                int rememberChance = randomPos.nextInt(2);

                if (rememberChance == 1) // Will remember this card
                {
                    if (isNotMapped(cardPos))
                        boardMap.put(cardPos, cardContent);
                }
                else // Will not remember this card
                {
                    if (isFlipped(cardPos) && boardMap.containsValue(cardContent) && (isNotMapped(cardPos))) // Checks if this flipped card content is already known in another card, because our bot is not that dumb
                        boardMap.put(cardPos, cardContent);
                }

                break;
            }

            case 3://Elephant - remembers all cards opened
            {
                if (isNotMapped(cardPos))
                    boardMap.put(cardPos, cardContent);
                break;
            }

            default:
        }
    }
}