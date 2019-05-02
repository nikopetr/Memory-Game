/** Represents a controlled User of the memory game.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class User extends Player implements Comparable<User>
{
    /** Represents the total wins of the user.
     */
    private int totalWins;

    /** Creates a user by calling the constructor of the super class Player.
     */
    public User()
    {
        super();
        totalWins = 0;
    }

    /** Increases the number of total card wins of the user.
     */
    void updateTotalWins()
    {
        totalWins++;
    }

    /** Returns the user’s number of total wins .
     *@return An integer representing the number of total wins of the user.
     */
    int getTotalWins()
    {
        return totalWins;
    }

    /** Compares the scores (totalCardPicks,totalWins) of the user to another user.
     * @param aUser A user representing one other user of the game.
     * @return An integer representing if the scores of the user is greater than the scores of the other user given.
     */
    @Override
    public int compareTo(User aUser) //Overwritten method to compare users for the high-score ranking. First checks lowest card picks , then most wins.
    {
        if (aUser.getTotalCardPicks() < this.getTotalCardPicks())
            return 1;

        else if (aUser.getTotalCardPicks() > this.getTotalCardPicks())
            return -1;

        else if (aUser.getTotalWins() > this.getTotalWins())
            return 1;

        else
            return -1;
    }

    /**
     * Returns the name, totalCardPicks and totalWins of the user in a way  to be presented on the screen for the High-Scores.
     * @return A String with the name, totalCardPicks and totalWins of the user.
     */
    @Override
    public String toString()
    {
        return Gui.getMessages().getString("name") + ": " + getName()+ " " + Gui.getMessages().getString("fewestCardsMessage") +
                ": " + getTotalCardPicks() + ",  " + Gui.getMessages().getString("totalWins") + ": " + totalWins;
    }
}