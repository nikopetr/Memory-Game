import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void getCardPos() {
        System.out.println("getCardPos");
        Bot aBot = new Bot(3);
        assertNull(aBot.getCardPos('A'));
        int[] cardPos = {1,2};
        aBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aBot.getCardPos('A'));
        Bot Bot2 = new Bot(1);
        Bot2.updateBoardMap(cardPos,'A');
        assertNull(Bot2.getCardPos('A'));
    }

    @Test
    public void removeFromFlipped() {
        System.out.println("removeFromFlipped");
        Bot aBot = new Bot(3);
        assertTrue(aBot.getFlippedCardsPos().isEmpty());

        int[] cardPos = {1,2};
        ArrayList<int []> AvailableCards = new ArrayList<>();
        AvailableCards.add(cardPos);

        aBot.generateCardPick(AvailableCards);
        assertTrue(aBot.getFlippedCardsPos().contains(cardPos));

        aBot.removeFromFlipped();
        assertTrue(aBot.getFlippedCardsPos().isEmpty());
    }

    @Test
    public void generateCardPick() {

        System.out.println("generateCardPick");
        Bot aBot = new Bot(3);
        ArrayList<int []> availableCardPicks = new ArrayList<>();
        int[] cardPos = {1,2};
        int[] cardPos2= {1,3};
        int[] cardPos3= {3,4};
        int[] cardPos4= {5,6};
        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos3);
        availableCardPicks.add(cardPos4);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        ArrayList<int[]> expectedCardPick = new ArrayList<>();
        ArrayList<int[]> generatedCardPick = new ArrayList<>();
        Bot.setCopies(2);
        expectedCardPick.add(cardPos);
        expectedCardPick.add(cardPos3);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

        availableCardPicks.clear();
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos4);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos2);
        expectedCardPick.add(cardPos4);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

        aBot = new Bot(3);
        Bot.setCopies(3);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        aBot.updateBoardMap(cardPos2,'A');
        availableCardPicks.clear();
        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos4);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos);
        expectedCardPick.add(cardPos2);
        expectedCardPick.add(cardPos4);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

        int[] cardPos5 = {2,2};
        int[] cardPos6 = {2,1};
        availableCardPicks.clear();
        availableCardPicks.add(cardPos5);
        availableCardPicks.add(cardPos4);
        availableCardPicks.add(cardPos6);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos5);
        expectedCardPick.add(cardPos4);
        expectedCardPick.add(cardPos6);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));


        aBot = new Bot(3);
        Bot.setCopies(2);
        aBot.updateBoardMap(cardPos3,'A');
        aBot.updateBoardMap(cardPos2,'A');
        availableCardPicks.clear();
        availableCardPicks.add(cardPos3);
        availableCardPicks.add(cardPos2);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos2);
        aBot.generateCardPick(availableCardPicks);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

        aBot = new Bot(3);
        Bot.setCopies(3);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        aBot.updateBoardMap(cardPos2,'A');
        availableCardPicks.clear();
        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos4);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

        aBot = new Bot(3);
        Bot.setCopies(3);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        aBot.updateBoardMap(cardPos2,'A');
        availableCardPicks.clear();
        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos4);
        expectedCardPick.clear();
        expectedCardPick.add(cardPos);
        aBot.generateCardPick(availableCardPicks);
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        generatedCardPick.add(aBot.generateCardPick(availableCardPicks));
        assertTrue(generatedCardPick.containsAll(expectedCardPick));

    }

    @Test
    public void clearCardsFromMemory() {
        System.out.println("clearCardsFromMemory");
        Bot aBot = new Bot(3);
        assertNull(aBot.getCardPos('A'));
        int[] cardPos = {1,2};
        int[] cardPos2 = {1,3};
        aBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos2,'B');
        assertEquals(cardPos2,aBot.getCardPos('B'));
        aBot.clearCardsFromMemory('A');
        assertNull(aBot.getCardPos('A'));
        aBot.clearCardsFromMemory('B');
        assertNull(aBot.getCardPos('B'));

        aBot = new Bot(1);
        assertNull(aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos,'A');
        assertNull(aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos2,'B');
        assertNull(aBot.getCardPos('B'));
        aBot.clearCardsFromMemory('A');
        assertNull(aBot.getCardPos('A'));
        aBot.clearCardsFromMemory('B');
        assertNull(aBot.getCardPos('B'));

    }

    @Test
    public void updateBoardMap() {
        System.out.println("updateBoardMap");
        Bot aBot = new Bot(3);
        assertNull(aBot.getCardPos('A'));
        int[] cardPos = {1,2};
        int[] cardPos2 = {1,3};
        aBot.updateBoardMap(cardPos,'A');
        assertEquals(cardPos,aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos2,'B');
        assertEquals(cardPos2,aBot.getCardPos('B'));

        aBot = new Bot(1);
        assertNull(aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos,'A');
        assertNull(aBot.getCardPos('A'));
        aBot.updateBoardMap(cardPos2,'B');
        assertNull(aBot.getCardPos('B'));

    }
    @Test
    public void getFlippedCardsPos()
    {
        System.out.println("getFlippedCardsPos");
        Bot aBot = new Bot(3);
        ArrayList<int []> availableCardPicks = new ArrayList<>();
        int[] cardPos = {1,2};
        int[] cardPos2= {1,3};
        int[] cardPos3= {3,4};
        int[] cardPos4= {5,6};

        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos3);
        availableCardPicks.add(cardPos4);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        ArrayList<int[]> expectedFlippedCardPos = new ArrayList<>();
        Bot.setCopies(2);
        expectedFlippedCardPos.add(cardPos);
        expectedFlippedCardPos.add(cardPos3);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));


        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));

        aBot.clearCardsFromMemory('A');
        aBot.removeFromFlipped();
        availableCardPicks.clear();
        expectedFlippedCardPos.clear();
        aBot.updateBoardMap(cardPos4,'B');
        aBot.updateBoardMap(cardPos2,'B');
        expectedFlippedCardPos.add(cardPos4);
        expectedFlippedCardPos.add(cardPos2);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos4);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));


        aBot = new Bot(3);
        Bot.setCopies(3);
        aBot.updateBoardMap(cardPos,'A');
        aBot.updateBoardMap(cardPos3,'A');
        aBot.updateBoardMap(cardPos2,'A');
        int[] cardPos5 = {2,2};
        int[] cardPos6 = {2,1};
        availableCardPicks.clear();
        availableCardPicks.add(cardPos);
        availableCardPicks.add(cardPos2);
        availableCardPicks.add(cardPos3);
        availableCardPicks.add(cardPos4);
        availableCardPicks.add(cardPos5);
        availableCardPicks.add(cardPos6);
        expectedFlippedCardPos.clear();
        expectedFlippedCardPos.add(cardPos);
        expectedFlippedCardPos.add(cardPos2);
        expectedFlippedCardPos.add(cardPos3);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));

        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));

        aBot.clearCardsFromMemory('A');
        aBot.removeFromFlipped();
        availableCardPicks.clear();
        expectedFlippedCardPos.clear();
        aBot.updateBoardMap(cardPos4,'B');
        aBot.updateBoardMap(cardPos5,'B');
        aBot.updateBoardMap(cardPos6,'B');
        expectedFlippedCardPos.add(cardPos6);
        expectedFlippedCardPos.add(cardPos4);
        expectedFlippedCardPos.add(cardPos5);
        availableCardPicks.add(cardPos6);
        availableCardPicks.add(cardPos4);
        availableCardPicks.add(cardPos5);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        aBot.generateCardPick(availableCardPicks);
        assertTrue(aBot.getFlippedCardsPos().containsAll(expectedFlippedCardPos));

    }
}