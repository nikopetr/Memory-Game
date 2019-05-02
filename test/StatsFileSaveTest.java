import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatsFileSaveTest {

    @Test
    public void saveUserStats() {
        System.out.println("saveUserStats");

        final int  MANY_CARD_PICKS = 300;

        StatsFileSave aStatFileSaving = new StatsFileSave(1);
        User aTestuser= new User();
        aTestuser.setName("userForTesting");

        for (int i = 0; i < MANY_CARD_PICKS; i++)
            aTestuser.updateCardPicks();

        aStatFileSaving.saveUserStats(aTestuser);
        ArrayList<User> winnerList = aStatFileSaving.getTopUsers();

        boolean playerFound = false;
        for (User aUser : winnerList)
            if (aUser.getName().equals(aTestuser.getName()))
            {
                playerFound = true;
                assertEquals(aTestuser.getName(),aUser.getName());
                break;
            }
        assertTrue(playerFound);
    }

    @Test
    public void getTopUsers()
    {
        System.out.println("getTopUsers");

        final int  MANY_CARD_PICKS = 300;
        StatsFileSave aStatFileSaving = new StatsFileSave(1);
        User aTestuser= new User();
        aTestuser.setName("userForTesting");

        for (int i = 0; i < MANY_CARD_PICKS; i++)
            aTestuser.updateCardPicks();

        aStatFileSaving.saveUserStats(aTestuser);
        ArrayList<User> winnerList = aStatFileSaving.getTopUsers();

        boolean playerFound = false;
        for (User aUser : winnerList)
            if (aUser.getName().equals(aTestuser.getName()))
            {
                playerFound = true;
                assertEquals(aTestuser.getName(),aUser.getName());
                break;
            }
        assertTrue(playerFound);
    }
}