import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DuelingBotTest {


    @Test
    public void generateCardPick() {
        DuelingBot aDuelingBot = new DuelingBot(3);
        int[] cardPos = {1,2};
        int[] cardPos2={1,3};
        ArrayList<int []> availableCardPicks = new ArrayList<>();
        availableCardPicks.add(cardPos);
        aDuelingBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aDuelingBot.generateCardPick(availableCardPicks));
        aDuelingBot.updateBoardMap(cardPos2,'B');
        availableCardPicks.add(cardPos2);
        cardPos = aDuelingBot.generateCardPick(availableCardPicks);
        assertEquals(cardPos[0],cardPos2[0]);
        assertEquals(cardPos[1],cardPos[1]);

        aDuelingBot = new DuelingBot(1);
        cardPos = new int[] {1,2};
        availableCardPicks = new ArrayList<>();
        availableCardPicks.add(cardPos);
        aDuelingBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aDuelingBot.generateCardPick(availableCardPicks));
        aDuelingBot.updateBoardMap(cardPos2,'B');
        availableCardPicks.add(cardPos2);
        cardPos = aDuelingBot.generateCardPick(availableCardPicks);
        assertEquals(cardPos[0],cardPos2[0]);
        assertEquals(cardPos[1],cardPos[1]);
    }


    @Test
    public void setGeneratedCardPick() {
        System.out.println("setGeneratedCardPick");
        DuelingBot aDuelingBot = new DuelingBot(3);
        int[] cardPos = {1,2};
        int[] cardPos2={1,3};
        ArrayList<int []> availableCardPicks = new ArrayList<>();
        availableCardPicks.add(cardPos);
        aDuelingBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos[0],aDuelingBot.generateCardPick(availableCardPicks)[0]);
        assertEquals(cardPos[1],aDuelingBot.generateCardPick(availableCardPicks)[1]);
        aDuelingBot.updateBoardMap(cardPos2,'B');
        aDuelingBot.setGeneratedCardPick('B');
        availableCardPicks.add(cardPos2);
        cardPos = aDuelingBot.generateCardPick(availableCardPicks);
        assertEquals(cardPos[0],cardPos2[0]);
        assertEquals(cardPos[1],cardPos[1]);


        aDuelingBot = new DuelingBot(1);
        availableCardPicks = new ArrayList<>();
        cardPos = new int[]{1, 2};
        availableCardPicks.add(cardPos);
        aDuelingBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aDuelingBot.generateCardPick(availableCardPicks));
        aDuelingBot.updateBoardMap(cardPos2,'B');
        aDuelingBot.setGeneratedCardPick('B');
        availableCardPicks.add(cardPos2);
        cardPos = aDuelingBot.generateCardPick(availableCardPicks);
        assertEquals(cardPos[0],cardPos2[0]);
        assertEquals(cardPos[1],cardPos[1]);

    }

}