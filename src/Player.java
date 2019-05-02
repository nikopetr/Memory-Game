import java.io.Serializable;
/** Represents a player of the memory game. The class implements Serializable for saving objects in files.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Player implements Serializable //Implements Serializable in order to save the high-scores in objects  in files.
{

    /** Represents the number of user’s total card picks.
     */
    private int totalCardPicks;

    /** Represents the number of total card pairs found.
     */
    private int totalPairsFound;

    /** Represents the name of the player.
    */
     private String name;

    /** Creates a player.
     */
    public Player()
    {
        totalCardPicks = 0;
        totalPairsFound = 0;
    }

    /** Sets the name of the player.
     * @param name A String representing the name of the player.
     */
    public void setName(String name){this.name = name;}

    /** Returns the player’s name.
     * @return A String representing the player's name.
     */
    public String getName()
    {
        return name;
    }

    /** Increases the number of total card picks.
     */
    void updateCardPicks()
    {
        totalCardPicks++;
    }

    /** Increases the number of total card picks.
     */
    void updatePairsFound()
    {
        totalPairsFound++;
    }

    /** Returns the user’s number of total card picks .
     *@return An integer representing the number of total card picks .
     */
    int getTotalCardPicks()
    {
        return totalCardPicks;
    }

    /** Returns the user’s number of total card pairs found .
     *@return An integer representing the number of total card pairs found .
     */
    int getTotalPairsFound()
    {
        return totalPairsFound;
    }
}
