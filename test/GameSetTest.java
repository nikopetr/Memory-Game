import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameSetTest {

    @Test
    public void getAvailableCardPicks() {
        ArrayList<int []> availableCardPicks;
        availableCardPicks = new ArrayList<>();
        int[] y = {1,3};
        int[] x = {2,5};
        availableCardPicks.add(y);
        availableCardPicks.add(x);
        Player player = new Player();
        GameSet aGameSet = new GameSet(availableCardPicks,player);
        assertEquals(availableCardPicks,aGameSet.getAvailableCardPicks());
    }


    @Test
    public void getGamePlayer() {
        ArrayList<int []> availableCardPicks;
        availableCardPicks = new ArrayList<>();
        int[] y = {1,3};
        int [] x = {2,5};
        availableCardPicks.add(y);
        availableCardPicks.add(x);
        Player player = new Player();
        GameSet aGameSet = new GameSet(availableCardPicks,player);
        assertEquals(player,aGameSet.getGamePlayer());
    }
}