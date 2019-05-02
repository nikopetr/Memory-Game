import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;


/** Reads and writes User Objects of the game winners, on files.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class StatsFileSave
{
    private static final int MAX_HIGHSCORE_SAVED_USERS = 100;
    private static final int MAX_HIGHSCORE_PRESENTED_USERS = 20;

    //Different files for each game - mode
    private static final String BASIC_GAMEMODE_SCORES_FILE_NAME = "scores-basic.ser";
    private static final String DOUBLE_GAMEMODE_SCORES_FILE_NAME = "scores-double.ser";
    private static final String TRIPLE_GAMEMODE_SCORES_FILE_NAME = "scores-triple.ser";
    private static final String DUEL_GAMEMODE_SCORES_FILE_NAME = "scores-duel.ser";

    /** Represents the file name of the file that the User is going to be saved
     */
    private String fileName;

    /** Creates a StatsFileSave and sets the fileName that the data is going to be saved based on the given gameType.
     * If the type is not in range : [1, 4] throws ArithmeticException.
     * @param gameType An integer representing the game type of the game.
     */
    public StatsFileSave(int gameType)
    {
        switch (gameType)
        {
            case 1:  // Basic-GameMode
            {
                fileName = BASIC_GAMEMODE_SCORES_FILE_NAME;
                break;
            }

            case 2:// Double-GameMode
            {
                fileName = DOUBLE_GAMEMODE_SCORES_FILE_NAME;
                break;
            }
            case 3:// Triple-GameMode
            {
                fileName = TRIPLE_GAMEMODE_SCORES_FILE_NAME;
                break;
            }

            case 4://Duel game-mode
            {
                fileName = DUEL_GAMEMODE_SCORES_FILE_NAME;
                break;
            }

            default:
            {
                throw new ArithmeticException("No game-type: " + gameType + "available to save the stats.");
            }
        }
    }

    /** Reads from the current game's file, if the gameWinner already exists in files then the best card picks will be written and a win will be added to his total wins.
     * Else if gameWinner isn't in the already in the files he will be written and the ranking will be sorted based mostly from totalCardPicks and then the wins of the User.
     * The max Users that will be saved in the files are 100.
     * @param gameWinner A User representing the user who won the game.
     */
    void saveUserStats(User gameWinner)
    {
        ArrayList<User> highScoreUsers = new ArrayList<>();
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            boolean cont = true;
            while (cont)
            {
                User aUser = (User) in.readObject();
                if (aUser != null && highScoreUsers.size() < MAX_HIGHSCORE_SAVED_USERS)
                {
                    if (!aUser.getName().equals(gameWinner.getName()))
                        highScoreUsers.add(aUser);
                    else // if User already exists in files
                    {
                        if (aUser.getTotalCardPicks() < gameWinner.getTotalCardPicks()) // selects the best card picks for new total card picks
                            gameWinner = aUser;

                        while (gameWinner.getTotalWins() < aUser.getTotalWins())  // adds wins until the total wins is same as his previous wins
                            gameWinner.updateTotalWins();
                    }
                }
                else
                    cont = false;
            }

        }
        catch (Exception exc) {
            //end of file
        }
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {

            gameWinner.updateTotalWins();
            highScoreUsers.add(gameWinner);

            Collections.sort(highScoreUsers); // Sorted based on card picks and then wins.
            for (User aUser : highScoreUsers)
            {
                out.writeObject(aUser);
            }


        } catch(Exception ex){
            //end of file
        }
    }

    /** Reads from the file and returns the 20 best ranked Users of the current game.
     * @return  An ArrayList of Users represents the Users saved in the files.
     */
    ArrayList<User> getTopUsers()
    {
        ArrayList<User> topUsers = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            boolean cont = true;
            while (cont)
            {
                User aUser = (User) in.readObject();
                if (aUser != null && topUsers.size() < MAX_HIGHSCORE_PRESENTED_USERS) //Only showing the top 20 scores on screen
                    topUsers.add(aUser);
                else
                    cont = false;
            }
        } catch (Exception e){
            //end of file
        }

        return topUsers;
    }
}
