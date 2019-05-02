import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DuelingBoardTest {

    @Test
    public void changeBoard() {
        System.out.println("changeBoard");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(0, aBoard.getCurrentBoardIndex());
        aBoard.changeBoard();
        assertEquals(1, aBoard.getCurrentBoardIndex());
        aBoard.changeBoard();
        assertEquals(0, aBoard.getCurrentBoardIndex());
    }

    @Test
    public void getCurrentBoardIndex()
    {
        System.out.println("getCurrentBoardIndex");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(0, aBoard.getCurrentBoardIndex());
        aBoard.changeBoard();
        assertEquals(1, aBoard.getCurrentBoardIndex());
        aBoard.changeBoard();
        assertEquals(0, aBoard.getCurrentBoardIndex());
    }

    @Test
    public void flipCard() {
        System.out.println("flipCard");
        DuelingBoard aBoard = new DuelingBoard();
        assertTrue(aBoard.flipCard(2,3));
        assertFalse( aBoard.flipCard(2,3));
        assertTrue(aBoard.flipCard(1,4));
        aBoard.changeBoard();
        assertTrue( aBoard.flipCard(1,5));
        assertFalse( aBoard.flipCard(1,5));
        assertTrue(aBoard.flipCard(1,4));


    }

    @Test
    public void getMostRecentCardContent() {
        System.out.println("getMostRecentCardContent");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(0,aBoard.getMostRecentCardContent());
        aBoard.flipCard(1,2);
        assertEquals(aBoard.getCardContent(1,2),aBoard.getMostRecentCardContent());
        aBoard.flipCard(1,3);
        aBoard.flipCard(2,2);
        assertEquals(aBoard.getCardContent(2,2),aBoard.getMostRecentCardContent());

    }


    @Test
    public void doFlippedCardsMatch() {
        System.out.println("doFlippedCardsMatch");
        DuelingBoard aBoard = new DuelingBoard();
        assertFalse( aBoard.doFlippedCardsMatch());
        aBoard.flipCard(3,3);
        assertTrue( aBoard.doFlippedCardsMatch());

        aBoard = new DuelingBoard();

        boolean isMatched=false;
        aBoard.flipCard(3,3);
        char cardChar = aBoard.getCardContentBoard1(3,3);

        aBoard.changeBoard();

        aBoard.flipCard(0,3);
        if (aBoard.getCardContentBoard2(0,3)==cardChar) {
            isMatched = true;
        }
        assertEquals(isMatched, aBoard.doFlippedCardsMatch());

    }


    @Test
    public void getCardContent() {
        System.out.println("getCardContent");
        DuelingBoard aBoard = new DuelingBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContent(i,j) < 'A' || aBoard.getCardContent(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos;
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContent(k, m));
            }
        Character cardChar = ' ';
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContent(1, 1))
                cardChar = entry.getValue();


        Character cardChar2 = aBoard.getCardContent(1,1);
        assertEquals(cardChar,cardChar2);
    }

    @Test
    public void getCardContentBoard1() {
        System.out.println("getCardContentBoard1");
        DuelingBoard aBoard = new DuelingBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContentBoard1(i,j) < 'A' || aBoard.getCardContentBoard1(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos;
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContentBoard1(k, m));
            }
        Character cardChar = ' ';
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContentBoard1(2, 1))
                cardChar = entry.getValue();


        Character cardChar2 = aBoard.getCardContentBoard1(2,1);
        assertEquals(cardChar,cardChar2);
    }

    @Test
    public void getCardContentBoard2() {
        System.out.println("getCardContentBoard2");
        DuelingBoard aBoard = new DuelingBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (aBoard.getCardContentBoard2(i,j) < 'A' || aBoard.getCardContentBoard2(i,j) > 'Z') {
                    fail("found values that were not expected");
                }
            }
        }
        HashMap<int[], Character> boardMap = new HashMap<>();
        int[] cardPos;
        for (int k = 0; k < aBoard.getRows(); k++)
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContentBoard2(k, m));
            }
        Character cardChar = ' ';
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet())
            if (entry.getValue() == aBoard.getCardContentBoard2(2, 3))
                cardChar = entry.getValue();


        Character cardChar2 = aBoard.getCardContentBoard2(2,3);
        assertEquals(cardChar,cardChar2);
    }

    @Test
    public void removeCardsFromBoard() {
        System.out.println("removeCardsFromBoard");
        DuelingBoard aBoard = new DuelingBoard();
        aBoard.flipCard(2,2);
        assertEquals(aBoard.getCardContentBoard1(2,2),aBoard.removeCardsFromBoard());
        aBoard.changeBoard();
        aBoard.flipCard(3,2);
        assertEquals(aBoard.getCardContentBoard2(3,2),aBoard.removeCardsFromBoard());


        aBoard = new DuelingBoard();
        aBoard.flipCard(3,3);
        aBoard.changeBoard();
        HashMap<int[],Character> boardMap = new HashMap<>();
        int[] cardPos = new int[2];
        for (int k = 0; k < aBoard.getRows(); k++) {
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContentBoard2(k, m));
            }
        }
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet()) {
            if (entry.getValue() == aBoard.getCardContentBoard1(3, 3)) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        }
        aBoard.flipCard(cardPos[0], cardPos[1]);
        assertTrue(aBoard.doFlippedCardsMatch());
        assertEquals(aBoard.getCardContentBoard1(3, 3), aBoard.removeCardsFromBoard());


    }

    @Test
    public void getCardsFound() {
        System.out.println("getCardsFound");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(0, aBoard.getCardsFound());
        aBoard.flipCard(2,2);
        aBoard.removeCardsFromBoard();
        assertEquals(1,aBoard.getCardsFound());
        aBoard.changeBoard();
        assertEquals(1, aBoard.getCardsFound());
        aBoard.flipCard(0,0);
        aBoard.removeCardsFromBoard();
        assertEquals(2,aBoard.getCardsFound());

        aBoard = new DuelingBoard();
        aBoard.flipCard(3,3);
        aBoard.changeBoard();
        HashMap<int[],Character> boardMap = new HashMap<>();
        int[] cardPos = new int[2];
        for (int k = 0; k < aBoard.getRows(); k++) {
            for (int m = 0; m < aBoard.getCol(); m++) {
                cardPos = new int[2];
                cardPos[0] = k;
                cardPos[1] = m;
                boardMap.put(cardPos, aBoard.getCardContentBoard2(k, m));
            }
        }
        for (HashMap.Entry<int[], Character> entry : boardMap.entrySet()) {
            if (entry.getValue() == aBoard.getCardContentBoard1(3, 3)) {
                cardPos[0] = entry.getKey()[0];
                cardPos[1] = entry.getKey()[1];
                break;
            }
        }
        aBoard.flipCard(cardPos[0], cardPos[1]);
        aBoard.doFlippedCardsMatch();
        aBoard.removeCardsFromBoard();
        assertEquals(2,aBoard.getCardsFound());
    }

    @Test
    public void getRows() {
        System.out.println("getRows");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(4, aBoard.getRows());
    }

    @Test
    public void getCol() {
        System.out.println("getCol");
        DuelingBoard aBoard = new DuelingBoard();
        assertEquals(6, aBoard.getCol());
    }

}