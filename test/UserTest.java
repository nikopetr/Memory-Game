import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void updateTotalWins() {
        User aUser = new User();
        assertEquals(0,aUser.getTotalWins());
        aUser.updateTotalWins();
        assertEquals(1,aUser.getTotalWins());
        aUser.updateTotalWins();
        aUser.updateTotalWins();
        assertEquals(3,aUser.getTotalWins());
    }

    @Test
    public void getTotalWins() {
        User aUser = new User();
        assertEquals(0,aUser.getTotalWins());
        aUser.updateTotalWins();
        assertEquals(1,aUser.getTotalWins());
        aUser.updateTotalWins();
        assertEquals(2,aUser.getTotalWins());

    }

    @Test
    public void compareTo() {
        User user1 = new User();
        User user2 = new User();
        user1.updateCardPicks();
        assertEquals(1,user1.compareTo(user2));
        assertEquals(-1,user2.compareTo(user1));

        user2.updateCardPicks();
        assertEquals(-1,user2.compareTo(user1));
        assertEquals(-1,user1.compareTo(user2));

        user1.updateTotalWins();
        assertEquals(-1,user1.compareTo(user2));
        assertEquals(1,user2.compareTo(user1));

        user2.updateTotalWins();
        assertEquals(-1,user2.compareTo(user1));
        assertEquals(-1,user1.compareTo(user2));
    }

    @Test
    public void testToString() {
        new Gui(); //in order to get the messages
        User aUser = new User();
        aUser.setName("Greg");
        assertEquals(Gui.getMessages().getString("name") + ": " + "Greg"+ " " + Gui.getMessages().getString("fewestCardsMessage") + ": " + "0" + ",  " + Gui.getMessages().getString("totalWins") + ": " + "0",aUser.toString());
        aUser.updateCardPicks();
        aUser.updateCardPicks();
        aUser.updateTotalWins();
        assertEquals(Gui.getMessages().getString("name") + ": " + "Greg"+ " " + Gui.getMessages().getString("fewestCardsMessage") +
                ": " + "2" + ",  " + Gui.getMessages().getString("totalWins") + ": " + "1",aUser.toString());

    }
}